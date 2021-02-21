package ru.protei.test.mappers;

import ru.protei.test.domains.User;
import ru.protei.test.dtos.UserIdDto;
import ru.protei.test.exceptions.IllegalMappingOperationException;

public class UserIdMapper implements Mapper<User, UserIdDto> {

  @Override
  public User mapToPersistable(final UserIdDto dto) throws IllegalMappingOperationException {
    throw new IllegalMappingOperationException("Can not map UserIdDto to User entity.");
  }

  @Override
  public UserIdDto mapToDto(final User persistable) throws IllegalMappingOperationException {
    return new UserIdDto(persistable.getId());
  }
}
