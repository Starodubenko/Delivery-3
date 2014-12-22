package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.GoodsDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.OrderDao;
import com.epam.star.dao.PeriodDao;
import com.epam.star.entity.Order;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;

@MappedAction("POST/saveOrderData")
public class AjaxSaveOrderData implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSaveOrderData.class);

    ActionResult jsonn = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        Order order = null;
        try {
            OrderDao orderDao = daoManager.getOrderDao();
            order = createOrder(request, daoManager);
            orderDao.updateEntity(order);

            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }

        JSONObject json = new JSONObject();
        if (order != null) json.put("errorMessage", "Order saved successful !");
        else json.put("errorMessage", "During savig the order an error has occurred !");

        request.setAttribute("json", json);

        if (json.length() > 0) return jsonn;
        return null;
    }

    private Order createOrder(HttpServletRequest request, DaoManager daoManager) throws ActionException {

        Order order = (Order)request.getSession().getAttribute("order");

        GoodsDao goodsDao = daoManager.getGoodsDao();
        PeriodDao periodDao = daoManager.getPeriodDao();

        order.setGoods(goodsDao.findByGoodsName(request.getParameter("goods-name")));
        order.setCount(Integer.parseInt(request.getParameter("goods-count")));
        order.setDeliveryDate(Date.valueOf(request.getParameter("delivery-date")));
        order.setPeriod(periodDao.findByPeriod(Time.valueOf(request.getParameter("delivery-time"))));
        String addInfo = request.getParameter("additional-info");
        order.setAdditionalInfo(addInfo);

        return order;
    }
}
