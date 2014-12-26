package com.epam.star.action.cart;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.entity.Goods;
import com.epam.star.entity.interfaces.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@MappedAction("POST/addGoods")
public class AddGoodsToCart implements Action{
    private static final Logger LOGGER = LoggerFactory.getLogger(AddGoodsToCart.class);
    ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        HttpSession session = request.getSession();

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));

        Goods goods = goodsDao.findById(goodsId);

        shoppingCart.addGoods(goods);
        session.setAttribute("shoppingCart", shoppingCart);
        request.setAttribute("message", "add.goods");

        daoManager.closeConnection();

        return message;
    }
}
