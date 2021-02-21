package ru.protei.test.domains;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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

  @JsonValue
  @JsonProperty("status")
  private final String name;

  /**
   * Parse Status object from corresponding name ignoring case.
   *
   * @param status name to parse
   * @return parsed Status object
   * @throws java.lang.IllegalArgumentException if there is no corresponding Status object
   */
  @NonNull
  @JsonCreator
  public static Status forName(final @NonNull String status) throws IllegalArgumentException {
    for (final var obj : values()) {
      if (obj.getName().equalsIgnoreCase(status.trim())) {
        return obj;
      }
    }
    throw new IllegalArgumentException(format("Status for name %s was not found.", status));
  }
}
