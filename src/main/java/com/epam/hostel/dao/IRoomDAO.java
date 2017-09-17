package com.epam.hostel.dao;


import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.room.Room;

import java.util.List;

public interface IRoomDAO extends IGenericDAO<Room, Long> {

    List<Room> showFreeRooms(Order order) throws DAOException;
}
