package core.basesyntax.dao.impl;

import core.basesyntax.dao.ProductDao;
import core.basesyntax.db.Storage;
import core.basesyntax.models.Product; 
import java.util.List;
import java.util.Optional;

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
        if (get(product.getId()).isPresent()) {
            Product product1 = get(product.getId()).get();
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            return product1;
        }
        throw new RuntimeException();
    }

    @Override
    public boolean delete(Long id) {
        Product product = get(id).get();
        Storage.products.remove(product);
        return true;
    }
}
