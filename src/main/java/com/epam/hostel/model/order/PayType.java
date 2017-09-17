package com.epam.hostel.model.order;


public enum PayType {
    COMPLEATE(0),
    BOOKING(1);

    private int id;

    private PayType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PayType valueOf(int id) {
        for (PayType pt : values()) {
            if (pt.id == id) {
                return pt;
            }
        }
        throw new IllegalArgumentException(
                "No enum constant " + PayType.class.getCanonicalName() + " with id:" + id);
    }
}
