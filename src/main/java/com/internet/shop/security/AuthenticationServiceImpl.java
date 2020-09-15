package com.internet.shop.security;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.models.User;
import com.internet.shop.services.UserService;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByLogin(login);
        if (userFromDb.isPresent() && userFromDb.get().getPassword().equals(password)) {
            return userService.findByLogin(login).get();
        }
        throw new AuthenticationException("Incorrect user name or password");
    }
}
