package com.epam.star.action.login;

import com.epam.star.action.Action;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.EmployeeDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@MappedAction("POST/login")
public class LoginAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);

    private ActionResult loginn = new ActionResult("welcome");
    private ActionResult client = new ActionResult("client", true);
    private ActionResult dispatcher = new ActionResult("dispatcher", true);
    private ActionResult admin = new ActionResult("admin", true);
    private ActionResult director = new ActionResult("director", true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        EmployeeDao employeeDao = daoManager.getEmployeeDao();
        ClientDao clientDao = daoManager.getClientDao();

        String login = request.getParameter("authenticationLogin");
        String password = request.getParameter("authenticationPassword");
        Client user = clientDao.findByCredentials(login, password);
        if (user == null)
            user = employeeDao.findByCredentials(login, password);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        LOGGER.debug("Name and Surname obtained in the case, if authentication is successful : {}", user);

        if (user == null) {
            request.setAttribute("loginError", "login.incorrect.login.or.password");
            return loginn;
        }

        if (user.getRole().getPositionName().equalsIgnoreCase("admin")) return admin;

        if (user.getRole().getPositionName().equalsIgnoreCase("director")) return director;

        if (user.getRole().getPositionName().equalsIgnoreCase("dispatcher")) return dispatcher;

        daoManager.closeConnection();

        return client;
    }
}
