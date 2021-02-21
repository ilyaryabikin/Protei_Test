package ru.protei.test.dtos;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.protei.test.domains.Status;
import ru.protei.test.json.StatusDeserializer;
import ru.protei.test.json.StatusSerializer;

@Data
@NoArgsConstructor
public class UserStatusDto implements Dto {

  @NotNull private Long id;

  @JsonProperty(access = WRITE_ONLY)
  @JsonInclude(NON_NULL)
  @JsonDeserialize(using = StatusDeserializer.class)
  private Status status;

  @JsonProperty(access = READ_ONLY)
  @JsonInclude(NON_NULL)
  @JsonSerialize(using = StatusSerializer.class)
  private Status oldStatus;

  @JsonProperty(access = READ_ONLY)
  @JsonInclude(NON_NULL)
  @JsonSerialize(using = StatusSerializer.class)
  private Status newStatus;
}
