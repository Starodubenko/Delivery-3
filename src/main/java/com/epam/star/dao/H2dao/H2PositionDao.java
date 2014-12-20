package com.epam.star.dao.H2dao;

import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2PositionDao extends AbstractH2Dao implements PositionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2PositionDao.class);
    private static final String ADD_POSITION = "INSERT INTO  POSITIONS VALUES (?, ?)";
    private static final String DELETE_POSITION = "DELETE FROM POSITIONS WHERE ID = ?";
    private static final String UPDATE_PERIOD = "UPDATE position SET ID = ?, POSITION_NAME = ? WHERE ID = ?";

    private static final String NECESSARY_COLUMNS =
            " POSITIONS.ID, POSITIONS.POSITION_NAME ";

    private static final String ADDITIONAL_COLUMNS =
            "";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM POSITIONS";

    private static final String ID_FIELD = " POSITIONS.ID, ";


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
