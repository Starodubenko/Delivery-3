package com.epam.star.dao.util;

import com.epam.star.dao.H2dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilDao {
    public Integer getIntValue(String fieldName, HttpServletRequest request) {
        String pageString = request.getParameter(fieldName);
        if (pageString != null && pageString.matches("\\d+")) return Integer.valueOf(pageString);
        return null;
    }

    public Integer getIntValue(String value) {
        if (value != null && value.matches("\\d+")) return Integer.valueOf(value);
        return null;
    }

    public Date getDateValue(String fieldName, HttpServletRequest request) {
        String dateString = request.getParameter(fieldName);
        if (dateString != null && dateString.matches("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"))
            try {
                return new Date(new SimpleDateFormat("yy-MM-dd").parse(dateString).getTime());
            } catch (ParseException e) {
                throw new DaoException(e);
            }
        return null;
    }

    public Date getDateValue(String dateString) {
        if (dateString != null && dateString.matches("([0-2]\\d|3[01])\\.(0\\d|1[012])\\.(\\d{4})"))
            try {
                return new Date(new SimpleDateFormat("dd.MM.yyyy").parse(dateString).getTime());
            } catch (ParseException e) {
                throw new DaoException(e);
            }
        return null;
    }
}
