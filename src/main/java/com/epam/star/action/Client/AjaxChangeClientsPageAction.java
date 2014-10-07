package com.epam.star.action.Client;

import com.epam.star.action.*;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import com.epam.star.dao.util.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/ajaxChangeClientsPage")
public class AjaxChangeClientsPageAction implements Action {
    private ActionResult dispatcher = new ActionResult("ajaxClientsTable");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ClientDao clientDao = daoManager.getClientDao();

        Pagination pagination = new Pagination();
        pagination.paginationEntity(request, clientDao, "clients");

        daoManager.closeConnection();

        return dispatcher;
    }
}
