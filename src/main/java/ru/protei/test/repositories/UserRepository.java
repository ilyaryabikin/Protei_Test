package ru.protei.test.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.protei.test.domains.Status;
import ru.protei.test.domains.User;

@Repository
public interface UserRepository extends PersistableRepository<User, Long> {

  boolean existsByEmail(final String email);

  List<User> findAllByStatus(final Status status);
}
