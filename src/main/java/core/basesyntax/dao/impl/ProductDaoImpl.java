package core.basesyntax.dao.impl;

import core.basesyntax.dao.ProductDao;
import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.models.Product;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        return Storage.addProduct(product);
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        return Storage.products.stream()
                .filter(productFromStorage -> productFromStorage.getId() == (product.getId()))
                .limit(1)
                .peek(x -> x.setPrice(product.getPrice()))
                .peek(x -> x.setName(product.getName()))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public boolean delete(Long id) {
        return Storage.products
                .removeIf(product -> product.getId()==id);
    }
}
