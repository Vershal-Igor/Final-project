package com.epam.hostel.model.order;


public enum OrderStatus {
    NEW(0),
    CONFIRMED(1),
    REJECTED(2),
    ARCHIVED(3);

    private int id;

    private OrderStatus(int id) {
        this.id = id;
    }

    public static OrderStatus valueOf(int id) {
        for(OrderStatus s : values()) {
            if (s.id == id) {
                return s;
            }
        }
        throw new IllegalArgumentException(
                "No enum constant " + OrderStatus.class.getCanonicalName() + " with id:" + id);
    }
}


