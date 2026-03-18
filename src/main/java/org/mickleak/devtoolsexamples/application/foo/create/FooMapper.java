package org.mickleak.devtoolsexamples.application.foo.create;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mickleak.devtoolsexamples.application.foo.GenericValueObjectMapper;
import org.mickleak.devtoolsexamples.domain.foo.Foo;


@Mapper(componentModel = "spring", uses = GenericValueObjectMapper.class)
public interface FooMapper {

	@BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	Foo toFoo(CreateFooCommand command);

	@Mapping(source = "id", target = "id")
	CreateFooResult toResult( String id, Foo foo );

	CreateFooResult toResultWithIdFromFoo( Foo foo ); // only to show, that only lombok can protect here
}
