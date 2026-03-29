package org.mickleak.devtoolsexamples.infrastructure.restful.server.dto;

import jakarta.validation.constraints.NotBlank;

public record NameRestDTO(
        @NotBlank
        String name
) {}
