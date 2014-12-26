package com.epam.star.entity.interfaces;

import com.epam.star.entity.Goods;

import java.math.BigDecimal;

public interface ShoppingCart {

    public BigDecimal getTotalSum();
    public int getGoodsCount();

    public void addGoods(Goods goods);

    public void setGoodsCount(Goods goods, int count);

    public void removeGoods(Goods goods);
}
