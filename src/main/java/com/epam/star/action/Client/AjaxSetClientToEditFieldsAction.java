package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/SetClientToEditFields")
public class AjaxSetClientToEditFieldsAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSetClientToEditFieldsAction.class);

    private ActionResult editClientsRow = new ActionResult("ajaxClientTableRow");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        int id = Integer.parseInt(request.getParameter("id"));

        H2ClientDao clientDao = daoManager.getClientDao();
        Client client = clientDao.findById(id);

        request.getSession().setAttribute("client", client);

        daoManager.closeConnection();
        return editClientsRow;
    }
}
