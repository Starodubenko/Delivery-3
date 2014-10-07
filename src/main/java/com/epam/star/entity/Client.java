package com.epam.star.entity;

import java.math.BigDecimal;

public class Client extends AbstractUser {

    public Client() {
    }

    public Client(int id, String login, String password, String firstName, String lastName, String middleName, String address, String telephone, String mobilephone, Position role, BigDecimal virtualBalance) {
        super(id, login, password, firstName, lastName, middleName, address, telephone, mobilephone, role, virtualBalance);
    }
}
