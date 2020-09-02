package core.basesyntax.dao.impl;

import core.basesyntax.dao.UserDao;
import core.basesyntax.models.User;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
