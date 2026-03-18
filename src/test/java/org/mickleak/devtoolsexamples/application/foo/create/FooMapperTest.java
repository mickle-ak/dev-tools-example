package org.mickleak.devtoolsexamples.application.foo.create;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mickleak.devtoolsexamples.domain.foo.Foo;
import org.mickleak.devtoolsexamples.domain.foo.vo.EmailVO;
import org.mickleak.devtoolsexamples.domain.foo.vo.FooIdVo;
import org.mickleak.devtoolsexamples.domain.foo.vo.NameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class FooMapperTest {

	@Autowired
	FooMapper fooMapper;


	@Nested
	class ToFoo {

		@Test
		void toFoo_setNameToNullAvoidingChecking_throwsNullPointerExceptionByMapping() {
			var command = new CreateFooCommand( "abc-123", "null", "john@example.com" );
			command.nameToAnyValue( null ); // static analyze protection

			// Foo.name is protected via lombok => exception in mapping
			assertThatThrownBy( () -> fooMapper.toFoo( command ) ).isInstanceOf( NullPointerException.class );
		}

		@Test
		void toFoo_tryToSetNameToNull_throwsNullPointerExceptionBySet() {
			var command = new CreateFooCommand( "abc-123", "null", "john@example.com" );

			// CreateFooCommand.name is protected via lombok private setter => exception in set name
			assertThatThrownBy( () -> command.nameToNonNullOnly( null ) ).isInstanceOf( NullPointerException.class );
		}

		@Test
		void toFoo_nullEmail_throwsNullPointerException() {
			var command = new CreateFooCommand( "abc-123", "John Doe", "null" );
			setFiledToNull( command, CreateFooCommand.class, "email" );

			// Foo.email is protected via lombok => exception in mapping
			assertThatThrownBy( () -> fooMapper.toFoo( command ) ).isInstanceOf( NullPointerException.class );
		}

		@Test
		void toFoo_happyPath() {
			var command = new CreateFooCommand( "abc-123", "John Doe", "john@example.com" );

			Foo foo = fooMapper.toFoo( command );

			assertThat( foo.getId() ).isNotNull();
			assertThat( foo.getId().getValue() ).isEqualTo( "abc-123" );
			assertThat( foo.getName().getValue() ).isEqualTo( "John Doe" );
			assertThat( foo.getEmail().getValue() ).isEqualTo( "john@example.com" );
			assertThat( foo.isActive() ).isFalse();
			assertThat( foo.isVerified() ).isFalse();
		}
	}

	@Nested
	class ToResult {

		@Test
		void toResult_idIsNull_throwsNullPointerException() {
			Foo foo = Foo.builder()
			             // id doesn't set in builder
			             .name( new NameVO( "No ID" ) )
			             .email( new EmailVO( "noid@example.com" ) )
			             .build();

			// CreateFooResult.id is non-nullable and protected via lombok => exception in mapping
			assertThatThrownBy( () -> fooMapper.toResultWithIdFromFoo( foo ) ).isInstanceOf( NullPointerException.class );
		}

		@Test
		void toResult_nameIsNull_throwsNullPointerException() {
			Foo foo = Foo.builder()
			             .id( new FooIdVo( "invalid-email" ) )
			             .name( new NameVO( "No ID" ) )
			             .email( new EmailVO( "noid@example.com" ) )
			             .build();
			setFiledToNull( foo, Foo.class, "name" );

			// CreateFooResult.name is protected via lombok => exception in mapping
			assertThatThrownBy( () -> fooMapper.toResult( "some-id", foo ) ).isInstanceOf( NullPointerException.class );
		}

		@Test
		void toResult_emailIsNotProtected_NullabilityViolation() {
			Foo foo = Foo.builder()
			             .id( new FooIdVo( "invalid-email" ) )
			             .name( new NameVO( "No ID" ) )
			             .email( new EmailVO( "noid@example.com" ) )
			             .build();
			setFiledToNull( foo, Foo.class, "email" );

			final CreateFooResult result = fooMapper.toResult( "id", foo );

			// CreateFooResult.email is non-nullable, but it doesn't have lombok's @NonNull => NULLABILITY VIOLATION
			assertThat( result.email() ).isNull(); // not protected! neither in editor or in compile time nor in runtime
		}

		@Test
		void toResult_happyPath() {
			Foo foo = Foo.builder()
			             .id( new FooIdVo( "xyz-999" ) )
			             .name( new NameVO( "Jane Doe" ) )
			             .email( new EmailVO( "jane@example.com" ) )
			             .build();
			foo.activate();
			foo.setToVerified();

			CreateFooResult result = fooMapper.toResult( "another-id", foo );

			assertThat( result.id() ).isEqualTo( "another-id" );
			assertThat( result.name() ).isEqualTo( "Jane Doe" );
			assertThat( result.email() ).isEqualTo( "jane@example.com" );
			assertThat( result.active() ).isTrue();
			assertThat( result.verified() ).isTrue();
		}

		@Test
		void toResultWithIdFromFoo_happyPath() {
			Foo foo = Foo.builder()
			             .id( new FooIdVo( "xyz-999" ) )
			             .name( new NameVO( "Jane Doe" ) )
			             .email( new EmailVO( "jane@example.com" ) )
			             .build();
			foo.activate();
			foo.setToVerified();

			CreateFooResult result = fooMapper.toResultWithIdFromFoo( foo );

			assertThat( result.id() ).isEqualTo( "xyz-999" );
			assertThat( result.name() ).isEqualTo( "Jane Doe" );
			assertThat( result.email() ).isEqualTo( "jane@example.com" );
			assertThat( result.active() ).isTrue();
			assertThat( result.verified() ).isTrue();
		}
	}


	private static <T> void setFiledToNull( final T foo, final Class<T> clazz, final String fieldName ) {
		final Field nameField = Objects.requireNonNull( ReflectionUtils.findField( clazz, fieldName ) );
		nameField.setAccessible( true );
		ReflectionUtils.setField( nameField, foo, null );
	}
}
