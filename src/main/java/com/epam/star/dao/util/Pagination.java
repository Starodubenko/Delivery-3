package com.epam.star.dao.util;

import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoException;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.util.PropertiesManager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pagination<T extends AbstractEntity, E extends AbstractH2Dao> {
    private static PropertiesManager jdbcProperties;

    public static final int DEFAULT_PAGE_NUMBER;
    public static final int DEFAULT_ROWS_COUNT;

    private static Map<String, String> fieldsMap = new HashMap<>();

    private static final UtilDao utilDao = new UtilDao();

    static {
        try {
            jdbcProperties = new PropertiesManager("pagination.properties");
        } catch (IOException e) {
            throw new DaoException(e);
        }
        DEFAULT_PAGE_NUMBER = jdbcProperties.getIntProperty("default.page.number");
        DEFAULT_ROWS_COUNT = jdbcProperties.getIntProperty("default.rows.count");

        fieldsMap.put("order-id", " orders.id = ?");
        fieldsMap.put("order-date", " orders.order_date = ?");
        fieldsMap.put("order-goods-name", " goods.goods_name = ?");
        fieldsMap.put("order-cost", " orders.order_cost = ?");
        fieldsMap.put("delivery-date", " orders.delivery_date = ?");
        fieldsMap.put("delivery-time", " period.period = ?");
        fieldsMap.put("order-addInfo", " orders.additional_info = ?");
        fieldsMap.put("order-status", " status.status_name = ?");
    }

    public void paginationEntity(HttpServletRequest request, E genericDao, String targetName) throws DaoException {

        int rowsCount = DEFAULT_ROWS_COUNT;
        int pageNumber = DEFAULT_PAGE_NUMBER;
        if (utilDao.getIntValue(targetName + "page", request) != null)
            pageNumber = utilDao.getIntValue(targetName + "page", request);
        if (utilDao.getIntValue(targetName + "rows", request) != null)
            rowsCount = utilDao.getIntValue(targetName + "rows", request);
        int firstRow = pageNumber * rowsCount - rowsCount;

        Map<String, String> queryMap = getQueryMap(request, genericDao);
        PaginatedList<T> paginatedList = genericDao.findRange(firstRow, rowsCount, queryMap);

        request.setAttribute(targetName + "PaginatedList", paginatedList);
    }

    private Map<String, String> getQueryMap(HttpServletRequest request, E genericDao) {

        Map<String, String> result = new HashMap<>();
        Map<String, String> parsqlParametersMap = genericDao.getParametersMap();

        Map<String, String[]> parameterMap = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (parsqlParametersMap.get(entry.getKey()) != null)
                if (entry.getValue() != null && entry.getValue()[0] != "")
                    result.put(entry.getKey(), entry.getValue()[0]);
        }
        return result;
    }

    private static List parseSearchStaring(String searchString) {
        List parametersValue = new ArrayList<>();
        String[] value = searchString.split(" ");

        Object val = null;

        for (String s : value) {
            val = utilDao.getIntValue(s);
            if (val == null) val = utilDao.getDateValue(s);
            if (val == null) val = s;

            parametersValue.add(val);
        }
        return parametersValue;
    }
}
