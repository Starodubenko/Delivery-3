package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.dao.util.PaginatedList;
import com.epam.star.dao.util.Pagination;
import com.epam.star.entity.Goods;
import com.epam.star.entity.ShoppingCart_HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/services")
public class ShowServicesPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowServicesPageAction.class);

    private ActionResult services = new ActionResult("services");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        Pagination pagination = new Pagination();

        PaginatedList<Goods> goods = pagination.paginationEntity(request, goodsDao, "goods");
        ShoppingCart_HashMap shoppingCart = (ShoppingCart_HashMap) request.getSession().getAttribute("shoppingCart");

        for (Goods good : goods) {
            for (Goods goods1 : shoppingCart.keySet()) {
                if (good.equals(goods1))
                    good.setInCart(true);
            }
        }

        request.setAttribute("goodsPaginatedList", goods);

        daoManager.closeConnection();

        return services;
    }
}

