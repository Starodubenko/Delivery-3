package com.epam.star.entity;

public abstract class AbstractEntity {
    protected AbstractEntity(int id) {
        this.id = id;
    }

    private int id;

    public AbstractEntity() {

    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
}
