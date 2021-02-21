package ru.protei.test.domains;

import static javax.persistence.GenerationType.IDENTITY;
import static ru.protei.test.domains.Status.OFFLINE;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.domain.Persistable;
import ru.protei.test.domains.constraints.PhoneNumber;
import ru.protei.test.domains.converters.StatusConverter;

@Entity(name = "users")
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User implements Persistable<Long>, Serializable {

  private static final long serialVersionUID = -4564476945410560023L;

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Email
  @Size(max = 128)
  @NotNull
  @NaturalId
  @Column(length = 128, unique = true, nullable = false)
  private String email;

  @Size(max = 128)
  @Column(length = 128)
  private String name;

  @Size(max = 128)
  @Column(length = 128)
  private String surname;

  @Size(max = 128)
  @PhoneNumber
  @Column(length = 128)
  private String phoneNumber;

  @Convert(converter = StatusConverter.class)
  @NotNull
  @Column(nullable = false)
  private Status status = OFFLINE;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return id == null;
  }
}
