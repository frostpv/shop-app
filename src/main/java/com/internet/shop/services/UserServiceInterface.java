package com.internet.shop.services;

import com.internet.shop.models.User;
import java.util.List;

public interface UserServiceInterface {

    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);
}
