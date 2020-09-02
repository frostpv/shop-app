package core.basesyntax.dao.impl;

import core.basesyntax.dao.ProductDao;
import core.basesyntax.models.Product;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
