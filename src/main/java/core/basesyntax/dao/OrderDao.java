package core.basesyntax.dao;

import core.basesyntax.lib.Dao;
import core.basesyntax.models.Order;
import java.util.List;
import java.util.Optional;

@Dao
public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    Order update(Order order);

    boolean delete(Long id);
}
