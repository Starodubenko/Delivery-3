package com.epam.star.dao;

import com.epam.star.entity.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    public List<Order> findAllByClientIdToday(int id);

    public List<Order> findAllByClientIdLastDays(int id);

    int getClientOrdersCount(int id);
}
