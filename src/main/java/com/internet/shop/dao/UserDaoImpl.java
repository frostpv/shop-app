package com.internet.shop.dao;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.models.User;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        return Storage.addUser(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        for (User usr : Storage.users) {
            if (usr.getId().equals(user.getId())) {
                Storage.users.set(Storage.users.indexOf(usr), user);
                return user;
            }
        }
        throw new RuntimeException("User does not exist in database");
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users
                .removeIf(user -> id.equals(user.getId()));
    }
}
