package com.internet.shop.dao;

import com.internet.shop.lib.Dao;
import com.internet.shop.models.Order;
import com.internet.shop.models.ShoppingCart;

import java.util.List;

@Dao
public class OrderDaoImpl implements OrderDao{
    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return null;
    }

    @Override
    public Order get(Long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
