package core.basesyntax.dao.impl;

import core.basesyntax.dao.ShoppingCartDao;
import core.basesyntax.models.ShoppingCart;
import java.util.List;
import java.util.Optional;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return null;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
