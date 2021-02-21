package ru.protei.test.domains;

import static javax.persistence.GenerationType.IDENTITY;
import static ru.protei.test.domains.Status.OFFLINE;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.domain.Persistable;
import ru.protei.test.domains.constraints.PhoneNumber;
import ru.protei.test.domains.converters.StatusConverter;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true)
public class User implements Persistable<Long>, Serializable {

  private static final long serialVersionUID = -4564476945410560023L;

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Email
  @Size(max = 128)
  @NotNull
  @NaturalId
  @Column(length = 128, unique = true, nullable = false)
  @EqualsAndHashCode.Include
  private String email;

  @Size(max = 128)
  @NotNull
  @Column(length = 128, nullable = false)
  private String name;

  @Size(max = 128)
  @NotNull
  @Column(length = 128, nullable = false)
  private String surname;

  @Size(max = 128)
  @PhoneNumber
  @NotNull
  @Column(length = 128, nullable = false)
  private String phoneNumber;

  @Convert(converter = StatusConverter.class)
  @NotNull
  @Column(nullable = false)
  @Builder.Default
  private Status status = OFFLINE;

  @PastOrPresent
  @Column(name = "last_online_change")
  private Instant lastOnlineChange;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return id == null;
  }
}
