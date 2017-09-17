package com.epam.hostel.model.room;


import com.epam.hostel.model.entity.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Class stores the room object with fields:
 * <b>roomNumber</b>
 * <b>roomPlaces</b>
 * <b>surname</b>
 * <b>price</b>
 *
 * @author Vershal
 * @version 1.0
 */
public class Room extends Entity implements Serializable {
    private byte roomNumber;
    private byte roomPlaces;
    private BigDecimal price;

    public byte getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(byte roomNumber) {
        this.roomNumber = roomNumber;
    }

    public byte getRoomPlaces() {
        return roomPlaces;
    }

    public void setRoomPlaces(byte roomPlaces) {
        this.roomPlaces = roomPlaces;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomPlaces=" + roomPlaces +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Room room = (Room) o;

        if (roomNumber != room.roomNumber) return false;
        if (roomPlaces != room.roomPlaces) return false;
        return price != null ? price.equals(room.price) : room.price == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) roomNumber;
        result = 31 * result + (int) roomPlaces;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
