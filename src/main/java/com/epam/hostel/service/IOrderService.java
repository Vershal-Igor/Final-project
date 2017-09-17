package com.epam.hostel.service;


import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.service.exception.ServiceException;

import java.util.List;

/**
 * Interface defines methods for working with {@code Order} objects.
 *
 * @author Vershal
 * @version 1.0
 */
public interface IOrderService {

    /**
     * Method gives list of {@code Order} objects.
     */
    List<Order> findAll() throws ServiceException;

    /**
     * Method loads {@code Order} object by id.
     */
    Order findById(Long id);

    /**
     * Method updates {@code Order} object.
     */
    Long update(Order update);

    /**
     * Method provides discount of order by id.
     */
    void setDiscount(long orderId) throws ServiceException;

    /**
     * Method finds order by user id.
     */
    List<Order> findUserOrder(long userId) throws ServiceException;

    /**
     * Method add room by information from {@code Order} object.
     *
     * @param order contains information needed for add
     */
    void add(Order order) throws ServiceException;

    /**
     * Method occur deleting of {@code Order} record in a DB by order id.
     */
    void delete(long orderId) throws ServiceException;

    /**
     * Method provides confirmed of order record by id.
     */
    boolean setConfirm(Long orderId) throws ServiceException;

    /**
     * Method provides rejected of order record by id.
     */
    boolean setReject(Long orderId) throws ServiceException;

    /**
     * Method provides archived of order record by id.
     */
    boolean setArchive(Long orderId) throws ServiceException;
}
