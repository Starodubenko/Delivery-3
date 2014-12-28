package com.epam.star.entity;

import java.sql.Date;

public class Order2 extends Cart {

    private int number;
    private Client user;
    private Period period;
    private Date deliveryDate;
    private Date orderDate;
    private String additionalInfo;
    private boolean paid;
    private Discount discount;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discountId) {
        this.discount = discountId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period periodId) {
        this.period = periodId;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client userID) {
        this.user = userID;
    }
}
