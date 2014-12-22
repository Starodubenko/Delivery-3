package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Client;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

@MappedAction("POST/saveClientData")
public class AjaxSaveClientDataAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSaveClientDataAction.class);
    ActionResult jsonn = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        Client client = null;
        try {
            ClientDao clientDao = daoManager.getClientDao();
            client = createClient(request, daoManager);
            clientDao.updateEntity(client);

            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }

        JSONObject json = new JSONObject();
        if (client != null) json.put("errorMessage", "Client saved successful !");
        else json.put("errorMessage", "During saving the client an error has occurred !");

        request.setAttribute("json", json);

        if (json.length() > 0) return jsonn;
        return null;
    }

    private Client createClient(HttpServletRequest request, DaoManager daoManager) throws ActionException {

        Client client = (Client)request.getSession().getAttribute("client");

        if (client != null) {
            client.setFirstName(request.getParameter("first-name"));
            client.setMiddleName(request.getParameter("middle-name"));
            client.setLastName(request.getParameter("last-name"));
            client.setAddress(request.getParameter("address"));
            client.setTelephone(request.getParameter("telephone"));
            client.setMobilephone(request.getParameter("mobilephone"));
        } else {
            LOGGER.error("Client does not exist: {}", client);
        }

        return client;
    }
}
