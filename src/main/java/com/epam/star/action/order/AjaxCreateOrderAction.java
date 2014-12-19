package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.dao.*;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Client;
import com.epam.star.entity.Employee;
import com.epam.star.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AjaxCreateOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxCreateOrderAction.class);


    ActionResult client = new ActionResult("registration", true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        try {
            OrderDao orderDao = daoManager.getOrderDao();
            Order order = createOrder(request, daoManager);
            orderDao.insert(order);

            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }

        return client;
    }

    private Order createOrder(HttpServletRequest request, DaoManager daoManager) throws ActionException {

        Order order = null;
        try {
            PeriodDao periodDao = daoManager.getPeriodDao();
            GoodsDao goodsDao = daoManager.getGoodsDao();
            StatusDao statusDao = daoManager.getStatusDao();
            ClientDao clientDao = daoManager.getClientDao();
            EmployeeDao employeeDao = daoManager.getEmployeeDao();

            Client user = clientDao.findById((Integer) request.getAttribute("idUser"));
            if (user == null)
                user = employeeDao.findById((Integer) request.getAttribute("idUser"));
            if (user == null)
                user = (Client) request.getSession().getAttribute("user");
            BigDecimal clientBalance = user.getVirtualBalance();
            BigDecimal goodsPricee = goodsDao.findByGoodsName(request.getParameter("goodsname")).getPrice();
            BigDecimal orderCost = goodsPricee.multiply(new BigDecimal(request.getParameter("goodscount")));

            order = new Order();
            order.setUser(user);
            order.setCount(Integer.parseInt(request.getParameter("goodscount")));
            order.setPeriod(periodDao.findByPeriod(Time.valueOf(request.getParameter("deliverytime"))));
            order.setGoods(goodsDao != null ? goodsDao.findByGoodsName(request.getParameter("goodsname")) : null);
            order.setOrderCost(orderCost);
            try {
                order.setDeliveryDate(new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("deliverydate")));
            } catch (ParseException e) {
                throw new ActionException(e);
            }
            order.setAdditionalInfo(request.getParameter("additionalinformation"));
            order.setStatus(statusDao != null ? statusDao.findByStatusName("waiting") : null);
            order.setOrderDate(new Date());

            debitFunds(request, daoManager);

        } catch (Exception e) {
            request.setAttribute("CreateOrderError", "You made a mistake, check all fields");
            throw new ActionException(e);
        }

        return order;
    }

    private void debitFunds(HttpServletRequest request, DaoManager daoManager) {
        boolean onlinePayment;

        String paymentType = request.getParameter("PaymentType");
        Client user = (Client) request.getSession().getAttribute("user");

        StatusDao statusDao = daoManager.getStatusDao();
        GoodsDao goodsDao = daoManager.getGoodsDao();
        ClientDao clientDao = daoManager.getClientDao();
        EmployeeDao employeeDao = daoManager.getEmployeeDao();

        BigDecimal goodsPrice = goodsDao.findByGoodsName(request.getParameter("goodsname")).getPrice();
        boolean online = paymentType.equals("online");

        BigDecimal clientBalance = user.getVirtualBalance();
        BigDecimal goodsPricee = goodsDao.findByGoodsName(request.getParameter("goodsname")).getPrice();
        BigDecimal res = user.getVirtualBalance().subtract(goodsPricee.multiply(new BigDecimal(request.getParameter("goodscount"))));

        if (online && (clientBalance.compareTo(goodsPrice) == 0 || clientBalance.compareTo(goodsPrice) == 1))
            onlinePayment = true;
        else onlinePayment = false;

        if (onlinePayment) {
            user.setVirtualBalance(res);
            if (user.getRole().equals(statusDao.findByStatusName("Client")))
                clientDao.updateEntity(user);
            else employeeDao.updateEntity((Employee) user);
        }
    }
}
