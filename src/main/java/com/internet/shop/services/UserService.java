package com.internet.shop.services;

import com.internet.shop.models.User;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    Optional<User> findByLogin(String login);
}
