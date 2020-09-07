package com.internet.shop.dao;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.ShoppingCart;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return Storage.addShopingCart(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Storage.shoppingCarts.stream()
                .filter(shoppingCart -> id.equals(shoppingCart.getId()))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        for (ShoppingCart scrd : Storage.shoppingCarts) {
            if (scrd.getId().equals(shoppingCart.getId())) {
                Storage.shoppingCarts.set(Storage.shoppingCarts.indexOf(scrd), shoppingCart);
                return scrd;
            }
        }
        throw new RuntimeException("Cart is not exist in database");
    }

    @Override
    public boolean delete(Long id) {
        return Storage.shoppingCarts
                .removeIf(shoppingCart -> id.equals(shoppingCart.getId()));
    }
}
