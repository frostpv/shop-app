package com.internet.shop.services;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;

@Service
public class ShoppingCartServiseImpl implements ShoppingCartServiceIntercace {

    @Inject
    ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        for (Product prod : shoppingCart.getProducts()) {
            if (prod.getId().equals(product.getId())) {
                shoppingCart.getProducts().set(shoppingCart.getProducts().indexOf(prod), product);
                shoppingCartDao.update(shoppingCart);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getAll()
                .stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return shoppingCartDao.delete(shoppingCart.getId());
    }
}
