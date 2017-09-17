package com.epam.hostel.service;


import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.room.Room;
import com.epam.hostel.service.exception.ServiceException;

import java.util.List;

/**
 * Interface defines methods for working with {@code Room} objects.
 *
 * @author Vershal
 * @version 1.0
 */
public interface IRoomService {

    /**
     * Method gives list of {@code Room} objects.
     */
    List<Room> findAll() throws ServiceException;

    /**
     * Method loads {@code Room} object by id.
     */
    Room findById(Long id);

    /**
     * Method add room by information from {@code Room} object.
     *
     * @param room contains information needed for add
     */
    void add(Room room) throws ServiceException;

    /**
     * Method occur deleting of {@code Room} record in a DB by room id.
     */
    void delete(long roomId) throws ServiceException;

    /**
     * Method gives list of {@code Room} free rooms.
     */
    List<Room> showFreeRooms(Order order) throws ServiceException;

    /**
     * Method updates {@code Room} object.
     */
    Long update(Room room);

    /**
     * Method provides update room price by room id
     */
    void updatePrice(long roomId) throws ServiceException;


}
