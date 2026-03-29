package org.mickleak.devtoolsexamples.application.foo.update;


import org.mapstruct.Mapper;
import org.mickleak.devtoolsexamples.application.foo.GenericValueObjectMapper;

@Mapper(componentModel = "spring", uses = GenericValueObjectMapper.class)
public interface UpdateFooMapper {
}
