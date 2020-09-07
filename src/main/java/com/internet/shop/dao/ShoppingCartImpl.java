package com.internet.shop.dao;

import com.internet.shop.lib.Dao;
import com.internet.shop.models.Product;

@Dao
public class ShoppingCartImpl implements ShoppingCart {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        return false;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {

    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return null;
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return false;
    }
}
