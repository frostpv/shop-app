package com.internet.shop.dao;

import com.internet.shop.lib.Dao;
import com.internet.shop.models.Product;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJdbcImpl implements ProductDao{
    @Override
    public Product create(Product item) {
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
    public Product update(Product item) {
        return null;
    }

    @Override
    public boolean delete(Long item) {
        return false;
    }
}
