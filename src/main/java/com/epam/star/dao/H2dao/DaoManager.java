package com.epam.star.dao.H2dao;

import com.epam.star.dao.DaoCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static Connection connection;

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

    public Object executeAndCloce(DaoCommand comand) throws DaoException {
        try {
            return comand.execute(this);
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
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
}
