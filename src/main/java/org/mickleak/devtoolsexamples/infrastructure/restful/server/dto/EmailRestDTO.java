package org.mickleak.devtoolsexamples.infrastructure.restful.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRestDTO(
        @NotBlank @Email
        String email
) {}
