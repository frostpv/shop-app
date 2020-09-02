package core.basesyntax.dao;

import core.basesyntax.lib.Dao;
import core.basesyntax.models.User;
import java.util.List;
import java.util.Optional;

@Dao
public interface UserDao {
    User create(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);
}
