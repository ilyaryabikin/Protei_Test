package ru.protei.test.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import ru.protei.test.domains.Status;
import ru.protei.test.domains.constraints.PhoneNumber;

@Data
@Builder
public class UserDto implements Dto {

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
}
