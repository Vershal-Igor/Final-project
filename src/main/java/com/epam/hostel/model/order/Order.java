package com.epam.hostel.model.order;


import com.epam.hostel.model.entity.Entity;
import com.epam.hostel.model.room.Room;
import com.epam.hostel.model.user.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Class stores the order object with fields:
 * <b>arrivalDate</b>
 * <b>departureDate</b>
 * <b>placesAmount</b>
 * <b>payType</b>
 * <b>discount</b>
 * <b>orderStatus</b>
 * <b>finalPrice</b>
 * <b>user</b>
 * <b>userId</b>
 * <b>user</b>
 * <b>room</b>
 * <b>roomId</b>
 * <b>name</b>
 * <b>surname</b>
 * <b>roomNumber</b>
 *
 * @author Vershal
 * @version 1.0
 */
public class Order extends Entity implements Serializable {
    private Date arrivalDate;
    private Date departureDate;
    private int placesAmount;
    private PayType payType;
    private BigDecimal discount;
    private OrderStatus orderStatus;
    private BigDecimal finalPrice;
    private User user;
    private Long userId;
    private Room room;
    private Long roomId;
    private String name;
    private String surname;
    private byte roomNumber;

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getPlacesAmount() {
        return placesAmount;
    }

    public void setPlacesAmount(int placesAmount) {
        this.placesAmount = placesAmount;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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

    public byte getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(byte roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isConfirm() {
        return orderStatus.CONFIRMED == orderStatus;
    }

    public boolean isReject() {
        return orderStatus.REJECTED == orderStatus;
    }

    public boolean isArchive() {
        return orderStatus.ARCHIVED == orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", placesAmount=" + placesAmount +
                ", payType=" + payType +
                ", discount=" + discount +
                ", orderStatus=" + orderStatus +
                ", finalPrice=" + finalPrice +
                ", user=" + user +
                ", userId=" + userId +
                ", room=" + room +
                ", roomId=" + roomId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roomNumber=" + roomNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (placesAmount != order.placesAmount) return false;
        if (roomNumber != order.roomNumber) return false;
        if (arrivalDate != null ? !arrivalDate.equals(order.arrivalDate) : order.arrivalDate != null) return false;
        if (departureDate != null ? !departureDate.equals(order.departureDate) : order.departureDate != null)
            return false;
        if (payType != order.payType) return false;
        if (discount != null ? !discount.equals(order.discount) : order.discount != null) return false;
        if (orderStatus != order.orderStatus) return false;
        if (finalPrice != null ? !finalPrice.equals(order.finalPrice) : order.finalPrice != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (userId != null ? !userId.equals(order.userId) : order.userId != null) return false;
        if (room != null ? !room.equals(order.room) : order.room != null) return false;
        if (roomId != null ? !roomId.equals(order.roomId) : order.roomId != null) return false;
        if (name != null ? !name.equals(order.name) : order.name != null) return false;
        return surname != null ? surname.equals(order.surname) : order.surname == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + placesAmount;
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (finalPrice != null ? finalPrice.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (roomId != null ? roomId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (int) roomNumber;
        return result;
    }
}
