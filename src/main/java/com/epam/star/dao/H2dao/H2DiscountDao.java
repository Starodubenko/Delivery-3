package com.epam.star.dao.H2dao;

import com.epam.star.dao.DiscountDao;
import com.epam.star.entity.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2DiscountDao extends AbstractH2Dao implements DiscountDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_STATUS = "INSERT INTO DISCOUNT VALUES (?, ?, ?)";
    private static final String DELETE_STATUS = "DELETE FROM DISCOUNT WHERE ID = ?";
    private static final String UPDATE_STATUS = "UPDATE DISCOUNT SET DISCOUNT.ID = ?, DISCOUNT.NAME = ?," +
            " DISCOUNT.DISCOUNT_PERCENTAGE = ? WHERE DISCOUNT.ID = ?";

    private static final String NECESSARY_COLUMNS =
            " DISCOUNT.ID, DISCOUNT.NAME, DISCOUNT.PERCENTAGE ";

    private static final String ADDITIONAL_COLUMNS =
            "";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM DISCOUNT";

    private static final String ID_FIELD = " DISCOUNT.ID, ";

    protected H2DiscountDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    public Discount findByName(String name) throws DaoException {
        String sql = "SELECT * FROM DISCOUNT WHERE DISCOUNT.NAME = " + "'" + name + "'";
        Discount discount = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                discount = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return discount;
    }

    @Override
    public Discount findById(int ID) throws DaoException {
        String sql = "SELECT * FROM DISCOUNT WHERE ID = " + ID;
        Discount discount = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                discount = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return discount;
    }

    @Override
    public String insert(Discount discount) throws DaoException {
        String message = "Status do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_STATUS);
            prstm.setString(1, null);
            prstm.setString(2, discount.getName());
            prstm.setInt(3, discount.getPercentage());
            prstm.execute();
            message = "Status added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return message;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        return null;
    }


    @Override
    public String updateEntity(Discount status) throws DaoException {
        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(UPDATE_STATUS);
            prstm.setInt(1, status.getId());
            prstm.setString(2, status.getName());
            prstm.setInt(3, status.getPercentage());
            prstm.setInt(4, status.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return null;
    }

    @Override
    public Discount getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Discount discount = new Discount();
        try {
            discount.setId(resultSet.getInt("id"));
            discount.setName(resultSet.getString("name"));
            discount.setPercentage(resultSet.getInt("discount_percentage"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return discount;
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
