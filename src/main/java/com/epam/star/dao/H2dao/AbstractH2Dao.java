package com.epam.star.dao.H2dao;

import com.epam.star.dao.util.PaginatedList;
import com.epam.star.entity.AbstractEntity;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Map;

public abstract class AbstractH2Dao<T extends AbstractEntity> {
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

    public int getRecordsCount() {
        int result = 0;

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT COUNT(*) FROM " + getTableName());
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

    public PaginatedList<T> findRange(int firstRow, int rowsCount, Map<String, String> fieldsMap) {
        int count = getRecordsCount();

        PaginatedList<T> result;

        String findByParameters = getFindByParameters();

        String conditionsForFindEntity = createQueryString(fieldsMap);

        String query = String.format(findByParameters, conditionsForFindEntity);

        PreparedStatement prstm = null;//todo try-with-resources
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(query);

            int prstmIndex = 0;

//            Identification obtained data, for setting it to PreparedStatement
            for (Map.Entry<String, String> entry : fieldsMap.entrySet()) {//todo create separate method
                String dynamicalString = String.valueOf(fieldsMap.get(entry.getKey()));
                try {
                    if (dynamicalString != null & dynamicalString != "") {
                        int num = Integer.parseInt(dynamicalString);
                        prstmIndex++;
                        prstm.setInt(prstmIndex, num);
                    }
                } catch (Exception e) {
                    try {
                        Date date = new Date(new SimpleDateFormat("yy-MM-dd").parse(dynamicalString).getTime());
                        prstmIndex++;
                        prstm.setDate(prstmIndex, date);
                    } catch (Exception e1) {
                        prstmIndex++;
                        prstm.setString(prstmIndex, dynamicalString);
                    }
                }
            }

            prstmIndex++;
            prstm.setInt(prstmIndex, rowsCount);
            prstmIndex++;
            prstm.setInt(prstmIndex, firstRow);

            result = new PaginatedList<>();

            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }

            result.setTotalRowsCount(count);
            result.setPageNumber(firstRow);
            result.setRowsPerPage(rowsCount);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

//    public PaginatedList<T> findRange(int firstRow, int rowsCount, List list) {
//        int count = getRecordsCount();
//
//        PaginatedList<T> result;
//
//        String findByParameters = getFindByParameters();
//
//        String conditionsForFindEntity = createQueryString(fieldsMap);
//
//        String query = String.format(findByParameters, conditionsForFindEntity);
//
//        PreparedStatement prstm = null;//todo try-with-resources
//        ResultSet resultSet = null;
//        try {
//            prstm = conn.prepareStatement(query);
//
//            int prstmIndex = 0;
//
////            Identification obtained data, for setting it to PreparedStatement
//            for (Map.Entry<String, String> entry : fieldsMap.entrySet()) {//todo create separate method
//                String dynamicalString = String.valueOf(fieldsMap.get(entry.getKey()));
//                try {
//                    if (dynamicalString != null & dynamicalString != "") {
//                        int num = Integer.parseInt(dynamicalString);
//                        prstmIndex++;
//                        prstm.setInt(prstmIndex, num);
//                    }
//                } catch (Exception e) {
//                    try {
//                        Date date = new Date(new SimpleDateFormat("yy-MM-dd").parse(dynamicalString).getTime());
//                        prstmIndex++;
//                        prstm.setDate(prstmIndex, date);
//                    } catch (Exception e1) {
//                        prstmIndex++;
//                        prstm.setString(prstmIndex, dynamicalString);
//                    }
//                }
//            }
//
//            prstmIndex++;
//            prstm.setInt(prstmIndex, rowsCount);
//            prstmIndex++;
//            prstm.setInt(prstmIndex, firstRow);
//
//            result = new PaginatedList<>();
//
//            resultSet = prstm.executeQuery();
//            while (resultSet.next()) {
//                result.add(getEntityFromResultSet(resultSet));
//            }
//
//            result.setTotalRowsCount(count);
//            result.setPageNumber(firstRow);
//            result.setRowsPerPage(rowsCount);
//
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        } finally {
//            closeStatement(prstm, resultSet);
//        }
//        return result;
//    }

    protected abstract String getFindByParameters();

    public abstract Map<String, String> getParametersMap();

    public abstract String getTableName();

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

    public String createQueryString(Map<String, String> fields) {

        if (fields.size() <= 0) return "";
        StringBuilder query = new StringBuilder(" where 1=1");
//todo check map parameters count
        String fieldValue;
        Map<String, String> fieldsMap = getParametersMap();
        for (Map.Entry<String, String> field : fields.entrySet()) {
            fieldValue = fieldsMap.get(field.getKey());
            query.append(" and ");
            query.append(fieldValue);
        }
        return query.toString();
    }
}
