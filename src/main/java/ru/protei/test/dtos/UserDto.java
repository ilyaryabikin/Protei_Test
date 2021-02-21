package ru.protei.test.dtos;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.protei.test.domains.Status;
import ru.protei.test.domains.constraints.PhoneNumber;
import ru.protei.test.json.StatusDeserializer;
import ru.protei.test.json.StatusSerializer;

@Data
@NoArgsConstructor
@Builder
public class UserDto implements Dto {

  @JsonProperty(access = READ_ONLY)
  private Long id;

  @Email
  @Size(max = 128)
  @NotNull
  private String email;

  @Size(max = 128)
  private String name;

  @Size(max = 128)
  private String surname;

  @Size(max = 128)
  @PhoneNumber
  private String phoneNumber;

  @JsonSerialize(using = StatusSerializer.class)
  @JsonDeserialize(using = StatusDeserializer.class)
  private Status status;
}
