package org.mickleak.devtoolsexamples.application.foo.create;

import lombok.*;
import org.jspecify.annotations.Nullable;


/**
 * This is a class for test only, to allow set fields to null in tests.
 * In the "real word" is should be a record!
 */
@AllArgsConstructor
@Getter
public class CreateFooCommand {
	@Nullable String id;

	@Setter(AccessLevel.PRIVATE) // use private setters to set value with nullability check
	@NonNull String name;

	@NonNull String email;


	/**
	 * For test only. It doesn't allow to set name to null,
	 * because it uses private setter generated from lombok!
	 */
	public void nameToNonNullOnly( String name ) {
		setName( name );
	}

	/**
	 * For test only. It avoids lombok's null-check and allows to set name to null in tests.
	 *
	 */
	public void nameToAnyValue( String name ) { // for test
		this.name = name;
	}
}
