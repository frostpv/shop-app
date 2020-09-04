package com.internet.shop.dao;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.Product;

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
                .filter(product -> id.equals(product.getId()))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        for (Product prod : Storage.products) {
            if (prod.getId() == product.getId()) {
                Storage.products.set(Storage.products.indexOf(prod), product);
                return prod;
            }
        }
        throw new RuntimeException("Product is not exist in database");
    }

    @Override
    public boolean delete(Long id) {
        return Storage.products
                .removeIf(product -> id.equals(product.getId()));
    }
}
