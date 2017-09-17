package com.epam.hostel.dao;


import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.order.Order;

import java.util.List;

public interface IOrderDAO extends IGenericDAO<Order, Long> {
    List<Order> historyUser(long userId) throws DAOException;

    boolean setConfirm(Long id) throws DAOException;

    boolean setReject(Long id) throws DAOException;

    boolean setArchive(Long id) throws DAOException;
}
