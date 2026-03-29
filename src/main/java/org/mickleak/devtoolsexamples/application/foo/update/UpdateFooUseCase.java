package org.mickleak.devtoolsexamples.application.foo.update;


import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UpdateFooUseCase {

	private final UpdateFooMapper fooMapper;
	private int count;

	@Nullable
	public UpdateFooResult execute(UpdateFooCommand command) {
//		var foo = fooMapper.updateFoo( command );
//		final String id = "id-%d".formatted( ++count );
//		return fooMapper.toResult( id, foo );
		return null;
	}
}
