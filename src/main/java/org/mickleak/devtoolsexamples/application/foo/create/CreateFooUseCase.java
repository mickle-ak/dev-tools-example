package org.mickleak.devtoolsexamples.application.foo.create;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CreateFooUseCase {

	private final FooMapper fooMapper;
	private int count;

	public CreateFooResult execute(CreateFooCommand command) {
		var foo = fooMapper.toFoo( command );
		final String id = "id-%d".formatted( ++count );
		return fooMapper.toResult( id, foo );
	}
}
