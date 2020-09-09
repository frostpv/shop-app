package com.internet.shop.services;

import java.util.List;

public interface GenericService<T, V> {
    T create(T item);

    T get(V id);

    List<T> getAll();

    T update(T item);

    boolean delete(V id);
}
