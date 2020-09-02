package core.basesyntax.services;

import core.basesyntax.models.Product;
import java.util.List;

public interface ProductServiceInterface {

    Product create(Product product);

    Product get(Long id);

    List<Product> getAll();

    Product update(Product product);

    boolean delete(Long id);
}
