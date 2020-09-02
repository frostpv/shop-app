package core.basesyntax.dao;

import core.basesyntax.lib.Dao;
import core.basesyntax.models.ShoppingCart;
import java.util.List;
import java.util.Optional;

@Dao
public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    Optional<ShoppingCart> get(Long id);

    List<ShoppingCart> getAll();

    ShoppingCart update(ShoppingCart shoppingCart);

    boolean delete(Long id);
}
