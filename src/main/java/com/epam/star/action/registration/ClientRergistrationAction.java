package com.epam.star.action.registration;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Client;
import com.epam.star.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;

@MappedAction("POST/ClientRegistration")
public class ClientRergistrationAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRergistrationAction.class);

    private ActionResult login = new ActionResult("welcome", true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        Validator validator = new Validator();
        Client client = createClient(request, validator);

        if (client != null) {

            daoManager.beginTransaction();
            try {
                PositionDao positionDao = daoManager.getPositionDao();
                ClientDao clientDao = daoManager.getClientDao();

                client.setRole(positionDao.findByPositionName("registration"));
                client.setVirtualBalance(new BigDecimal(0));
                clientDao.insert(client);
                daoManager.commit();
            } catch (Exception e) {
                daoManager.rollback();
            } finally {
                daoManager.closeConnection();
            }
        }
        return login;
    }

    private Client createClient(HttpServletRequest request, Validator validator) {
        Client client = new Client();

        boolean login = validator.checkUserName(request.getParameter("login"));
        boolean password = validator.checkUserPassword(request.getParameter("password"));
        boolean firstname = validator.checkUserFirsName(request.getParameter("firstname"));
        boolean lastname = validator.checkUserLastName(request.getParameter("lastname"));
        boolean middlename = validator.checkUserMiddleName(request.getParameter("middlename"));
        boolean address = validator.checkUserAddress(request.getParameter("address"));
        boolean telephone = validator.checkUserTelephone(request.getParameter("telephone"));
        boolean mobilephone = validator.checkUserPMobilephone(request.getParameter("mobilephone"));

        if (validator.isValide()) {

            client.setLogin(request.getParameter("login"));
            client.setPassword(request.getParameter("password"));
            client.setFirstName(request.getParameter("firstname"));
            client.setLastName(request.getParameter("lastname"));
            client.setMiddleName(request.getParameter("middlename"));
            client.setAddress(request.getParameter("address"));
            client.setTelephone(request.getParameter("telephone"));
            client.setMobilephone(request.getParameter("mobilephone"));
            return client;
        } else return null;
    }
}
