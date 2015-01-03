package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao;
import com.epam.star.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/SetOrderToEditFields")
public class AjaxSetOrderToEditFields implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSetOrderToEditFields.class);

    private ActionResult editOrdersRow = new ActionResult("ajaxOrderTableRow");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        int id = Integer.parseInt(request.getParameter("id"));

        H2OrderDao orderDao = daoManager.getOrderDao();
        Order order = orderDao.findById(id);

        request.getSession().setAttribute("order", order);

        daoManager.closeConnection();
        return editOrdersRow;
    }
}
