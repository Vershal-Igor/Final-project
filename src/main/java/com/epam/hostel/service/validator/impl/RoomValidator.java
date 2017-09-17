package com.epam.hostel.service.validator.impl;


import com.epam.hostel.model.room.Room;
import com.epam.hostel.service.validator.Validator;

import java.math.BigDecimal;

/**
 * Class implements {@code Validator} interface and realize
 * its methods by validating {@code Room} objects.
 *
 * @author Vershal
 * @version 1.0
 */

public class RoomValidator implements Validator<Room> {
    private static final int LOWER_LIMIT = 0;

    private RoomValidator() {
    }

    private static class SingletonHolder {
        private static final RoomValidator INSTANCE = new RoomValidator();
    }

    public static RoomValidator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * This method check the value of room places. If it is lower than {@code LOWER_LIMIT}
     * constant method returns {@code false}.
     *
     * @param room checked bean
     * @return boolean
     */
    @Override
    public boolean validate(Room room) {
        return room.getRoomPlaces() > LOWER_LIMIT;
    }
}
