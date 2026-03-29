package org.mickleak.devtoolsexamples.infrastructure.restful.server.update;

import jakarta.validation.constraints.NotNull;
import org.mickleak.devtoolsexamples.infrastructure.restful.server.dto.FooRestDTO;


public record UpdateFooRequest(
    @NotNull
    FooRestDTO foo
) {}
