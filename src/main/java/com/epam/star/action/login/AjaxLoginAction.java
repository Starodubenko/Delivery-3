package com.epam.star.action.login;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.EmployeeDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Client;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@MappedAction("POST/ajaxLogin")
public class AjaxLoginAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxLoginAction.class);

    private ActionResult result = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        EmployeeDao employeeDao = daoManager.getEmployeeDao();
        ClientDao clientDao = daoManager.getClientDao();
        PositionDao positionDao = daoManager.getPositionDao();

        String login = request.getParameter("authenticationLogin");
        String password = request.getParameter("authenticationPassword");
        Client user = clientDao.findByCredentials(login, password);
        if (user == null)
            user = employeeDao.findByCredentials(login, password);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        LOGGER.debug("Name and Surname obtained in the case, if authentication is successful : {}", user);

        JSONObject json = new JSONObject();
        if (user != null) {
            if (user.getRole().equals(positionDao.findByPositionName("Client"))) json.put("roleView", "client");
            if (user.getRole().equals(positionDao.findByPositionName("Dispatcher"))) json.put("roleView", "dispatcher");
            if (user.getRole().equals(positionDao.findByPositionName("Admin"))) json.put("roleView", "admin");
        } else {
            json.put("errorMessage", "Login or password was introduced with error");
        }

        request.setAttribute("json", json);

        daoManager.closeConnection();
        return result;
    }
}
