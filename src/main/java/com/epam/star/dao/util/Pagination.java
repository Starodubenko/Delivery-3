package com.epam.star.dao.util;

import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoException;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.util.PropertiesManager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Pagination<T extends AbstractEntity, E extends AbstractH2Dao> {
    private static PropertiesManager jdbcProperties;

    public static final int DEFAULT_PAGE_NUMBER;
    public static final int DEFAULT_ROWS_COUNT;

    private static final UtilDao utilDao = new UtilDao();

    static {
        try {
            jdbcProperties = new PropertiesManager("pagination.properties");
        } catch (IOException e) {
            throw new DaoException(e);
        }
        DEFAULT_PAGE_NUMBER = jdbcProperties.getIntProperty("default.page.number");
        DEFAULT_ROWS_COUNT = jdbcProperties.getIntProperty("default.rows.count");
    }

    public PaginatedList<T> paginationEntity(HttpServletRequest request, E genericDao, String targetName) throws DaoException {

        int rowsCount = DEFAULT_ROWS_COUNT;
        int pageNumber = DEFAULT_PAGE_NUMBER;

        if (utilDao.getIntValue(targetName + "page", request) != null)
            pageNumber = utilDao.getIntValue(targetName + "page", request);
        if (utilDao.getIntValue(targetName + "rows", request) != null)
            rowsCount = utilDao.getIntValue(targetName + "rows", request);
        int firstRow = pageNumber * rowsCount - rowsCount;

        String orderStatus = request.getParameter("orderStatus");

        String searchString = utilDao.getString("searchString", request);
        return  genericDao.findRange(firstRow, rowsCount, pageNumber, genericDao, searchString);
    }
}
