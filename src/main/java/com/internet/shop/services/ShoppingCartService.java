package com.internet.shop.services;

import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;

public interface ShoppingCartService extends GenericService<ShoppingCart, Long> {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    boolean delete(ShoppingCart shoppingCart);
}
