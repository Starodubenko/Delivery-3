package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.OrderDao;
import com.epam.star.entity.Client;
import com.epam.star.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/clientInfo")
public class ShowClientInfoPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowClientInfoPageAction.class);
    private static final DaoManager DAO_MANAGER = DaoFactory.getInstance().getDaoManager();

    private ActionResult clientInfo = new ActionResult("clientInfo");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        OrderDao orderDao = DAO_MANAGER.getOrderDao();
        ClientDao clientDao = DAO_MANAGER.getClientDao();

        Client currentClient = (Client) request.getSession().getAttribute("user");
        Client user = clientDao.findById(currentClient.getId());

        List<Order> todayOrders = orderDao.findAllByClientIdToday(user.getId());
        List<Order> pastOrders = orderDao.findAllByClientIdLastDays(user.getId());

        HttpSession session = request.getSession();
        session.setAttribute("todayOrders", todayOrders);
        session.setAttribute("pastOrders", pastOrders);

        DAO_MANAGER.closeConnection();

        return clientInfo;
    }
}
