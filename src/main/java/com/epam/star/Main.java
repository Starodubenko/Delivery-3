package com.epam.star;


import com.epam.star.entity.Goods;
import com.epam.star.entity.ShoppingCart_HashMap;
import com.epam.star.entity.interfaces.ShoppingCart;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws SQLException, ParseException {
        Goods goods1 = new Goods();
        goods1.setGoodsName("Test goods №1");
        goods1.setPrice(new BigDecimal(500));
        Goods goods2 = new Goods();
        goods2.setGoodsName("Test goods №2");
        goods2.setPrice(new BigDecimal(200));

        ShoppingCart shoppingCart = new ShoppingCart_HashMap();
        shoppingCart.addGoods(goods1);
        shoppingCart.setGoodsCount(goods1,3);

        shoppingCart.addGoods(goods2);
        shoppingCart.setGoodsCount(goods2,10);

        System.out.println(shoppingCart.getGoodsCount());
        System.out.println(shoppingCart.getTotalSum());

        shoppingCart.removeGoods(goods2);
        System.out.println(shoppingCart.getTotalSum());

    }
}
