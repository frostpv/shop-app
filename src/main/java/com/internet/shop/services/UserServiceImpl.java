package com.internet.shop.services;

import com.internet.shop.dao.UserDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.models.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao
                .get(id)
                .get();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        if (user.getId().equals(get(user.getId()).getId())) {
            return userDao.update(user);
        }
        throw new RuntimeException("User is not found");
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }
}