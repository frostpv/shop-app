package com.internet.shop.services;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.models.Product;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductServiceInterface {
    @Inject
    ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).orElseThrow();
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        if (get(product.getId()).getId() == product.getId()) {
            return productDao.update(product);
        }
        throw new RuntimeException("Product is not found");
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}
