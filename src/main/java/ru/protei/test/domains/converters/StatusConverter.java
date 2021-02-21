package ru.protei.test.domains.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.lang.Nullable;
import ru.protei.test.domains.Status;

@Converter
public class StatusConverter implements AttributeConverter<Status, String> {

  @Nullable
  @Override
  public String convertToDatabaseColumn(final Status attribute) {
    if (attribute == null) {
      return null;
    }
    return attribute.getName();
  }

  @Nullable
  @Override
  public Status convertToEntityAttribute(final String dbData) {
    if (dbData == null) {
      return null;
    }
    return Status.forName(dbData);
  }
}
