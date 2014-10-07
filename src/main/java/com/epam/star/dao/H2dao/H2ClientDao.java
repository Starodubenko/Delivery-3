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
import java.util.HashMap;
import java.util.Map;

public class H2ClientDao extends AbstractH2Dao implements ClientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String TABLE_NAME = "users";
    private static final String ADD_CLIENT = "INSERT INTO  users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String RANGE_CLIENT = "SELECT * FROM users LIMIT ? OFFSET ?";
    private static final String UPDATE_CLIENT = "UPDATE users SET id = ?, login = ?, password = ?, firstname = ?, lastname = ?, middlename = ?," +
            "address = ?, telephone = ?, mobilephone = ?, identitycard = ?, workbook = ?, rnn = ?, sik = ?, " +
            "position_id = ?, virtual_balance = ? WHERE id = ?";

    private static final String FIND_BY_PARAMETERS =
            " SELECT *" +
                    " FROM users" +
                    " inner join positions" +
                    " on users.position_id = positions.id" +
                    " %s LIMIT ? OFFSET ?";
    private static Map<String, String> fieldsQueryMap = new HashMap<>();

    static {
        fieldsQueryMap.put("client-id", " users.id = ?");
        fieldsQueryMap.put("client-login", " users.login = ?");
        fieldsQueryMap.put("client-password", " users.password = ?");
        fieldsQueryMap.put("client-first-name", " users.firstname = ?");
        fieldsQueryMap.put("client-middle-name", " users.middlename = ?");
        fieldsQueryMap.put("client-last-name", " users.lastname = ?");
        fieldsQueryMap.put("client-address", " users.address = ?");
        fieldsQueryMap.put("client-telephone", " users.telephone = ?");
        fieldsQueryMap.put("client-mobilephone", " users.mobilephone = ?");
        fieldsQueryMap.put("client-identitycard", " users.identitycard = ?");
        fieldsQueryMap.put("client-workbook", " users.workbook = ?");
        fieldsQueryMap.put("client-rnn", " users.rnn = ?");
        fieldsQueryMap.put("client-sik", " users.sik = ?");
        fieldsQueryMap.put("client-position_id", " users.position_id = ?");
        fieldsQueryMap.put("client-virtual_balance", " users.virtual_balance = ?");
    }

    protected H2ClientDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    private Map<String, String> correctFields(Map<String, String> fields) {

        Map<String, String> newFields = new HashMap<>();

        for (Map.Entry<String, String> entry : fields.entrySet()) {
            if (entry.getValue() != null && entry.getValue() != "") newFields.put(entry.getKey(), entry.getValue());
        }
        return newFields;
    }

    private String createQuerryString(Map<String, String> fields) {

        String result = "";
        String conditions = "";

        int selectedFieldsCount = 0;

        for (Map.Entry<String, String> field : fields.entrySet()) {
            if (field.getValue() != null & field.getValue() != "") {
                selectedFieldsCount++;
                if (selectedFieldsCount > 1) conditions += " and ";
                conditions += fieldsQueryMap.get(field.getKey());
            }
        }

        if (selectedFieldsCount > 0) result += " where " + conditions;
        return result;
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
                " where POSITION_ID = 11 and LOGIN = " + "'" + login + "'" + " and PASSWORD = " + "'" + password + "'";
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
            prstm.setString(10, null);
            prstm.setString(11, null);
            prstm.setString(12, null);
            prstm.setString(13, null);
            prstm.setInt(14, client.getRole().getId());
            prstm.setBigDecimal(15, client.getVirtualBalance());
            prstm.setInt(16, client.getId());
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
    public Map<String, String> getParametersMap() {
        return fieldsQueryMap;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getFindByParameters() {
        return FIND_BY_PARAMETERS;
    }

    @Override
    public Client getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        PositionDao positionDao = daoManager.getPositionDao();

        Client client = new Client();
        try {
            client.setId(resultSet.getInt("id"));
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
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }
}
