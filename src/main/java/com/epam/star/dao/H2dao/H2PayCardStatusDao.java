package com.epam.star.dao.H2dao;

import com.epam.star.dao.PayCardStatusDao;
import com.epam.star.entity.StatusPayCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class H2PayCardStatusDao extends AbstractH2Dao implements PayCardStatusDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String TABLE_NAME = "status_card";
    private static final String ADD_STATUS_PAY_CARD = "INSERT INTO status_card VALUES (?, ?)";
    private static final String DELETE_STATUS_PAY_CARD = "DELETE FROM status_card WHERE id = ?";
    private static final String UPDATE_STATUS_PAY_CARD = "UPDATE status_card SET id = ?, status_name = ? WHERE id = ?";

    private static Map<String, String> fieldsQueryMap = new HashMap<>();

    private static final String FIND_BY_PARAMETERS =
            " SELECT *" +
                    " FROM status_card" +
                    " %s LIMIT ? OFFSET ?";

    static {
        fieldsQueryMap.put("status-card-id", " status_card.id = ?");
        fieldsQueryMap.put("status-card-status-name", " status_card.status_name = ?");
    }

    protected H2PayCardStatusDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public StatusPayCard findByStatusName(String name) throws DaoException {
        String sql = "SELECT * FROM status_card WHERE status_name = " + "'" + name + "'";
        StatusPayCard statusPayCard = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                statusPayCard = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return statusPayCard;
    }

    @Override
    public StatusPayCard findById(int ID) throws DaoException {
        String sql = "SELECT * FROM status_card WHERE id = " + ID;
        StatusPayCard statusPayCard = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                statusPayCard = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return statusPayCard;
    }

    @Override
    public String insert(StatusPayCard statusPayCard) throws DaoException {
        String statuss = "StatusPayCard do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_STATUS_PAY_CARD);
            prstm.setString(1, null);
            prstm.setString(2, statusPayCard.getStatusName());
            prstm.execute();
            statuss = "StatusPayCard added successfully";
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
    public String updateEntity(StatusPayCard statusPayCard) throws DaoException {
        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(UPDATE_STATUS_PAY_CARD);
            prstm.setInt(1, statusPayCard.getId());
            prstm.setString(2, statusPayCard.getStatusName());
            prstm.setInt(3, statusPayCard.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return null;
    }

    @Override
    public StatusPayCard getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        StatusPayCard statusPayCard = new StatusPayCard();
        try {
            statusPayCard.setId(resultSet.getInt("id"));
            statusPayCard.setStatusName(resultSet.getString("status_name"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return statusPayCard;
    }

//    @Override
//    public int getRecordsCount() {
//        int result = 0;
//
//        PreparedStatement prstm = null;
//        ResultSet resultSet = null;
//        try {
//            prstm = conn.prepareStatement("SELECT COUNT(*) FROM status_card");
//            resultSet = prstm.executeQuery();
//            while (resultSet.next())
//                result = resultSet.getInt("count(*)");
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        } finally {
//            closeStatement(prstm, resultSet);
//        }
//        return result;
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
