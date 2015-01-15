package com.epam.star.action.registration;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2DiscountDao;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Client;
import com.epam.star.util.Validator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@MappedAction("POST/ajaxClientRegistration")
public class AjaxClientRegistration implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxClientRegistration.class);
    private ActionResult result = new ActionResult("json");
    private ActionResult success = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        JSONObject jsonObject = new JSONObject();
        Validator validator = new Validator();
        Client client = createClient(request, validator, jsonObject);

        if (client != null) {
            request.setAttribute("message", "registration.successful");

            daoManager.beginTransaction();
            try {
                PositionDao positionDao = daoManager.getPositionDao();
                ClientDao clientDao = daoManager.getClientDao();
                H2DiscountDao discountDao = daoManager.getDiscountDao();

                client.setRole(positionDao.findByPositionName("Client"));
                client.setVirtualBalance(new BigDecimal(0));
                client.setAvatar(0);
                client.setDiscount(discountDao.findById(0));
                clientDao.insert(client);
                LOGGER.info("Client created successful, {}", client);
                daoManager.commit();
            } catch (Exception e) {
                daoManager.rollback();
                request.setAttribute("message", "login.already.occupied");
            } finally {
                daoManager.closeConnection();
            }
            return success;
        } else LOGGER.info("Creation of a client failed, {}", client);
        return result;
    }

    private Client createClient(HttpServletRequest request, Validator validator, JSONObject jsonObject) {
        Client client = new Client();

        boolean login = validator.checkUserName(request.getParameter("login"));
        boolean password = validator.checkUserPassword(request.getParameter("password"));
        boolean firstname = validator.checkUserFirsName(request.getParameter("firstname"));
        boolean lastname = validator.checkUserLastName(request.getParameter("lastname"));
        boolean middlename = validator.checkUserMiddleName(request.getParameter("middlename"));
        boolean address = validator.checkUserAddress(request.getParameter("address"));
        boolean telephone = validator.checkUserTelephone(request.getParameter("telephone"));
        boolean mobilephone = validator.checkUserPMobilephone(request.getParameter("mobilephone"));
        try {
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
            } else {
                List<String> invalidFields = validator.getInvalidFields();
                for (String invalidField : invalidFields) {
                    jsonObject.put(invalidField + "NotValid", invalidField + ".is.not.valid");
//                    jsonObject.put(invalidField + "NotValid","<fmt:message key=\"message."+invalidField+".is.not.valid\"/>");
                }
            }
        } finally {
            request.setAttribute("json", jsonObject);
        }
        return null;
    }
}
