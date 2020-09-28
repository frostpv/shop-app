package com.internet.shop.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private Long idUser;

    public Order() {
    }

    public Order(Long idUser) {
        this.products = new ArrayList<>();
        this.idUser = idUser;
    }

    public Long getId() {
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Order{" + "id="
                + id + ", products="
                + products + ", idUser="
                + idUser + '}';
    }
}
