package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.*;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Client;
import com.epam.star.entity.Employee;
import com.epam.star.entity.Order;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@MappedAction("GET/fastCreateOrder")
public class CreateOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderAction.class);
    ActionResult client = new ActionResult("ordersTable", true);
    ActionResult jsonn = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        Order order = null;
        try {
            OrderDao orderDao = daoManager.getOrderDao();
            order = createOrder(request, daoManager);
            orderDao.insert(order);

            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }

        JSONObject json = null;
        if (order != null) {
            json = new JSONObject();
            json.put("errorMessage", "Order created successful !");
        } else json.put("errorMessage", "During creating the order an error has occurred !");

        request.setAttribute("json", json);

        if (json.length() > 0) return jsonn;

        return client;
    }

    private Order createOrder(HttpServletRequest request, DaoManager daoManager) throws ActionException {

        Order order = null;
        String idString = request.getParameter("id");
        int index = -1;
        if (idString != null)
            index = Integer.parseInt(request.getParameter("id"));

        Client user = daoManager.getClientDao().findById(index);
        if (user == null) daoManager.getEmployeeDao().findById(index);
        if (user == null) user = (Client) request.getSession().getAttribute("user");

        try {
            PeriodDao periodDao = daoManager.getPeriodDao();
            GoodsDao goodsDao = daoManager.getGoodsDao();
            StatusDao statusDao = daoManager.getStatusDao();

            if (user == null) user = (Client) request.getSession().getAttribute("user");
            BigDecimal clientBalance = user.getVirtualBalance();
            BigDecimal goodsPricee = goodsDao.findByGoodsName(request.getParameter("goodsname")).getPrice();
            BigDecimal orderCost = goodsPricee.multiply(new BigDecimal(request.getParameter("goodscount")));

            order = new Order();
            order.setUser(user);
            order.setCount(Integer.parseInt(request.getParameter("goodscount")));
            order.setPeriod(periodDao.findByPeriod(Time.valueOf(request.getParameter("deliverytime"))));
            order.setGoods(goodsDao != null ? goodsDao.findByGoodsName(request.getParameter("goodsname")) : null);
            order.setOrderCost(orderCost);
            boolean res;
            res = debitFunds(request, daoManager, user);
            if (res)
                order.setPaid(orderCost);
            else order.setPaid(new BigDecimal(0));
            try {
                order.setDeliveryDate(new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("deliverydate")));
            } catch (ParseException e) {
                throw new ActionException(e);
            }
            order.setAdditionalInfo(request.getParameter("additionalinformation"));
            order.setStatus(statusDao != null ? statusDao.findByStatusName("waiting") : null);
            order.setOrderDate(new Date());

        } catch (Exception e) {
            request.setAttribute("CreateOrderError", "You made a mistake, check all fields");
            throw new ActionException(e);
        }

        return order;
    }

    private boolean debitFunds(HttpServletRequest request, DaoManager daoManager, Client user) {
        boolean onlinePayment;

        String paymentType = request.getParameter("paymentType");

        PositionDao positionDao = daoManager.getPositionDao();
        GoodsDao goodsDao = daoManager.getGoodsDao();
        ClientDao clientDao = daoManager.getClientDao();
        EmployeeDao employeeDao = daoManager.getEmployeeDao();

        BigDecimal goodsPrice = goodsDao.findByGoodsName(request.getParameter("goodsname")).getPrice();
        boolean online = paymentType.equals("online");

        BigDecimal clientBalance = user.getVirtualBalance();
        BigDecimal goodsPricee = goodsDao.findByGoodsName(request.getParameter("goodsname")).getPrice();
        if (!user.getRole().equals(positionDao.findByPositionName("Client")))
            goodsPricee = goodsPricee.divide(new BigDecimal(2));
        BigDecimal res = user.getVirtualBalance().subtract(goodsPricee.multiply(new BigDecimal(request.getParameter("goodscount"))));

        if (online && (clientBalance.compareTo(goodsPrice) == 0 || clientBalance.compareTo(goodsPrice) == 1))
            onlinePayment = true;
        else onlinePayment = false;

        if (onlinePayment) {
            user.setVirtualBalance(res);
            if (user.getRole().equals(positionDao.findByPositionName("Client")))
                clientDao.updateEntity((Client) user);
            else employeeDao.updateEntity((Employee) user);
            return true;
        }
        return false;
    }
}
