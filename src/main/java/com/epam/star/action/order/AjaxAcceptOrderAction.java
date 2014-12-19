package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.OrderDao;
import com.epam.star.dao.StatusDao;
import com.epam.star.entity.Order;
import com.epam.star.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/accept")
public class AjaxAcceptOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxAcceptOrderAction.class);

    ActionResult ordersTable = new ActionResult("ajaxOrdersTable");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        String stringCheckedOrders = request.getParameter("stringIdOrders");

        String[] idCheckedOrders = stringCheckedOrders.split(",");

        try {
            if (idCheckedOrders.length > 0) {
                daoManager.beginTransaction();

                StatusDao statusDao = daoManager.getStatusDao();
                OrderDao orderDao = daoManager.getOrderDao();

                for (String id : idCheckedOrders) {
                    int index = Integer.parseInt(id);
                    Order order = orderDao.findById(index);
                    Status status = statusDao.findByStatusName("accept");
                    order.setStatus(status);

                    orderDao.updateEntity(order);
                }
            } else {
                LOGGER.error("The order was not selected {}");
                request.setAttribute("SelectOrderError", "Did not selected orders");
            }
            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }


        return ordersTable;
    }
}
