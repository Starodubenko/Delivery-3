package com.epam.star.dao.H2dao;

import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class H2PositionDao extends AbstractH2Dao implements PositionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2PositionDao.class);
    private static final String TABLE_NAME = "positions";
    private static final String ADD_POSITION = "INSERT INTO  positions VALUES (?, ?)";
    private static final String DELETE_POSITION = "DELETE FROM positions WHERE id = ?";
    private static final String UPDATE_PERIOD = "UPDATE position SET id = ?, position_name = ? WHERE id = ?";

    private static Map<String, String> fieldsQueryMap = new HashMap<>();

    private static final String FIND_BY_PARAMETERS =
            " SELECT *" +
                    " FROM position" +
                    " %s LIMIT ? OFFSET ?";

    static {
        fieldsQueryMap.put("position-id", " position.id = ?");
        fieldsQueryMap.put("position-name", " position.position_name = ?");
    }

    protected H2PositionDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public Position findByPositionName(String name) throws DaoException {
        String sql = "SELECT * FROM positions WHERE position_name = " + "'" + name + "'";
        Position position = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                position = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return position;
    }

    @Override
    public Position findById(int ID) throws DaoException {
        String sql = "SELECT * FROM positions WHERE id = " + ID;
        Position position = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                position = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return position;
    }

    @Override
    public String insert(Position position) throws DaoException {
        String statuss = "Period do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_POSITION);
            prstm.setString(1, null);
            prstm.setString(2, position.getPositionName());
            prstm.execute();
            statuss = "Period added successfully";
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
    public String updateEntity(Position position) throws DaoException {
        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(UPDATE_PERIOD);
            prstm.setInt(1, position.getId());
            prstm.setString(2, position.getPositionName());
            prstm.setInt(3, position.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return null;
    }

    @Override
    public Position getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Position position = new Position();
        try {
            position.setId(resultSet.getInt("id"));
            position.setPositionName(resultSet.getString("position_name"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return position;
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
}
