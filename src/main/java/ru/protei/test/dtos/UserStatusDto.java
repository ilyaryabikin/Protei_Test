package ru.protei.test.dtos;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.protei.test.domains.Status;

@JsonInclude(NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatusDto implements Dto {

  @JsonProperty(access = READ_ONLY)
  private Long id;

  @JsonProperty(access = WRITE_ONLY)
  private Status status;

  @JsonProperty(access = READ_ONLY)
  private Status oldStatus;

  @JsonProperty(access = READ_ONLY)
  private Status newStatus;
}
