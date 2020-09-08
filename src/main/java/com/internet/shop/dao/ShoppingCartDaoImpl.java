package com.internet.shop.dao;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.ShoppingCart;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return Storage.addShopingCart(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Storage.shoppingCarts.stream()
                .filter(shoppingCart -> shoppingCart.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        for (ShoppingCart cart : Storage.shoppingCarts) {
            if (cart.getId().equals(shoppingCart.getId())) {
                Storage.shoppingCarts.set(Storage.shoppingCarts.indexOf(cart), shoppingCart);
                return cart;
            }
        }
        throw new RuntimeException("Cart does not exist in database with id "
                + shoppingCart.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return Storage.shoppingCarts
                .stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public boolean delete(Long id) {
        return Storage.shoppingCarts
                .removeIf(shoppingCart -> id.equals(shoppingCart.getId()));
    }
}
