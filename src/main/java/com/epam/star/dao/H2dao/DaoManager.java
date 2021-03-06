package com.epam.star.dao.H2dao;

import com.epam.star.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DaoManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static Connection connection;

    private static Map<String, Class<? extends Dao>> daosMap = new HashMap<>();

    static {
        daosMap.put("Clients", ClientDao.class);
        daosMap.put("Contacts", ContactDao.class);
        daosMap.put("Goods", GoodsDao.class);
        daosMap.put("Orders", OrderDao.class);
        daosMap.put("Periods", PeriodDao.class);
        daosMap.put("Positions", PositionDao.class);
        daosMap.put("Status", StatusDao.class);
        daosMap.put("Status card", PayCardStatusDao.class);
        daosMap.put("Employees", EmployeeDao.class);
    }

    public DaoManager(Connection connection) {
        this.connection = connection;
    }

    public void beginTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void closeConnection() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public AbstractH2Dao getDao(String name) {
        Constructor<?> constructor;
        constructor = daosMap.get(name).getConstructors()[1];
        AbstractH2Dao dao = null;
        try {
            dao = (AbstractH2Dao) constructor.newInstance(connection, this);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return dao;
    }

    public H2ClientDao getClientDao() {
        return new H2ClientDao(connection, this);
    }

    public H2ContactDao getContactDao() {

        return new H2ContactDao(connection, this);
    }

    public H2EmployeeDao getEmployeeDao() {
        return new H2EmployeeDao(connection, this);
    }

    public H2OrderDao getOrderDao() {
        return new H2OrderDao(connection, this);
    }

    public H2PeriodDao getPeriodDao() {
        return new H2PeriodDao(connection, this);
    }

    public H2StatusDao getStatusDao() {
        return new H2StatusDao(connection, this);
    }

    public H2GoodsDao getGoodsDao() {
        return new H2GoodsDao(connection, this);
    }

    public H2PayCardDao getPayCardDao() {
        return new H2PayCardDao(connection, this);
    }

    public H2PayCardStatusDao getPayCardStatusDao() {
        return new H2PayCardStatusDao(connection, this);
    }

    public H2PositionDao getPositionDao() {
        return new H2PositionDao(connection, this);
    }

    public H2ImageDao getImageDao() {
        return new H2ImageDao(connection, this);
    }
    public H2DiscountDao getDiscountDao() {
        return new H2DiscountDao(connection, this);
    }
    public H2OrderedGoodsDao getOrderedGoodsDao() {
        return new H2OrderedGoodsDao(connection, this);
    }
    public H2OrderDao2 getOrderDao2() {
        return new H2OrderDao2(connection, this);
    }
}
