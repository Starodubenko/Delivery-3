package com.epam.star.entity;

import com.epam.star.entity.interfaces.ShoppingCart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart extends AbstractEntity implements ShoppingCart {

    private Map<Goods, Integer> cart = new HashMap<>();

    public Map<Goods, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Goods, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public BigDecimal getTotalSum() {
        BigDecimal totalSum = new BigDecimal(0);

        for (Map.Entry<Goods, Integer> goods : cart.entrySet()) {
            BigDecimal price = goods.getKey().getPrice();
            Integer count = goods.getValue().intValue();
            BigDecimal cost = price.multiply(new BigDecimal(count));
            totalSum = totalSum.add(cost);
        }
        return totalSum;
    }

    @Override
    public int getGoodsCount() {
        return cart.size();
    }

    @Override
    public void addGoods(Goods goods) {
        cart.put(goods, 1);
    }

    @Override
    public void setGoodsCount(Goods goods, int count) {
        for (Map.Entry<Goods, Integer> entry : cart.entrySet()) {
            if (entry.getKey().equals(goods)) entry.setValue(count);
        }
    }

    @Override
    public void removeGoods(Goods goods) {
        cart.remove(goods);
    }

    @Override
    public BigDecimal getCostByGoodsId(int id) {
        for (Map.Entry<Goods, Integer> entry : cart.entrySet()) {
            if (entry.getKey().getId() == id) {
                return entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue()));
            }
        }
        return null;
    }
}
