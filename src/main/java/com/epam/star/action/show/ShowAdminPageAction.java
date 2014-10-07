package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/admin")
public class ShowAdminPageAction implements Action {
    private ActionResult admin = new ActionResult("admin");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();


        daoManager.closeConnection();
        return admin;
    }
}
