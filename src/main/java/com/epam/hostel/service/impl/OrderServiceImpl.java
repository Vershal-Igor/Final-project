package com.epam.hostel.service.impl;


import com.epam.hostel.dao.impl.OrderDAOImpl;
import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.service.IOrderService;
import com.epam.hostel.service.exception.InvalidDataException;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.validator.impl.OrderValidator;
import com.epam.hostel.service.validator.impl.RoomValidator;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class implements {@code IOrderService} interface and realize
 * its methods for {@code OrderServiceImpl} objects management.
 *
 * @author Vershal
 * @version 1.0
 */
public class OrderServiceImpl implements IOrderService {
    public static Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private OrderValidator orderValidator = OrderValidator.getInstance();

    private OrderDAOImpl orderDAOImpl;

    public void setOrderDAOImpl(OrderDAOImpl orderDAOImpl) {
        this.orderDAOImpl = orderDAOImpl;
    }

    /**
     * Method call {@code OrderDAOImpl}to return
     * all order information from DB.
     */
    @Override
    public List<Order> findAll() throws ServiceException {
        List<Order> orders = null;
        try {

            orders = orderDAOImpl.findAll();

        } catch (DAOException e) {
            logger.error("error while selecting all order records", e);
            throw new ServiceException("error while selecting all order records", e);
        }
        return orders;
    }

    /**
     * Method call {@code OrderDAOImpl}to return
     * order satisfying id information from DB
     */
    public Order findById(Long id) {
        return orderDAOImpl.findById(id);
    }

    /**
     * Method call {@code OrderDAOImpl} to provide
     * updating order
     *
     * @param order
     */
    public Long update(Order order) {
        return orderDAOImpl.update(order);
    }

    /**
     * Method call {@code OrderDAOImpl} to provides
     * discount of order by id.
     */
    @Override
    public void setDiscount(long orderId) throws ServiceException {
        Order order = new Order();
        order.setId(orderId);
        orderDAOImpl.update(order);

    }

    /**
     * Method call {@code OrderDAOImpl}to return
     * all order information for particular user.
     *
     * @param userId
     * @throws ServiceException
     */
    @Override
    public List<Order> findUserOrder(long userId) throws ServiceException {
        List<Order> orders = null;
        try {

            orders = orderDAOImpl.historyUser(userId);

        } catch (DAOException e) {
            logger.error("error while selecting all order records for particular user", e);
            throw new ServiceException("error while selecting all order records for particular user", e);
        }
        return orders;
    }

    /**
     * Method add room by information from {@code Order} object.
     *
     * @param order contains information needed for add
     */
    @Override
    public void add(Order order) throws ServiceException {
        if (orderValidator.validate(order)) {
            try {
                orderDAOImpl.add(order);
            } catch (DAOException e) {
                logger.error("error while adding data to order table", e);
                throw new ServiceException("error while adding data to order table", e);
            }
        } else {
            logger.error("invalid order data");
            throw new InvalidDataException("invalid order data");
        }

    }

    /**
     * Method provide deleting a order record from DB by id
     *
     * @param orderId id of a order
     * @throws ServiceException
     */
    @Override
    public void delete(long orderId) throws ServiceException {
        try {
            orderDAOImpl.delete(orderId);
        } catch (DAOException e) {
            logger.error("error while deleting order", e);
            throw new ServiceException("error while deleting order", e);
        }
    }


    /**
     * Method make confirm order
     *
     * @param orderId contains information needed for confirmed order
     * @throws ServiceException
     */
    @Override
    public boolean setConfirm(Long orderId) throws ServiceException {
        boolean status = false;
        try {
            status = orderDAOImpl.setConfirm(orderId);
        } catch (DAOException e) {
            logger.error("error while set confirm order", e);
            throw new ServiceException("error while set confirm order", e);
        }
        return status;
    }

    /**
     * Method make reject order
     *
     * @param orderId contains information needed for reject order
     * @throws ServiceException
     */
    @Override
    public boolean setReject(Long orderId) throws ServiceException {
        boolean status = false;
        try {
            status = orderDAOImpl.setReject(orderId);
        } catch (DAOException e) {
            logger.error("error while set reject order", e);
            throw new ServiceException("error while set reject order", e);
        }
        return status;
    }

    /**
     * Method make archive order
     *
     * @param orderId contains information needed for archive order
     * @throws ServiceException
     */
    @Override
    public boolean setArchive(Long orderId) throws ServiceException {
        boolean status = false;
        try {
            status = orderDAOImpl.setArchive(orderId);
        } catch (DAOException e) {
            logger.error("error while set archive order", e);
            throw new ServiceException("error while set archive order", e);
        }
        return status;
    }

}
