package com.epam.star.entity;

import java.math.BigDecimal;

public class Employee extends Client {
    private String identityCard;
    private String workBook;
    private String RNN;
    private String SIK;

    public Employee(int id, String login, String password, String firstName, String lastName, String middleName,
                    String address, String telephone, String mobilephone, Position role, BigDecimal virtualBalance,
                    String mobilephone1, String identityCard, String workBook, String RNN, String SIK) {
        super(id, login, password, firstName, lastName, middleName, address, telephone, mobilephone, role, virtualBalance);
        this.identityCard = identityCard;
        this.workBook = workBook;
        this.RNN = RNN;
        this.SIK = SIK;
    }

    public Employee() {

    }

    public String getSIK() {
        return SIK;
    }

    public void setSIK(String SIK) {
        this.SIK = SIK;
    }

    public String getRNN() {
        return RNN;
    }

    public void setRNN(String RNN) {
        this.RNN = RNN;
    }

    public String getWorkBook() {
        return workBook;
    }

    public void setWorkBook(String workBook) {
        this.workBook = workBook;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
}
