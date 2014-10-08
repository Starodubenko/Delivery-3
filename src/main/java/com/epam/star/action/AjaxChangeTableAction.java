package com.epam.star.action;

import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/changeTableAction")
public class AjaxChangeTableAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxChangeTableAction.class);
    ActionResult genericTable = new ActionResult("ajaxGenericTable");
    private static final DaoManager daoManager = DaoFactory.getInstance().getDaoManager();


    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        String name = request.getParameter("tableName");
        System.out.println(name);
        request.setAttribute("entityName", name);
        return genericTable;
    }
}
