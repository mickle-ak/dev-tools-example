package org.mickleak.devtoolsexamples.infrastructure.restful.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FooRestDTO(
        @NotBlank
        String id,

        @NotNull
        NameRestDTO name,

        @NotNull
        EmailRestDTO email
) {}
