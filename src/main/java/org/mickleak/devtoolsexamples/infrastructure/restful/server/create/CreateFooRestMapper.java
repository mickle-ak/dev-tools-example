package org.mickleak.devtoolsexamples.infrastructure.restful.server.create;

import org.mapstruct.*;
import org.mickleak.devtoolsexamples.application.foo.create.CreateFooCommand;
import org.mickleak.devtoolsexamples.application.foo.create.CreateFooResult;


@Mapper(componentModel = "spring")
public interface CreateFooRestMapper {

	@Mapping( target = "name", source = "fooName" )
	CreateFooCommand toCommand( CreateFooRequest request );

	@Mapping( target = "fooName", source = "name" )
	CreateFooResponse toResponse(CreateFooResult result);
}
