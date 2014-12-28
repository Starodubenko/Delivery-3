package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ContactDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Contact;
import com.epam.star.entity.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/welcome")
public class ShowWelcomePageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowWelcomePageAction.class);

    private ActionResult login = new ActionResult("welcome");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        ContactDao contactDao = daoManager.getContactDao();
        List<Contact> contacts = contactDao.getContacts();

        request.getSession().setAttribute("contacts", contacts);
        request.getSession().setAttribute("shoppingCart", new Cart());

        daoManager.closeConnection();

        return login;
    }
}

