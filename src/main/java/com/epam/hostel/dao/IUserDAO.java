package com.epam.hostel.dao;


import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.user.User;


public interface IUserDAO extends IGenericDAO<User, Long> {

    User getUserInf(User user) throws DAOException;

    int checkUser(User user) throws DAOException;

    boolean setBan(Long id) throws DAOException;

    boolean setUnban(Long id) throws DAOException;

}
