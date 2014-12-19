package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.ImageDao;
import com.epam.star.dao.OrderDao;
import com.epam.star.entity.Client;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Image;
import com.epam.star.entity.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/client")
public class ShowClientPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowClientPageAction.class);

    private ActionResult client = new ActionResult("client");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        OrderDao orderDao = daoManager.getOrderDao();
        ClientDao clientDao = daoManager.getClientDao();
        ImageDao imageDao = daoManager.getImageDao();

        Client currentClient = (Client) request.getSession().getAttribute("user");
        Client user = clientDao.findById(currentClient.getId());
        Image clientAvatar = imageDao.findById(user.getAvatar());

        int clientOrdersCount = orderDao.getClientOrdersCount(user.getId());

        List<Period> periods = daoManager.getPeriodDao().getAllPeriods();
        List<Goods> goods = daoManager.getGoodsDao().getAllGoods();

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("periods", periods);
        session.setAttribute("goods", goods);
        session.setAttribute("ordersCount", clientOrdersCount);
        session.setAttribute("clientAvatar", clientAvatar);

        daoManager.closeConnection();

        return client;
    }
}
