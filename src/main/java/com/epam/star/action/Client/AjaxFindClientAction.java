package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import com.epam.star.dao.util.PaginatedList;
import com.epam.star.dao.util.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("POST/findClient")
public class AjaxFindClientAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxFindClientAction.class);
    private ActionResult order = new ActionResult("clientsBlock");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ClientDao clientDao = daoManager.getClientDao();

        Pagination pagination = new Pagination();
        PaginatedList clients = pagination.paginationEntity(request, clientDao, "clients");
        request.setAttribute("clientsPaginatedList",clients);

        daoManager.closeConnection();

        return order;
    }
}
