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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity entity = (AbstractEntity) o;

        if (id != entity.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
