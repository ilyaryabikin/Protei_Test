package ru.protei.test.tasks;

import static ru.protei.test.domains.Status.AWAY;

import java.util.Optional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.protei.test.domains.User;
import ru.protei.test.repositories.UserRepository;

@Data
@Slf4j
public class ChangeStatusToAwayTask implements Runnable {

  private final Long userId;
  private final UserRepository userRepository;

  @Override
  public void run() {
    final Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isEmpty()) {
      log.info("Failed to set away status for user with id={} as the user was not found.", userId);
      return;
    }
    final User awayUser = optionalUser.get();
    awayUser.setStatus(AWAY);
    userRepository.save(awayUser);
    log.info("Set away status for user with id={}", userId);
  }
}
