package org.mickleak.devtoolsexamples.infrastructure.restful.server.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CreateFooRequest(
	String id,
	@NotBlank String fooName,
	@NotBlank @Email String email
) {}
