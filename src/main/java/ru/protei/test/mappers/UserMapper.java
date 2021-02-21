package ru.protei.test.mappers;

import ru.protei.test.domains.User;
import ru.protei.test.dtos.UserDto;

public class UserMapper implements Mapper<User, UserDto> {

  @Override
  public User mapToPersistable(UserDto dto) {
    return User.builder()
        .email(dto.getEmail())
        .name(dto.getName())
        .surname(dto.getSurname())
        .phoneNumber(dto.getPhoneNumber())
        .status(dto.getStatus())
        .build();
  }

  @Override
  public UserDto mapToDto(User persistable) {
    return UserDto.builder()
        .id(persistable.getId())
        .email(persistable.getEmail())
        .name(persistable.getName())
        .surname(persistable.getSurname())
        .phoneNumber(persistable.getPhoneNumber())
        .status(persistable.getStatus())
        .build();
  }
}
