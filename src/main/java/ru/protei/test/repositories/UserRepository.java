package ru.protei.test.repositories;

import org.springframework.stereotype.Repository;
import ru.protei.test.domains.User;

@Repository
public interface UserRepository extends PersistableRepository<User, Long> {}
