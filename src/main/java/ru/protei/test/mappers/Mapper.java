package ru.protei.test.mappers;

import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Component;
import ru.protei.test.dtos.Dto;
import ru.protei.test.exceptions.IllegalMappingOperationException;

@Component
public interface Mapper<T extends Persistable<?>, D extends Dto> {

  T mapToPersistable(final D dto) throws IllegalMappingOperationException;

  D mapToDto(final T persistable) throws IllegalMappingOperationException;
}
