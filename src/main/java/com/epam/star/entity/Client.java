package com.epam.star.entity;

import java.math.BigDecimal;

public class Client extends AbstractEntity {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String telephone;
    private String mobilephone;
    private Position role;
    private BigDecimal VirtualBalance;

    public Client() {
    }

    public Client(int id, String login, String password, String firstName, String lastName, String middleName,
                  String address, String telephone, String mobilephone, Position role, BigDecimal virtualBalance) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.telephone = telephone;
        this.mobilephone = mobilephone;
        this.role = role;
        VirtualBalance = virtualBalance;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Position getRole() {
        return role;
    }

    public void setRole(Position role) {
        this.role = role;
    }

    public BigDecimal getVirtualBalance() {
        return VirtualBalance;
    }

    public void setVirtualBalance(BigDecimal virtualBalance) {
        VirtualBalance = virtualBalance;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
