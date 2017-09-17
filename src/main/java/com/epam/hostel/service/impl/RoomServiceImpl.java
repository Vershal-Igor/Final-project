package com.epam.hostel.service.impl;


import com.epam.hostel.dao.impl.RoomDAOImpl;
import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.room.Room;
import com.epam.hostel.service.IRoomService;
import com.epam.hostel.service.exception.DuplicateEntityException;
import com.epam.hostel.service.exception.InvalidDataException;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.validator.impl.RoomValidator;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class implements {@code IRoomService} interface and realize
 * its methods for {@code RoomServiceImpl} objects management.
 *
 * @author Vershal
 * @version 1.0
 */
public class RoomServiceImpl implements IRoomService {
    public static Logger logger = Logger.getLogger(RoomServiceImpl.class);
    private RoomValidator roomValidator = RoomValidator.getInstance();

    private RoomDAOImpl roomDAOImpl;

    public void setRoomDAOImpl(RoomDAOImpl roomDAOImpl) {
        this.roomDAOImpl = roomDAOImpl;
    }

    /**
     * Method call {@code RoomDAOImpl}to return
     * all room information from DB
     */
    @Override
    public List<Room> findAll() throws ServiceException {
        List<Room> rooms = null;
        try {

            rooms = roomDAOImpl.findAll();

        } catch (DAOException e) {
            logger.error("error while selecting all room records", e);
            throw new ServiceException("error while selecting all room records", e);
        }
        return rooms;
    }

    /**
     * Method call {@code RoomDAOImpl}to return
     * room satisfying id information from DB
     */
    public Room findById(Long id) {
        return roomDAOImpl.findById(id);
    }

    /**
     * Method occur add a room by room number, room places and price.
     * First of all room data is checked for validity. If every thing is ok,
     * method calls {@code RoomDAOImpl} to provide add operation
     *
     * @param room contains information needed for add
     * @throws ServiceException
     */
    @Override
    public void add(Room room) throws ServiceException {
        if (roomValidator.validate(room)) {
            try {
                List<Room> rooms = roomDAOImpl.findAll();
                for (Room r : rooms) {
                    if (r.getRoomNumber() == room.getRoomNumber()) {
                        throw new DuplicateEntityException("duplicate room record");
                    }
                }
                roomDAOImpl.add(room);
            } catch (DAOException e) {
                logger.error("error while adding room record", e);
                throw new ServiceException("error while adding room record", e);
            }
        } else {
            logger.error("invalid room number");
            throw new InvalidDataException("invalid room number");
        }
    }

    /**
     * Method provide deleting a room record from DB by id
     *
     * @param roomId id of a room
     * @throws ServiceException
     */
    @Override
    public void delete(long roomId) throws ServiceException {
        try {
            roomDAOImpl.delete(roomId);
        } catch (DAOException e) {
            logger.error("error while deleting room", e);
            throw new ServiceException("error while deleting room", e);
        }
    }


    /**
     * Method show free roms by amount of guests arrival and departure dates
     *
     * @param order contains information needed for show free rooms
     * @throws ServiceException
     */
    @Override
    public List<Room> showFreeRooms(Order order) throws ServiceException {
        List<Room> rooms = null;
        try {
            order = new Order();
            rooms = roomDAOImpl.showFreeRooms(order);
        } catch (DAOException e) {
            logger.error("error while selecting free rooms records", e);
            throw new ServiceException("error while selecting free rooms records", e);
        }
        return rooms;
    }

    /**
     * Method call {@code RoomDAOImpl}to provide
     * updating room
     *
     * @param room
     */
    @Override
    public Long update(Room room) {
        return roomDAOImpl.update(room);
    }

    /**
     * Method call {@code RoomDAOImpl}to provide
     * updating room price
     *
     * @param roomId contains information needed for update
     * @throws ServiceException
     */
    @Override
    public void updatePrice(long roomId) throws ServiceException {
        Room room = new Room();
        room.setId(roomId);
        roomDAOImpl.update(room);
    }

}
