package com.internet.shop.services;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.models.Order;
import com.internet.shop.models.ShoppingCart;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderServiceInterface {

    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartServiceIntercace shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order(shoppingCart.getUserId());
        order.setProducts(List.copyOf(shoppingCart.getProducts()));
        orderDao.create(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return getAll().stream()
                .filter(order -> order.getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
