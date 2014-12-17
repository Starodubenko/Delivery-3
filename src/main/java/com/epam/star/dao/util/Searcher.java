package com.epam.star.dao.util;

import com.epam.star.dao.H2dao.DaoException;
import com.epam.star.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Searcher {
    protected Connection conn;

    public Searcher(Connection conn) {
        this.conn = conn;
    }

    private Map<Integer, Integer> foundRecords = new HashMap<>();

    private static final UtilDao utilDao = new UtilDao();
    private static final String TABLE_WITH_TYPE = "select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = 'ORDERS' and Data_Type = %s";
    private static final String COLUMNS_WITH_DEFINED_TYPE = "select orders.id, %s from ";

    private static Map<String, Integer> typesMap = new HashMap<>();

    static {
        typesMap.put("Date", 91);
        typesMap.put("Integer", 4);
        typesMap.put("String", 12);
    }


    private List<Object> parseSearchString(String searchString){
        List<Object> findingValues = new ArrayList<>();
        String[] value = searchString.split(" ");

        Object val = null;

        for (String s : value) {
            val = utilDao.getIntValue(s);
            if (val == null) val = utilDao.getDateValue(s);
            if (val == null) val = s;

            findingValues.add(val);
        }
        return findingValues;
    }

    private List<String> getTableNamesFromDataBase(String query){

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        List<String> tableNames = new ArrayList<>();
//        String tableNames = null;
        try {
            prstm = conn.prepareStatement(query);
            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                tableNames.add(getTableNamesFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }

        return tableNames;
    }

    private String getTableNamesString(List<String> query){

        StringBuilder tableNames = new StringBuilder();

        for (String name : query) {
            tableNames.append(", ");
            tableNames.append(name);
        }

        return tableNames.toString().substring(tableNames.toString().indexOf(" ")+1);
    }

    private String getTableNamesFromResultSet(ResultSet resultSet) {
        try {
            return resultSet.getString("COLUMN_NAME");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

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

    public List<Order> find(String findingString, String tableName){

        List<Order> orders = new ArrayList<>();

        List<Object> values = parseSearchString(findingString);

        String queryString = "";
        for (Object value : values) {
            String aClass = value.getClass().getSimpleName();
            Integer classIdInSQL = typesMap.get(aClass);

            queryString = String.format(TABLE_WITH_TYPE, classIdInSQL);
            List<String> tableNames = getTableNamesFromDataBase(queryString);
            String tableNamesString = getTableNamesString(tableNames);
            queryString = String.format(COLUMNS_WITH_DEFINED_TYPE, tableNamesString);

            queryString = String.format(queryString+"%s",tableName);


            StringBuilder afterWherePartString = new StringBuilder();
            for (String name : tableNames) {
                afterWherePartString.append(" or ");
                afterWherePartString.append(name);
                afterWherePartString.append(" like '" + value + "'");
            }


            queryString = String.format(queryString+"%s"," where " + afterWherePartString.toString().substring(afterWherePartString.toString().indexOf("or")+3));

            PreparedStatement prstm = null;
            ResultSet resultSet = null;
            try {
                prstm = conn.prepareStatement(queryString);
                resultSet = prstm.executeQuery();
//                while (resultSet.next()) {
//                    tableNames.append(", ");
//                    tableNames.append(getTableNamesFromResultSet(resultSet));
//                }
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                closeStatement(prstm, resultSet);
            }
        }

        return orders;
    }
}
