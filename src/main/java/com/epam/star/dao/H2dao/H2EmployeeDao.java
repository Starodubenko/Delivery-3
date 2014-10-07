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
import java.util.HashMap;
import java.util.Map;

public class H2EmployeeDao extends AbstractH2Dao implements EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String TABLE_NAME = "users";
    private static final String ADD_EMPLOYEE = "INSERT INTO  USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_EMPLOYEE = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_EMPLOYEE =
            " UPDATE users SET id = ?, login = ?, password = ?, firstname = ?,  lastname = ?, middlename = ?," +
                    " address = ?, telephone = ?, mobilephone = ?, identitycard = ?, workbook = ?, rnn = ?, sik = ?," +
                    " position_id = ?, virtual_balance = ? WHERE id = ?";

    private static final String FIND_BY_PARAMETERS =
            " SELECT *" +
                    " FROM users" +
                    " inner join positions" +
                    " on users.position_id = positions.id" +
                    " %s LIMIT ? OFFSET ?";
    private static Map<String, String> fieldsQueryMap = new HashMap<>();

    static {
        fieldsQueryMap.put("employee-id", " users.id = ?");
        fieldsQueryMap.put("employee-login", " users.login = ?");
        fieldsQueryMap.put("employee-password", " users.password = ?");
        fieldsQueryMap.put("employee-first-name", " users.firstname = ?");
        fieldsQueryMap.put("employee-middle-name", " users.middlename = ?");
        fieldsQueryMap.put("employee-last-name", " users.lastname = ?");
        fieldsQueryMap.put("employee-address", " users.address = ?");
        fieldsQueryMap.put("employee-telephone", " users.telephone = ?");
        fieldsQueryMap.put("employee-mobilephone", " users.mobilephone = ?");
        fieldsQueryMap.put("employee-identitycard", " users.identitycard = ?");
        fieldsQueryMap.put("employee-workbook", " users.workbook = ?");
        fieldsQueryMap.put("employee-rnn", " users.rnn = ?");
        fieldsQueryMap.put("employee-sik", " users.sik = ?");
        fieldsQueryMap.put("employee-position_id", " users.position_id = ?");
        fieldsQueryMap.put("employee-virtual_balance", " users.virtual_balance = ?");
    }

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
        return null;
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
            prstm.setInt(16, employee.getId());
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

        Employee employee = new Employee();
        try {
            employee.setId(resultSet.getInt("id"));
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
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return employee;
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
    public int getRecordsCount() {
        int result = 0;

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT COUNT(*) FROM users where position_id != 11");
            resultSet = prstm.executeQuery();
            while (resultSet.next())
                result = resultSet.getInt("count(*)");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    protected String getFindByParameters() {
        return FIND_BY_PARAMETERS;
    }
}
