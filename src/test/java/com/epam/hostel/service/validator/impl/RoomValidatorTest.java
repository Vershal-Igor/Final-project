package com.epam.hostel.service.validator.impl;

import com.epam.hostel.model.room.Room;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:dispatcher-servlet.xml", "classpath*:applicationContext.xml"})
public class RoomValidatorTest {
    private static final Logger logger = Logger.getLogger(RoomValidatorTest.class);

    private RoomValidator validator = RoomValidator.getInstance();
    private static Room roomInvalid;
    private static Room roomValid;
    private static byte WRONG_ROOM_PLACES = 0;
    private static byte RIGHT_ROOM_PLACES = 1;

    @Before
    public void setUp() throws Exception {
        roomInvalid = new Room();
        roomValid = new Room();
    }

    @Test
    public void validate() throws Exception {
        roomInvalid.setRoomPlaces(WRONG_ROOM_PLACES);
        roomValid.setRoomPlaces(RIGHT_ROOM_PLACES);
        logger.info("invalid:" + roomInvalid.getRoomPlaces() + "\tvalid:" + roomValid.getRoomPlaces());
        Assert.assertFalse(validator.validate(roomInvalid));
        Assert.assertTrue(validator.validate(roomValid));
    }

}