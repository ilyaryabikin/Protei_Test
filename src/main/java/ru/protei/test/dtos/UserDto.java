package ru.protei.test.dtos;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.protei.test.domains.Status;
import ru.protei.test.domains.constraints.PhoneNumber;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

  private Status status;

  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSXXX", timezone = "UTC")
  private Instant lastOnlineChange;
}
