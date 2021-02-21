package ru.protei.test.services;

import static java.lang.String.format;
import static java.time.temporal.ChronoUnit.MINUTES;
import static ru.protei.test.domains.Status.ONLINE;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.protei.test.domains.Status;
import ru.protei.test.domains.User;
import ru.protei.test.dtos.UserDto;
import ru.protei.test.dtos.UserIdDto;
import ru.protei.test.dtos.UserStatusDto;
import ru.protei.test.exceptions.IllegalMappingOperationException;
import ru.protei.test.exceptions.ResourceAlreadyExistsException;
import ru.protei.test.exceptions.ResourceNotFoundException;
import ru.protei.test.mappers.Mapper;
import ru.protei.test.repositories.UserRepository;
import ru.protei.test.schedulers.UserStatusChangeScheduler;
import ru.protei.test.tasks.ChangeStatusToAwayTask;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final UserStatusChangeScheduler userStatusChangeScheduler;
  private final Mapper<User, UserIdDto> userIdMapper;
  private final Mapper<User, UserDto> userMapper;

  @Transactional
  public UserIdDto saveUser(final UserDto userDto)
      throws ResourceAlreadyExistsException, IllegalMappingOperationException {
    if (userRepository.existsByEmail(userDto.getEmail())) {
      throw new ResourceAlreadyExistsException(
          format("User with email %s already exists.", userDto.getEmail()));
    }
    final User userToSave = userMapper.mapToPersistable(userDto);
    final User savedUser = userRepository.save(userToSave);
    return userIdMapper.mapToDto(savedUser);
  }

  @Transactional(readOnly = true)
  public UserDto findUserById(final Long id)
      throws ResourceNotFoundException, IllegalMappingOperationException {
    final User user = findUserOrThrowException(id);
    return userMapper.mapToDto(user);
  }

  @Transactional
  public UserStatusDto changeUserStatus(final Long id, final UserStatusDto userStatusDto)
      throws ResourceNotFoundException {
    final User user = findUserOrThrowException(id);
    final Status oldStatus = user.getStatus();
    final Status newStatus = userStatusDto.getStatus();

    user.setStatus(userStatusDto.getStatus());

    final Long userId = user.getId();
    if (oldStatus == ONLINE) {
      userStatusChangeScheduler.cancelTask(userId);
    }
    if (newStatus == ONLINE) {
      user.setLastOnlineChange(Instant.now());
      userStatusChangeScheduler.scheduleTask(
          userId,
          new ChangeStatusToAwayTask(userId, userRepository),
          Instant.now().plus(5, MINUTES));
    }

    return UserStatusDto.builder()
        .id(user.getId())
        .oldStatus(oldStatus)
        .newStatus(newStatus)
        .build();
  }

  private User findUserOrThrowException(final Long id) throws ResourceNotFoundException {
    return userRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(format("User with id %d was not found.", id)));
  }
}
