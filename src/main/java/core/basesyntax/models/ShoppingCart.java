package core.basesyntax.models;

import java.util.List;

public class ShoppingCart {
    private long id;
    private List<Product> products;
    private long userId;

    public ShoppingCart(long id, List<Product> products, long userId) {
        this.id = id;
        this.products = products;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
