package org.mickleak.devtoolsexamples.infrastructure.restful.server.create;

import lombok.NonNull;


public record CreateFooResponse(
	@NonNull String id,
	@NonNull String fooName,
	@NonNull String email,
	boolean active,
	boolean verified
) {}
