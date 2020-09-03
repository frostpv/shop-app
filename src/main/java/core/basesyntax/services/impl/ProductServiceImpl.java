package core.basesyntax.services.impl;

import core.basesyntax.dao.ProductDao;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Services;
import core.basesyntax.models.Product;
import core.basesyntax.services.ProductServiceInterface;

import java.util.List;

@Services
public class ProductServiceImpl implements ProductServiceInterface {
    @Inject
    ProductDao productDao;

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product get(Long id) {
        return null;
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
