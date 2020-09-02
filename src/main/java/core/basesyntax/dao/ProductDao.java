package core.basesyntax.dao;

import core.basesyntax.lib.Dao;
import core.basesyntax.models.Product;
import java.util.List;
import java.util.Optional;

@Dao
public interface ProductDao {
    Product create(Product product);

    Optional<Product> get(Long id);

    List<Product> getAll();

    Product update(Product product);

    boolean delete(Long id);
}
