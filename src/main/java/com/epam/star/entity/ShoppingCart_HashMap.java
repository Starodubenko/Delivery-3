package com.epam.star.entity;

import com.epam.star.entity.interfaces.ShoppingCart;

import java.math.BigDecimal;
import java.util.HashMap;

public class ShoppingCart_HashMap extends HashMap<Goods, Integer> implements ShoppingCart {

    @Override
    public BigDecimal getTotalSum() {
        BigDecimal totalSum = new BigDecimal(0);

        for (Entry<Goods, Integer> goods : super.entrySet()) {
            BigDecimal price = goods.getKey().getPrice();
            Integer count = goods.getValue().intValue();
            BigDecimal cost = price.multiply(new BigDecimal(count));
            totalSum = totalSum.add(cost);
        }
        return totalSum;
    }

    @Override
    public int getGoodsCount() {
        return super.size();
    }

    @Override
    public void addGoods(Goods goods) {
        super.put(goods,1);
    }

    @Override
    public void setGoodsCount(Goods goods, int count) {
        super.replace(goods,count);
    }

    @Override
    public void removeGoods(Goods goods) {
        super.remove(goods);
    }
}
