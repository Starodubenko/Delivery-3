package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao2;
import com.epam.star.dao.H2dao.H2OrderedGoodsDao;
import com.epam.star.dao.PeriodDao;
import com.epam.star.dao.StatusDao;
import com.epam.star.entity.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@MappedAction("GET/createFullOrder")
public class CreateFullOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateFullOrderAction.class);
    private static Random rnd = new Random();

    ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        Client user = (Client) request.getSession().getAttribute("user");
        String paymentType = request.getParameter("paymentType");

        Order2 order = null;
        boolean haveMoney = false;
        try {
            H2OrderDao2 orderDao = daoManager.getOrderDao2();

            order = createOrder(request, daoManager);

            haveMoney = checkBalance(user, order);
            if (haveMoney && paymentType.equals("online")) {
                orderDao.insert(order);
                insertOrderedGoods(order.getGoods(),daoManager, order.getNumber());
            }
            else request.setAttribute("message", "order.not.enough.money");

            if (paymentType.equals("cache")) {
                orderDao.insert(order);
                insertOrderedGoods(order.getGoods(),daoManager, order.getNumber());
            }

            daoManager.commit();
            Cart cart = (Cart) request.getSession().getAttribute("shoppingCart");
            cart.clear();
            request.getSession().setAttribute("shoppingCart",cart);
        } catch (Exception e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }

        JSONObject json = new JSONObject();
        if (order != null) {
            if (haveMoney || paymentType.equals("cache"))
            request.setAttribute("message", "order.created.successful");
        } else request.setAttribute("message", "during.creating.error.occurred");

        return message;
    }

    private boolean checkBalance (Client client, Order2 order){
        if (client.getVirtualBalance().intValue()
                < order.getTotalSum().intValue() * (1 - (double)client.getDiscount().getPercentage() / 100)) return false;
        else return true;
    }

    private Order2 createOrder(HttpServletRequest request, DaoManager daoManager) throws ActionException {

        Cart cart = (Cart) request.getSession().getAttribute("shoppingCart");

        Order2 order = null;
        String idString = request.getParameter("id");
        int userId = -1;
        if (idString != null)
            userId = Integer.parseInt(request.getParameter("id"));

        Client user = daoManager.getClientDao().findById(userId);
        if (user == null) daoManager.getEmployeeDao().findById(userId);
        if (user == null) user = (Client) request.getSession().getAttribute("user");

        try {
            PeriodDao periodDao = daoManager.getPeriodDao();
            StatusDao statusDao = daoManager.getStatusDao();

            order = new Order2();
            order.setGoods(cart.getGoods());
            order.setNumber(rnd.nextInt(999999));
            order.setUser(user);
            order.setPeriod(periodDao.findByPeriod(Time.valueOf(request.getParameter("deliverytime"))));
            try {
                order.setDeliveryDate(new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("deliverydate")));
            } catch (ParseException e) {
                throw new ActionException(e);
            }
            order.setOrderDate(new Date());
            order.setAdditionalInfo(request.getParameter("additionalinformation"));
            boolean isOnline = debitFunds(request, daoManager, user);
            if (isOnline) {
                order.setPaid(true);
            }
            else {
                order.setPaid(false);
            }
            order.setDiscount(user.getDiscount());
            order.setStatus(statusDao.findByStatusName("waiting"));

        } catch (Exception e) {
            request.setAttribute("CreateOrderError", "You made a mistake, check all fields");
            throw new ActionException(e);
        }
        return order;
    }

    private boolean debitFunds(HttpServletRequest request, DaoManager daoManager, Client user) {

        String paymentType = request.getParameter("paymentType");
        if (paymentType != null) {
            boolean online = paymentType.equals("online");
            return online;
        }else return false;
    }

    private void insertOrderedGoods(Map<Goods, Integer> goods, DaoManager daoManager, int orderNumber) {

        H2OrderedGoodsDao orderedGoodsDao = daoManager.getOrderedGoodsDao();
        for (Map.Entry<Goods, Integer> good : goods.entrySet()) {
            OrderedGoods orderedGoods = new OrderedGoods();
            orderedGoods.setOrderNumber(orderNumber);
            orderedGoods.setGoods(good.getKey());
            orderedGoods.setGoodsCount(good.getValue());
            orderedGoodsDao.insert(orderedGoods);
        }
    }
}
