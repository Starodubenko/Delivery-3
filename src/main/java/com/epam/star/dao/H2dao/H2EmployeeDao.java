package com.epam.star.dao.H2dao;

import com.epam.star.dao.EmployeeDao;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2EmployeeDao extends AbstractH2Dao implements EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_EMPLOYEE = "INSERT INTO  USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_EMPLOYEE = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_EMPLOYEE =
            " UPDATE USERS SET ID = ?, LOGIN = ?, PASSWORD = ?, FIRSTNAME = ?,  LASTNAME = ?, MIDDLENAME = ?," +
                    " ADDRESS = ?, TELEPHONE = ?, MOBILEPHONE = ?, IDENTITYCARD = ?, WORKBOOK = ?, RNN = ?, SIK = ?," +
                    " POSITION_ID = ?, VIRTUAL_BALANCE = ?, AVATAR = ? WHERE ID = ?";

    private static final String NECESSARY_COLUMNS =
            " USERS.ID, USERS.LOGIN, USERS.PASSWORD, USERS.FIRSTNAME, USERS.LASTNAME, " +
                    "USERS.MIDDLENAME, USERS.ADDRESS, USERS.TELEPHONE, USERS.MOBILEPHONE, " +
                    "USERS.IDENTITYCARD, USERS.WORKBOOK, USERS.RNN, USERS.SIK " +
                    "POSITIONS.POSITION_NAME";

    private static final String ADDITIONAL_COLUMNS =
            " USERS.VIRTUAL_BALANCE, USERS.AVATAR, ";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s" +
                    " FROM USERS" +
                    " INNER JOIN POSITIONS" +
                    " ON USERS.POSITION_ID = POSITIONS.ID";

    private static final String ID_FIELD = " USERS.ID, ";


    protected H2EmployeeDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public Employee findByCredentials(String login, String password) throws DaoException {
        String sql = "SELECT *" +
                " FROM USERS" +
                " inner join POSITIONS" +
                " on users.POSITION_ID = positions.id" +
                " where POSITION_ID != 11 and LOGIN = " + "'" + login + "'" + "and PASSWORD = " + "'" + password + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Employee employee = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                employee = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return employee;
    }

    @Override
    public Employee findById(int ID) throws DaoException {
        String sql = "select * from users where id = " + ID;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Employee employee = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                employee = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return employee;
    }

    @Override
    public String insert(Employee employee) throws DaoException {
        String status = "Employee do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(ADD_EMPLOYEE);
            prstm.setString(1, null);
            prstm.setString(2, employee.getLogin());
            prstm.setString(3, employee.getPassword());
            prstm.setString(4, employee.getFirstName());
            prstm.setString(5, employee.getLastName());
            prstm.setString(6, employee.getMiddleName());
            prstm.setString(7, employee.getAddress());
            prstm.setString(8, employee.getTelephone());
            prstm.setString(9, employee.getMobilephone());
            prstm.setString(10, employee.getIdentityCard());
            prstm.setString(11, employee.getWorkBook());
            prstm.setString(12, employee.getRNN());
            prstm.setString(13, employee.getSIK());
            prstm.setInt(14, employee.getRole().getId());
            prstm.setBigDecimal(15, employee.getVirtualBalance());
            prstm.setBigDecimal(16, null);
            prstm.execute();
            status = "Employee added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        return null;
    }

    @Override
    public String updateEntity(Employee employee) throws DaoException {
        String status = "Employee do not updated";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(UPDATE_EMPLOYEE);
            prstm.setInt(1, employee.getId());
            prstm.setString(2, employee.getLogin());
            prstm.setString(3, employee.getPassword());
            prstm.setString(4, employee.getFirstName());
            prstm.setString(5, employee.getLastName());
            prstm.setString(6, employee.getMiddleName());
            prstm.setString(7, employee.getAddress());
            prstm.setString(8, employee.getTelephone());
            prstm.setString(9, employee.getMobilephone());
            prstm.setString(10, employee.getIdentityCard());
            prstm.setString(11, employee.getWorkBook());
            prstm.setString(12, employee.getRNN());
            prstm.setString(13, employee.getSIK());
            prstm.setInt(14, employee.getRole().getId());
            prstm.setBigDecimal(15, employee.getVirtualBalance());
            prstm.setInt(16, employee.getAvatar().intValue());
            prstm.setInt(17, employee.getId());
            prstm.executeUpdate();
            status = "Employee updated successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public Employee getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        PositionDao positionDao = daoManager.getPositionDao();
        H2DiscountDao discountDao = daoManager.getDiscountDao();

        Employee employee = new Employee();
        try {
            employee.setId(resultSet.getInt("id"));
            employee.setAvatar(resultSet.getInt("avatar"));
            employee.setLogin(resultSet.getString("login"));
            employee.setPassword(resultSet.getString("password"));
            employee.setFirstName(resultSet.getString("firstname"));
            employee.setLastName(resultSet.getString("lastname"));
            employee.setMiddleName(resultSet.getString("middlename"));
            employee.setAddress(resultSet.getString("address"));
            employee.setTelephone(resultSet.getString("telephone"));
            employee.setMobilephone(resultSet.getString("mobilephone"));
            employee.setIdentityCard(resultSet.getString("identitycard"));
            employee.setWorkBook(resultSet.getString("workbook"));
            employee.setRNN(resultSet.getString("rnn"));
            employee.setSIK(resultSet.getString("sik"));
            employee.setRole(positionDao.findById(resultSet.getInt("position_id")));
            employee.setVirtualBalance(new BigDecimal(resultSet.getInt("virtual_balance")));
            employee.setDiscount(discountDao.findById(resultSet.getInt("discount")));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return employee;
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
}
