package ru.protei.test.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class TaskSchedulersConfig {

  @Bean
  public ThreadPoolTaskScheduler threadPoolUserStatusChangeScheduler() {
    final ThreadPoolTaskScheduler userStatusChangeScheduler = new ThreadPoolTaskScheduler();
    userStatusChangeScheduler.setPoolSize(10);
    userStatusChangeScheduler.setThreadNamePrefix("ThreadPoolUserStatusChangeScheduler");
    return userStatusChangeScheduler;
  }
}
