package ru.protei.test.mappers;

import org.springframework.stereotype.Component;
import ru.protei.test.domains.User;
import ru.protei.test.dtos.UserDto;
import ru.protei.test.exceptions.IllegalMappingOperationException;

@Component("userMapper")
public class UserMapper implements Mapper<User, UserDto> {

  @Override
  public User mapToPersistable(UserDto dto) throws IllegalMappingOperationException {
    return User.builder()
        .email(dto.getEmail())
        .name(dto.getName())
        .surname(dto.getSurname())
        .phoneNumber(dto.getPhoneNumber())
        .build();
  }

  @Override
  public UserDto mapToDto(User persistable) throws IllegalMappingOperationException {
    return UserDto.builder()
        .id(persistable.getId())
        .email(persistable.getEmail())
        .name(persistable.getName())
        .surname(persistable.getSurname())
        .phoneNumber(persistable.getPhoneNumber())
        .status(persistable.getStatus())
        .lastOnlineChange(persistable.getLastOnlineChange())
        .build();
  }
}
