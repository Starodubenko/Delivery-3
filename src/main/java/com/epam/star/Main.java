package com.epam.star;


import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao;
import com.epam.star.dao.util.Searcher;
import com.epam.star.dao.util.UtilDao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

public class Main {
    private static final UtilDao utilDao = new UtilDao();
    private static final Searcher SEARCHER = new Searcher();

    public static void main(String[] args) throws SQLException, ParseException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2OrderDao orderDao = daoManager.getOrderDao();

        Map map = SEARCHER.find("25.08.2014 26-08-2014 qwerty 13 13qwert qert12", orderDao, 10, 0);

        System.out.println(map);

        daoManager.closeConnection();

        System.out.println(Integer.parseInt("870012365"));
        System.out.println(Long.parseLong("87001236546"));
    }

}
