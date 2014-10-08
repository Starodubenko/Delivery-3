package com.epam.star;

import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.util.UtilDao;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    private static final UtilDao utilDao = new UtilDao();

    public static void main(String[] args) throws SQLException, ParseException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        AbstractH2Dao clients = daoManager.getDao("Clients");
    }

}
