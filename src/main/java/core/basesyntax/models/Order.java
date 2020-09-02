package core.basesyntax.models;

import java.util.List;

public class Order {
    private long id;
    private List<Product> products;
    private long idUser;

    public Order(long id, List<Product> products, long idUser) {
        this.id = id;
        this.products = products;
        this.idUser = idUser;
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

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}
