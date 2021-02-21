package ru.protei.test.runners;

import static java.time.temporal.ChronoUnit.MINUTES;
import static ru.protei.test.domains.Status.AWAY;
import static ru.protei.test.domains.Status.ONLINE;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.protei.test.domains.User;
import ru.protei.test.repositories.UserRepository;
import ru.protei.test.schedulers.TaskScheduler;
import ru.protei.test.tasks.ChangeStatusToAwayTask;

/**
 * Runner checks if there are any users with {@link ru.protei.test.domains.Status#ONLINE} status and
 * change it to {@link ru.protei.test.domains.Status#AWAY} or schedules with {@link
 * ru.protei.test.schedulers.TaskScheduler}.
 *
 * @see ru.protei.test.schedulers.UserStatusChangeScheduler
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class UserStatusChangeRunner implements ApplicationRunner {

  private final UserRepository userRepository;
  private final TaskScheduler userStatusChangeScheduler;

  /**
   * Check all users with with {@link ru.protei.test.domains.Status#ONLINE} status then change them
   * to {@link ru.protei.test.domains.Status#AWAY} if users' lasOnlineChange date was earlier than
   * five minutes before now. Schedule this task to {@link ru.protei.test.schedulers.TaskScheduler}
   * otherwise.
   *
   * @param args has no usages.
   * @throws Exception if some internal exception occurs
   * @see ru.protei.test.domains.User
   * @see ru.protei.test.schedulers.UserStatusChangeScheduler
   */
  @Transactional
  @Override
  public void run(ApplicationArguments args) throws Exception {
    final List<User> onlineUsers = userRepository.findAllByStatus(ONLINE);
    for (final var user : onlineUsers) {
      if (user.getLastOnlineChange().isBefore(Instant.now().minus(5, MINUTES))) {
        user.setStatus(AWAY);
        log.info("Set away status for user with id={}", user.getId());
      } else {
        userStatusChangeScheduler.scheduleTask(
            user.getId(),
            new ChangeStatusToAwayTask(user.getId(), userRepository),
            Instant.now()
                .plus(
                    Duration.between(Instant.now(), user.getLastOnlineChange().plus(5, MINUTES))));
      }
    }
    log.info("Finished UserStatusChangeRunner");
  }
}
