package com.epam.hostel.service.validator.impl;

import com.epam.hostel.model.order.Order;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:dispatcher-servlet.xml", "classpath*:applicationContext.xml"})
public class OrderValidatorTest {
    private static final Logger logger = Logger.getLogger(OrderValidatorTest.class);
    private static Order orderInvalid;
    private static Order orderNullInvalid;
    private static final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private OrderValidator validator = OrderValidator.getInstance();

    @Before
    public void setUp() throws Exception {
        orderInvalid = new Order();
        orderNullInvalid = new Order();
    }

    @Test
    public void validate() throws Exception {
        orderInvalid.setArrivalDate(new java.sql.Date(formatter.parse("05.04.2017").getTime()));
        orderInvalid.setDepartureDate(new java.sql.Date(formatter.parse("01.04.2017").getTime()));
        logger.info("invalid:" + orderInvalid.getArrivalDate() + " " + orderInvalid.getDepartureDate());
        Assert.assertFalse("invalid", validator.validate(orderInvalid));

        orderNullInvalid.setArrivalDate(null);
        orderNullInvalid.setDepartureDate(new java.sql.Date(formatter.parse("05.04.2017").getTime()));
        logger.info("Null invalid:" + orderNullInvalid.getArrivalDate() + " " + orderNullInvalid.getDepartureDate());
        Assert.assertFalse("null invalid", validator.validate(orderNullInvalid));

    }

}