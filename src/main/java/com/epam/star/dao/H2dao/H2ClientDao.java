package com.epam.star.dao.H2dao;

import com.epam.star.dao.ClientDao;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2ClientDao extends AbstractH2Dao implements ClientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_CLIENT = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String RANGE_CLIENT = "SELECT * FROM users LIMIT ? OFFSET ?";
    private static final String UPDATE_CLIENT = "UPDATE USERS SET ID = ?, LOGIN = ?, PASSWORD = ?, FIRSTNAME = ?, " +
            "LASTNAME = ?, MIDDLENAME = ?, ADDRESS = ?, TELEPHONE = ?, MOBILEPHONE = ?," +
            "POSITION_ID = ?, VIRTUAL_BALANCE = ?, AVATAR = ?, DISCOUNT = ? WHERE ID = ?";

    private static final String NECESSARY_COLUMNS =
            " USERS.ID, USERS.LOGIN, USERS.PASSWORD, USERS.FIRSTNAME, USERS.LASTNAME, " +
                    "USERS.MIDDLENAME, USERS.ADDRESS, USERS.TELEPHONE, USERS.MOBILEPHONE, " +
                    "POSITIONS.POSITION_NAME";

    private static final String ADDITIONAL_COLUMNS =
            " ,USERS.VIRTUAL_BALANCE, USERS.AVATAR, USERS.DISCOUNT";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s" +
                    " FROM USERS" +
                    " INNER JOIN POSITIONS" +
                    " ON USERS.POSITION_ID = POSITIONS.ID";

    private static final String ID_FIELD = " USERS.ID, ";


    protected H2ClientDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public Client findByLogin(String login) throws DaoException {
        String sql = "select * from clients where login = " + "'" + login + "'";

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findByName(String name) throws DaoException {
        String sql = "select * from clients where firstname = " + "'" + name + "'";

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findBySurnameName(String surName) throws DaoException {
        String sql = "select * from clients where surname = " + "'" + surName + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findByAddress(String address) throws DaoException {
        String sql = "select * from clients where address= " + "'" + address + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findByTelephone(String telephone) throws DaoException {
        String sql = "select * from clients where telephone = " + "'" + telephone + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findByMobilephone(String mobilephone) throws DaoException {
        String sql = "select * from clients where surname = " + "'" + mobilephone + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return getEntityFromResultSet(resultSet);
    }

    @Override
    public Client findByCredentials(String login, String password) throws DaoException {
        String sql = "SELECT *" +
                " FROM USERS" +
                " inner join POSITIONS" +
                " on users.POSITION_ID = positions.id" +
                " where POSITION_ID = 11 and Lower(LOGIN) = " + "Lower('" + login + "')" + " and PASSWORD = " + "'" + password + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                client = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findById(int ID) throws DaoException {
        String sql = "select * from users where id = " + ID;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                client = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public String insert(Client client) throws DaoException {

        String status = "Client do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(ADD_CLIENT);
            prstm.setString(1, null);
            prstm.setString(2, client.getLogin());
            prstm.setString(3, client.getPassword());
            prstm.setString(4, client.getFirstName());
            prstm.setString(5, client.getLastName());
            prstm.setString(6, client.getMiddleName());
            prstm.setString(7, client.getAddress());
            prstm.setString(8, client.getTelephone());
            prstm.setString(9, client.getMobilephone());
            prstm.setString(10, null);
            prstm.setString(11, null);
            prstm.setString(12, null);
            prstm.setString(13, null);
            prstm.setInt(14, client.getRole().getId());
            prstm.setBigDecimal(15, client.getVirtualBalance());
            prstm.setInt(16, 0);
            prstm.setInt(17, 0);
            prstm.execute();
            status = "Client added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        String status = "Client do not deleted";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(UPDATE_CLIENT);
            prstm.setString(1, String.valueOf(ID));
            prstm.execute();
            status = "Client successfully deleted";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String updateEntity(Client client) throws DaoException {
        String status = "Client do not updated";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(UPDATE_CLIENT);
            prstm.setInt(1, client.getId());
            prstm.setString(2, client.getLogin());
            prstm.setString(3, client.getPassword());
            prstm.setString(4, client.getFirstName());
            prstm.setString(5, client.getLastName());
            prstm.setString(6, client.getMiddleName());
            prstm.setString(7, client.getAddress());
            prstm.setString(8, client.getTelephone());
            prstm.setString(9, client.getMobilephone());
            prstm.setInt(10, client.getRole().getId());
            prstm.setBigDecimal(11, client.getVirtualBalance());
            prstm.setInt(12, client.getAvatar().intValue());
            prstm.setInt(13, client.getDiscount().getPercentage());
            prstm.setInt(14, client.getId());
            prstm.executeUpdate();
            status = "Client updated successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String getFindByParameters(Boolean needAditionalColumns) {

        String columns = NECESSARY_COLUMNS;

        if (needAditionalColumns == true){
            columns = columns + ADDITIONAL_COLUMNS;
        }

        String result = String.format(FIND_BY_PARAMETERS_WITHOUT_COLUMNS,columns);

        result = String.format(result+"%s", LIMIT_OFFSET);

        return result;
    }

    @Override
    public String getFindByParametersWithoutColumns() {
        return FIND_BY_PARAMETERS_WITHOUT_COLUMNS;
    }

    @Override
    public String getNecessaryColumns() {
        return NECESSARY_COLUMNS;
    }

    @Override
    public String getAdditionalColumns() {
        return ADDITIONAL_COLUMNS;
    }

    @Override
    public String getIdField() {
        return ID_FIELD;
    }

    @Override
    public Client getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        PositionDao positionDao = daoManager.getPositionDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        Client client = new Client();
        try {
            client.setId(resultSet.getInt("id"));
            client.setAvatar(resultSet.getInt("avatar"));
            client.setLogin(resultSet.getString("login"));
            client.setPassword(resultSet.getString("password"));
            client.setFirstName(resultSet.getString("firstname"));
            client.setLastName(resultSet.getString("lastname"));
            client.setMiddleName(resultSet.getString("middlename"));
            client.setAddress(resultSet.getString("address"));
            client.setTelephone(resultSet.getString("telephone"));
            client.setMobilephone(resultSet.getString("mobilephone"));
            client.setRole(positionDao.findById(resultSet.getInt("position_id")));
            client.setVirtualBalance(new BigDecimal(resultSet.getInt("virtual_balance")));
            client.setDiscount(discountDao.findById(resultSet.getInt("discount")));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }
}
