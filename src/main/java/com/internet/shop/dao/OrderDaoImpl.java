package com.internet.shop.dao;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.Order;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        return Storage.addOrder(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order update(Order order) {
        for (Order or : Storage.orders) {
            if (or.getId().equals(order.getId())) {
                Storage.orders.set(Storage.orders.indexOf(or), order);
                return or;
            }
        }
        throw new RuntimeException("Order does not exist in database with id " + order.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders
                .removeIf(order -> id.equals(order.getId()));
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.orders.stream()
                .filter(order -> order.getIdUser().equals(userId))
                .collect(Collectors.toList());
    }
}
