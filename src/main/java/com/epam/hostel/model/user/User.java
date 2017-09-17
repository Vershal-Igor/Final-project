package com.epam.hostel.model.user;


import com.epam.hostel.model.entity.Entity;

import java.io.Serializable;

/**
 * Class stores the user object with fields:
 * <b>role</b>
 * <b>name</b>
 * <b>surname</b>
 * <b>login</b>
 * <b>password</b>
 * <b>userStatus</b>
 *
 * @author Vershal
 * @version 1.0
 */
public class User extends Entity implements Serializable {
    private Role role;
    private String name;
    private String surname;
    private String login;
    private transient String password;
    private UserStatus userStatus;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public boolean isBanned() {
        return userStatus.BANNED == userStatus;
    }


    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (role != user.role) return false;
        if (!name.equals(user.name)) return false;
        if (!surname.equals(user.surname)) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        return userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        int result = role.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + userStatus.hashCode();
        return result;
    }
}
