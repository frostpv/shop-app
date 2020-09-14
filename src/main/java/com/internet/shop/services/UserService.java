package com.internet.shop.services;

import com.internet.shop.models.User;

public interface UserService extends GenericService<User, Long> {
    User findByLogin(String login);
}
