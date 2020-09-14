package com.internet.shop.services;

import com.internet.shop.models.Order;
import com.internet.shop.models.ShoppingCart;
import java.util.List;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getUserOrders(Long userId);

    Double getOrderPrice(Long orderId);
}
