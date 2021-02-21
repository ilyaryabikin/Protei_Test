package ru.protei.test.schedulers;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component("userStatusChangeScheduler")
@Slf4j
public class UserStatusChangeScheduler implements TaskScheduler {

  private final ThreadPoolTaskScheduler threadPoolTaskScheduler;
  private final Map<Long, ScheduledFuture<?>> scheduledFutureMap;

  @Autowired
  public UserStatusChangeScheduler(
      final ThreadPoolTaskScheduler threadPoolUserStatusChangeScheduler) {
    this.threadPoolTaskScheduler = threadPoolUserStatusChangeScheduler;
    this.scheduledFutureMap = new ConcurrentHashMap<>();
  }

  @Override
  public ScheduledFuture<?> scheduleTask(
      final Long taskId, final Runnable task, final Instant startDate) {
    final var scheduledFuture = threadPoolTaskScheduler.schedule(task, startDate);
    scheduledFutureMap.put(taskId, scheduledFuture);
    log.info(
        "Scheduling task with id={} and remaining delay={} ms",
        taskId,
        scheduledFuture.getDelay(MILLISECONDS));
    return scheduledFuture;
  }

  @Override
  public boolean cancelTask(final Long taskId) {
    final var scheduledFuture = scheduledFutureMap.get(taskId);
    if (scheduledFuture == null) {
      return false;
    }
    log.info(
        "Cancelling task with id={} and remaining delay={} ms",
        taskId,
        scheduledFuture.getDelay(MILLISECONDS));
    return scheduledFuture.cancel(false);
  }
}
