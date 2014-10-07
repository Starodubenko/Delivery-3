package com.epam.star.dao.H2dao;

import com.epam.star.dao.StatusDao;
import com.epam.star.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class H2StatusDao extends AbstractH2Dao implements StatusDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String TABLE_NAME = "status";
    private static final String ADD_STATUS = "INSERT INTO  Status VALUES (?, ?)";
    private static final String DELETE_STATUS = "DELETE FROM status WHERE id = ?";
    private static final String UPDATE_STATUS = "UPDATE status SET id = ?, status_name = ? WHERE id = ?";

    private static Map<String, String> fieldsQueryMap = new HashMap<>();

    private static final String FIND_BY_PARAMETERS =
            " SELECT *" +
                    " FROM status" +
                    " %s LIMIT ? OFFSET ?";

    static {
        fieldsQueryMap.put("status-id", " status.id = ?");
        fieldsQueryMap.put("status-name", " status.status_name = ?");
    }

    protected H2StatusDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public Status findByStatusName(String name) throws DaoException {
        String sql = "SELECT * FROM status WHERE status_name = " + "'" + name + "'";
        Status status = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                status = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return status;
    }

    @Override
    public Status findById(int ID) throws DaoException {
        String sql = "SELECT * FROM status WHERE id = " + ID;
        Status status = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                status = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return status;
    }

    @Override
    public String insert(Status status) throws DaoException {
        String statuss = "Status do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_STATUS);
            prstm.setString(1, null);
            prstm.setString(2, status.getStatusName());
            prstm.execute();
            statuss = "Status added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return statuss;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        return null;
    }

    @Override
    public String updateEntity(Status status) throws DaoException {
        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(UPDATE_STATUS);
            prstm.setInt(1, status.getId());
            prstm.setString(2, status.getStatusName());
            prstm.setInt(3, status.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return null;
    }

    @Override
    public Status getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Status status = new Status();
        try {
            status.setId(resultSet.getInt("id"));
            status.setStatusName(resultSet.getString("status_name"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return status;
    }
//
//    @Override
//    public int getRecordsCount() {
//        return 0;
//    }

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
}
