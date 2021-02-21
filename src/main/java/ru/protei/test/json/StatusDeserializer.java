package ru.protei.test.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import org.springframework.lang.Nullable;
import ru.protei.test.domains.Status;

public class StatusDeserializer extends StdDeserializer<Status> {

  private static final long serialVersionUID = -3933434405517630818L;

  public StatusDeserializer() {
    super(Status.class);
  }

  public StatusDeserializer(final Class<Status> statusClass) {
    super(statusClass);
  }

  @Nullable
  @Override
  public Status deserialize(final JsonParser p, final DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    final JsonNode node = p.getCodec().readTree(p);
    final JsonNode statusNode = node.get("status");
    if (statusNode == null) {
      return null;
    }
    final String statusName = statusNode.asText();
    return Status.forName(statusName);
  }
}
