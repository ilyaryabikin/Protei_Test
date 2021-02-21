package ru.protei.test.schedulers;

import java.time.Instant;
import java.util.concurrent.ScheduledFuture;
import org.springframework.stereotype.Component;

@Component
public interface TaskScheduler {

  ScheduledFuture<?> scheduleTask(final Long taskId, final Runnable task, final Instant startDate);

  boolean cancelTask(final Long taskId);
}
