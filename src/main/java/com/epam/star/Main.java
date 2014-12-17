package com.epam.star;

import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.util.Searcher;
import com.epam.star.dao.util.UtilDao;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    private static final UtilDao utilDao = new UtilDao();

    public static void main(String[] args) throws SQLException, ParseException {
        Searcher searcher = DaoFactory.getInstance().getSearcher();
        searcher.find("2014.08.25 qwerty 13 13qwert qert12","orders");
    }

}
