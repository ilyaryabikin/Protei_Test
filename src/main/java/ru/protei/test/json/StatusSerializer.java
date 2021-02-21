package ru.protei.test.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import ru.protei.test.domains.Status;

public class StatusSerializer extends StdSerializer<Status> {

  private static final long serialVersionUID = 5457334882352487243L;

  public StatusSerializer() {
    super(Status.class);
  }

  public StatusSerializer(final Class<Status> statusClass) {
    super(statusClass);
  }

  @Override
  public void serialize(
      final Status value, final JsonGenerator gen, final SerializerProvider provider)
      throws IOException {
    gen.writeStringField("status", value.getName());
  }
}
