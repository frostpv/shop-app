package com.internet.shop.security;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.models.User;
import com.internet.shop.services.UserService;
import com.internet.shop.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByLogin(login);
        if (userFromDb.isPresent()
                && isValid(userFromDb.get().getPassword(), password, userFromDb.get().getSalt())) {
            return userFromDb.get();
        }

        throw new AuthenticationException("Incorrect user name or password");
    }

    private static boolean isValid(String userPassword, String password, byte[] salt) {
        return userPassword.equals(HashUtil.hashPassword(password, salt));
    }
}
