package com.epam.star.dao.H2dao;

import com.epam.star.dao.util.PaginatedList;
import com.epam.star.dao.util.SearchResult;
import com.epam.star.dao.util.Searcher;
import com.epam.star.dao.util.UtilDao;
import com.epam.star.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractH2Dao<T extends AbstractEntity, E extends AbstractH2Dao> {
    private static final Searcher SEARCHER = new Searcher();
    protected static final UtilDao UTIL_DAO = new UtilDao();
    protected static final String LIMIT_OFFSET = " LIMIT ? OFFSET ? ";

    protected Connection conn;
    protected DaoManager daoManager;

    protected AbstractH2Dao(Connection conn, DaoManager daoManager) {
        this.conn = conn;
        this.daoManager = daoManager;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public DaoManager getDaoManager() {
        return daoManager;
    }

    public void setDaoManager(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PaginatedList<T> findRange(int firstRow, int rowsCount, int pageNumber,  E genericDao, String searchString) {

        PaginatedList<T> result = new PaginatedList<>();
        SearchResult searchResult = SEARCHER.find(searchString, genericDao, rowsCount, firstRow);
        Map<Integer, Integer> foundEntitiesProbabilityMap = searchResult.getFoundEntitiesMap();

        for (Integer id : foundEntitiesProbabilityMap.keySet()) {
            AbstractEntity entity = genericDao.findById(id.intValue());
            result.add((T) entity);
        }

        result.setTotalRowsCount(searchResult.getTotalFoundEntitiesCount());
        result.setPageNumber(pageNumber);
        result.setRowsPerPage(rowsCount);

        return result;
    }

    public abstract T findById(int i);

    public abstract String getFindByParameters(Boolean needAditionalColumns);
    public abstract String getFindByParametersWithoutColumns();
    public abstract String getNecessaryColumns();
    public abstract String getAdditionalColumns();
    public abstract String getIdField();
    public String getLimitOffset(){
        return LIMIT_OFFSET;
    }

    public abstract T getEntityFromResultSet(ResultSet resultSet) throws DaoException;

    protected void closeStatement(PreparedStatement prstm, ResultSet resultSet) {
        if (prstm != null) {
            try {
                prstm.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }
}
