package com.epam.hostel.service.impl;


import com.epam.hostel.dao.impl.UserDAOImpl;
import com.epam.hostel.dao.exception.DAOException;

import com.epam.hostel.model.user.User;
import com.epam.hostel.service.IUserService;
import com.epam.hostel.service.exception.DuplicateEntityException;
import com.epam.hostel.service.exception.InvalidDataException;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.validator.IUserValidator;
import com.epam.hostel.service.validator.impl.UserValidator;
import org.apache.log4j.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.dao.DuplicateKeyException;
//вроде есть и import org.springframework.util.DigestUtils;
// но не хочет подключаться md5Hex

import java.util.List;

/**
 * Class implements {@code IUserService} interface and realize
 * its methods for {@code UserServiceImpl} objects management.
 *
 * @author Vershal
 * @version 1.0
 */
public class UserServiceImpl implements IUserService {
    public static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDAOImpl userDAOImpl;

    public void setUserDAOImpl(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    private IUserValidator userValidator = UserValidator.getInstance();

    private static final int ZERO_RECORDS_COUNT = 0;

    /**
     * Method call {@code UserDAOImpl}to return
     * all user information from DB.
     */
    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users = null;
        try {
            users = userDAOImpl.findAll();
        } catch (DAOException e) {
            logger.error("error while find All users", e);
            throw new ServiceException("error while find All users", e);
        }
        return users;
    }

    /**
     * Method call {@code UserDAOImpl}to return all information
     * about particular user from DB.
     */
    @Override
    public User findById(Long id) {
        return userDAOImpl.findById(id);
    }

    /**
     * Method occur sign in a user by its login and password.
     * First of all user data is checked for validity. If every thing is ok,
     * method calls {@code UserDAOImpl} to provide sign in operation.
     *
     * @param user contains information needed for sign in
     * @return {@code User} object that contains user id
     * @throws ServiceException
     */
    @Override
    public User signIn(User user) throws ServiceException {
        if (userValidator.validateLogPass(user)) {
            try {
                String password = user.getPassword();
                user.setPassword(DigestUtils.md5Hex(password));

                int recordCount = userDAOImpl.checkUser(user);
                if (recordCount == ZERO_RECORDS_COUNT) {
                    return null;
                } else {
                    return userDAOImpl.getUserInf(user);
                }
            } catch (DAOException e) {
                logger.error("error while sign in user", e);
                throw new ServiceException("error while sign in user", e);
            }
        } else {
            logger.error("invalid data");
            throw new InvalidDataException("invalid data");
        }
    }

    /**
     * Method occur sign up a user by its name, surname, login and password.
     * First of all user data is checked for validity. If every thing is ok,
     * method calls {@code UserDAOImpl} to provide sign up operation.
     *
     * @param user contains information needed for sign up
     * @return long
     * @throws ServiceException
     */
    @Override
    public long signUp(User user) throws ServiceException {
        if (userValidator.validate(user)) {
            try {
                String password = user.getPassword();
                user.setPassword(DigestUtils.md5Hex(password));

                /*int recordsCount = userDAOImpl.checkUser(user);
                if (recordsCount != ZERO_RECORDS_COUNT) {
                    throw new DuplicateEntityException("there is a record with such data");
                } else {*/
                    return userDAOImpl.add(user);
                /*}*/
            } catch (DAOException e) {
                logger.error("error while sign up user", e);
                throw new ServiceException("error while sign up user", e);
            }
        } else {
            logger.error("invalid user data");
            throw new InvalidDataException("invalid user data");
        }
    }

    /**
     * Method occur add a user by its name, surname, login and password.
     * First of all user data is checked for validity. If every thing is ok,
     * method calls {@code UserDAOImpl} to provide add operation.
     *
     * @param user contains information needed for add
     * @throws ServiceException
     */
    @Override
    public void add(User user) throws ServiceException/*, DuplicateKeyException*/ {
        if (userValidator.validate(user)) {
            try {
                String password = user.getPassword();
                user.setPassword(DigestUtils.md5Hex(password));
                List<User> users = userDAOImpl.findAll();
                for (User r : users) {
                    if (r.getLogin().equals(user.getLogin())) {
                        throw new DuplicateEntityException("duplicate user record");
                    }
                }
                userDAOImpl.add(user);
            } catch (DAOException e) {
                logger.error("error while adding user record", e);
                throw new ServiceException("error while adding user record", e);
            }
        } else {
            logger.error("invalid user login");
            throw new InvalidDataException("invalid user login");
        }
    }


    /**
     * Method provide deleting a user record from DB by its id.
     *
     * @param userId id of a user
     * @throws ServiceException
     */
    @Override
    public void delete(long userId) throws ServiceException {
        try {
            userDAOImpl.delete(userId);
        } catch (DAOException e) {
            logger.error("error while deleting user", e);
            throw new ServiceException("error while deleting user", e);
        }
    }

    /**
     * Method call {@code UserDAOImpl}to provide
     * updating user
     *
     * @param user
     * @throws DAOException
     * @throws ServiceException
     */
    @Override
    public Long update(User user) throws DAOException, ServiceException/*, DuplicateKeyException*/ {
        if (userValidator.validateNameSurnameLogin(user)) {
            try {
                List<User> users = userDAOImpl.findAll();
                for (User r : users) {
                    if (r.getLogin().equals(user.getLogin())) {
                        throw new DuplicateEntityException("duplicate user record");
                    }
                }
                return userDAOImpl.update(user);
            } catch (DAOException e) {
                logger.error("error while edit user", e);
                throw new ServiceException("error while edit user", e);
            }
        } else {
            logger.error("invalid user data");
            throw new InvalidDataException("invalid user data");
        }
    }

    /**
     * Method provide updating user's name, surname and login by id.
     *
     * @param userId contains information needed for update
     * @throws ServiceException
     */
    @Override
    public void editNameSurnameLogin(long userId) throws ServiceException, DAOException {
        User user = new User();
        user.setId(userId);
        userDAOImpl.update(user);


    }

    /**
     * Method make banned user
     *
     * @param userId contains information needed for banned user
     * @throws ServiceException
     */
    @Override
    public boolean setBan(Long userId) throws ServiceException {
        boolean banned = false;
        try {
            banned = userDAOImpl.setBan(userId);
        } catch (DAOException e) {
            logger.error("error while set ban user", e);
            throw new ServiceException("error while set ban user", e);
        }
        return banned;
    }

    /**
     * Method make unbanned user
     *
     * @param userId contains information needed for unbanned user
     * @throws ServiceException
     */
    @Override
    public boolean setUnban(Long userId) throws ServiceException {
        boolean unbanned = false;
        try {
            unbanned = userDAOImpl.setUnban(userId);
        } catch (DAOException e) {
            logger.error("error while set unban user", e);
            throw new ServiceException("error while set unban user", e);
        }
        return unbanned;
    }


}

