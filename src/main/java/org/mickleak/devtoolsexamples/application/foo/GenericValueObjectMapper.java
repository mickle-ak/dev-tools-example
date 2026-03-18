package org.mickleak.devtoolsexamples.application.foo;

import org.jspecify.annotations.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.TargetType;
import org.mickleak.devtoolsexamples.domain.foo.vo.BaseVO;


@Mapper(componentModel = "spring")
public abstract class GenericValueObjectMapper {

	public @Nullable <T> T unwrap(@Nullable BaseVO<T> vo) {
		return vo == null ? null : vo.getValue();
	}

	public @Nullable <V extends BaseVO<T>, T> V wrap(@Nullable T value, @TargetType Class<V> targetType) {
		if( value == null ) return null;
		try {
			return targetType.getConstructor( value.getClass() ).newInstance( value );
		} catch( Exception e ) {
			throw new IllegalArgumentException(
				"Cannot wrap value of type " + value.getClass().getSimpleName()
					+ " into " + targetType.getSimpleName(), e );
		}
	}
}
