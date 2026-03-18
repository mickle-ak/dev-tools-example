package org.mickleak.devtoolsexamples.application.foo.create;

import lombok.NonNull;


public record CreateFooResult(
	@NonNull String id,  // protected from lombok in constructor
	@NonNull String name,
	String email, // not protected! neither in editor or in compile time nor in runtime
	boolean active,
	boolean verified
) {}
