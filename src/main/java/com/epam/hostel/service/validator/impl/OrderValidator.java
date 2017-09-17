package com.epam.hostel.service.validator.impl;


import com.epam.hostel.model.order.Order;
import com.epam.hostel.service.validator.Validator;

import java.util.Date;


public class OrderValidator implements Validator<Order> {
    private OrderValidator() {
    }

    private static class SingletonHolder {
        private static final OrderValidator INSTANCE = new OrderValidator();
    }

    public static OrderValidator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Method provide checking of {@code Order} objects. If date of order
     * is null or arrival date rise than date of departure method returns {@code false}.
     *
     * @param order checked bean
     */

    @Override
    public boolean validate(Order order) {
        if (order.getArrivalDate() == null || order.getDepartureDate() == null) {
            return false;
        } else {
            return order.getArrivalDate().before(order.getDepartureDate()) &&
                    order.getDepartureDate().after(new Date());
        }
    }
}
