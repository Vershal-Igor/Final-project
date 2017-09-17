package com.epam.hostel.model.user;

public enum UserStatus {
    BANNED(0),
    ADMITTED(1);//допущен

    private int id;

    private UserStatus(int id) {
        this.id = id;
    }

    public static UserStatus valueOf(int id) {
        for(UserStatus s : values()) {
            if (s.id == id) {
                return s;
            }
        }
        throw new IllegalArgumentException(
                "No enum constant " + UserStatus.class.getCanonicalName() + " with id:" + id);
    }
}

