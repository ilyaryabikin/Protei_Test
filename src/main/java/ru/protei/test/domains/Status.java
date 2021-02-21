package ru.protei.test.domains;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor(access = PRIVATE)
@Getter
@ToString
public enum Status implements Serializable {
  ONLINE("Online"),
  AWAY("Away"),
  OFFLINE("Offline");

  private final String name;

  /**
   * Parse Status object from corresponding name ignoring case.
   *
   * @param name name to parse
   * @return parsed Status object
   * @throws java.lang.IllegalArgumentException if there is no corresponding Status object
   */
  @NonNull
  public static Status forName(final @NonNull String name) throws IllegalArgumentException {
    for (final var status : values()) {
      if (status.getName().equalsIgnoreCase(name.trim())) {
        return status;
      }
    }
    throw new IllegalArgumentException(format("Status for name %s was not found.", name));
  }
}
