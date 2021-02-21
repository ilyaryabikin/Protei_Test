package ru.protei.test.domains;

import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = PRIVATE)
@Getter
@ToString
public enum Status implements Serializable {
  ONLINE("Online"),
  AWAY("Away"),
  OFFLINE("Offline");

  private final String name;
}
