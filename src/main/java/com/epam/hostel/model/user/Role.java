package com.epam.hostel.model.user;


public enum Role {
    CLIENT(1),
    ADMIN(0);

    private int id;

    private Role(int id) {
        this.id = id;
    }

    public static Role valueOf(int id) {
        for(Role r : values()) {
            if (r.id == id) {
                return r;
            }
        }
        throw new IllegalArgumentException(
                "No enum constant " + Role.class.getCanonicalName() + " with id:" + id);
    }
}
