package com.epam.star.dao.H2dao;

import com.epam.star.dao.ImageDao;
import com.epam.star.entity.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2ImageDao extends AbstractH2Dao implements ImageDao {
    private static final String ID = "id";
    private static final String FILENAME = "filename";
    private static final String CONTENT = "content";
    private static final String FIND_BY_FILENAME = "SELECT * FROM image WHERE filename = ?";
    private static final String GET_LAST_ELEMENT = "SELECT * FROM image ORDER BY id DESC LIMIT 1";
    private static final String FIND_BY_ID = "SELECT * FROM image WHERE id = ?";
    private static final String INSERT = "INSERT INTO image VALUES (?, ?, ?)";
    private static final String DELETE = "delete from image where ID = ?";

    private static final String NECESSARY_COLUMNS =
            " IMAGE.ID, IMAGE.FILENAME, IMAGE.CONTENT";

    private static final String ADDITIONAL_COLUMNS =
            "";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM IMAGE";

    private static final String ID_FIELD = " IMAGE.ID, ";


    protected H2ImageDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
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
    public Image getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        Image image = null;
        try {
            image = new Image();
            image.setId(resultSet.getInt(ID));
            image.setFilename(resultSet.getString(FILENAME));
            image.setContent(resultSet.getBytes(CONTENT));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return image;
    }

    @Override
    public Image findByFilename(String filename) throws DaoException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_FILENAME)) {
            preparedStatement.setString(1, filename);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getEntityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    public Image findLastAddedImage() throws DaoException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(GET_LAST_ELEMENT)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getEntityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Image findById(int ID) {
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Image image = null;
        try {
            prstm = conn.prepareStatement(FIND_BY_ID);
            prstm.setInt(1, ID);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                image = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return image;
    }

    @Override
    public String insert(Image entity) {
        String status = "Image do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(INSERT);
            prstm.setString(1, null);
            prstm.setString(2, entity.getFilename());
            prstm.setBytes(3, entity.getContent());
            prstm.execute();
            status = "Image added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String deleteEntity(int ID) {
        String status = "Image do not deleted";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(DELETE);
            prstm.setString(1, String.valueOf(ID));
            prstm.execute();
            status = "Image successfully deleted";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String updateEntity(Image entity) {
        return null;
    }
}
