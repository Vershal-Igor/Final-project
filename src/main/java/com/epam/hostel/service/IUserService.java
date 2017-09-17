package com.epam.hostel.service;

import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

/**
 * This interface define methods for working with {@code User}
 * objects.
 *
 * @author Vershal
 * @version 1.0
 */

public interface IUserService {
    List<User> findAll() throws ServiceException;

    /**
     * Method gives user bean by id.
     */
    User findById(Long id);

    /**
     * Method provides sign in of user.
     *
     * @param user contains information needed for sign in
     */
    User signIn(User user) throws ServiceException;

    /**
     * Method provides sign up of user.
     *
     * @param user contains information needed for sign up
     */
    long signUp(User user) throws ServiceException;

    /**
     * Method add user by information from {@code User} object
     *
     * @param user contains information needed for add
     */
    void add(User user) throws ServiceException;

    /**
     * Method occur deleting of {@code User} record in a DB by user id
     */
    void delete(long userId) throws ServiceException;

    /**
     * Method updates {@code User} object.
     */
    Long update(User user) throws DAOException, ServiceException,DuplicateKeyException;

    /**
     * Method provides update of user's name, surname and login by id
     */
    void editNameSurnameLogin(long userId) throws ServiceException, DAOException;

    /**
     * Method provides banned of user record by id
     */
    boolean setBan(Long userId) throws ServiceException;

    /**
     * Method provides unbanned of user record by id
     */
    boolean setUnban(Long userId) throws ServiceException;

}
