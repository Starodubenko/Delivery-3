package com.epam.star.dao.H2dao;

import com.epam.star.util.PropertiesManager;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    private final BoneCP connectionPool;
    private static PropertiesManager jdbcProperties;

    private DaoFactory() throws DaoException {

        try {
            jdbcProperties = new PropertiesManager("connection.properties");
        } catch (IOException e) {
            throw new DaoException(e);
        }

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new DaoException(e);
        }

        BoneCPConfig config = new BoneCPConfig();

        config.setJdbcUrl(jdbcProperties.getProperty("jdbc.url"));
        config.setUsername(jdbcProperties.getProperty("user.name"));
        config.setPassword(jdbcProperties.getProperty("password"));
        config.setMaxConnectionsPerPartition(20);

        try {
            connectionPool = new BoneCP(config);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void destroy(){
        connectionPool.shutdown();
    }

    public static DaoFactory getInstance() {
        return InstanceHolder.instance;
    }

    public DaoManager getDaoManager() throws DaoException {
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return new DaoManager(connection);
    }

    private static class InstanceHolder {
        private static DaoFactory instance = new DaoFactory();
    }

}

