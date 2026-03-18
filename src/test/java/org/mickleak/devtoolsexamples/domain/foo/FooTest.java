package org.mickleak.devtoolsexamples.domain.foo;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mickleak.devtoolsexamples.domain.foo.vo.EmailVO;
import org.mickleak.devtoolsexamples.domain.foo.vo.FooIdVo;
import org.mickleak.devtoolsexamples.domain.foo.vo.NameVO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class FooTest {

	@Test
	void nullabilityErrorFromLombok_nameVoShouldBeNotNull() { // static + lombok + null-away
		assertThatThrownBy( () -> Foo.builder()
		                             .id(new FooIdVo( "42" ) )
		                             .name( getName() )
		                             .email( new EmailVO( "name@domain.de" ) )
		                             .build() )
			.isInstanceOf( NullPointerException.class );
	}

	private static @Nullable NameVO getName() {
		return null;
	}


	@Test
	void nullabilityErrorFromLombok_nameShouldBeNotNull() { // static + lombok + null-away
		assertThatThrownBy( () -> new NameVO( null ) )
			.isInstanceOf( NullPointerException.class );
	}

	@Test
	void nullabilityErrorFromLombok_notAllRequiredFieldsAreSet() { // lombok only
		final Foo.FooBuilder builder = Foo.builder()
		                               .id( new FooIdVo( "42" ) )
		                               .name( new NameVO( "name" ) );
		// email not defined in builder => NullPointerException in build() - lombok's protection
		assertThatThrownBy( builder::build ).isInstanceOf( NullPointerException.class );
	}

	@Test
	@Disabled("we need to change implementation of isVerifiedWithPossibleNullPointerException for null-away")
	void isVerifiedWithPossibleNullPointerException() {
		var foo = Foo.builder()
		             .id(new FooIdVo( "42" ) )
		             .name( new NameVO( "name" ) )
		             .email( new EmailVO( "name@domain.dom" ) )
		             .build();
		assertThatThrownBy( foo::isVerifiedWithPossibleNullPointerException )
			.isInstanceOf( NullPointerException.class );
	}

	@Test
	void createWithoutId() {
		var foo = Foo.builder()
		             .name( new NameVO( "name" ) )
		             .email( new EmailVO( "n@d.d" ) )
		             .build();
		foo.activate();

		// no id set => id == null, but it is allowed because Foo.id is nullable
		assertThat(foo)
			.extracting(
				Foo::getId,
				foo1 -> foo1.getName().getValue(),
				foo2 -> foo2.getEmail().getValue(),
				Foo::canBeUsed,
				Foo::isActive,
				Foo::isVerified )
			.containsExactly( null, "name", "n@d.d", false, true, false );
	}
}