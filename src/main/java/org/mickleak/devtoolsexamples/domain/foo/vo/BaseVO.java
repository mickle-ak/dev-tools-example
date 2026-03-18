package org.mickleak.devtoolsexamples.domain.foo.vo;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public abstract class BaseVO<T> {
	@NonNull
	private final T value;
}
