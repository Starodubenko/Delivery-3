package com.epam.star.dao.util;

import com.epam.star.dao.H2dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
        if (dateString != null && dateString.matches("([0-2]\\d|3[01])[\\.\\-](0\\d|1[012])[\\.\\-](\\d{4})"))
            try {
                return new Date(new SimpleDateFormat("dd.MM.yyyy").parse(dateString).getTime());
            } catch (ParseException e) {
                throw new DaoException(e);
            }
        return null;
    }

    public Date getDateValue(String dateString) {
        List<String> dateformats = new ArrayList<>();
        dateformats.add("dd.MM.yyyy");
        dateformats.add("dd-MM-yyyy");
        if (dateString != null && dateString.matches("([0-2]\\d|3[01])[\\.\\-](0\\d|1[012])[\\.\\-](\\d{4})"))
            for (String dateformat : dateformats) {
                try {
                    return new Date(new SimpleDateFormat(dateformat).parse(dateString).getTime());
                } catch (ParseException e) {
                    if(dateformat.equals(dateformats.get(dateformats.size()-1)))
                    throw new DaoException(e);
                }
            }
        return null;
    }

    public String getString(String fieldName, HttpServletRequest request) {
        return request.getParameter(fieldName);
    }

}
