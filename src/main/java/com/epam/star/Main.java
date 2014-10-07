package com.epam.star;

import com.epam.star.dao.util.UtilDao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class Main {
    private static final UtilDao utilDao = new UtilDao();

    public static void main(String[] args) throws SQLException, ParseException {
        List result = parseSearchStaring("39 2014-09-14 Water 20L");

        for (Object o : result) {
//            if (entry.getKey().equals("Integer"))
//                System.out.println(entry.getKey() + "=>" + entry.getValue());
//            if (entry.getKey().equals("Date"))
//                System.out.println(entry.getKey() + "=>" + entry.getValue());
//            if (entry.getKey().equals("String"))
//                System.out.println(entry.getKey() + "=>" + entry.getValue());

            System.out.println(o.getClass().getSimpleName() + "=>" + o.toString());
        }
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
