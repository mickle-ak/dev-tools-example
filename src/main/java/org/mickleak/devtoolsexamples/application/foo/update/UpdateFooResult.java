package org.mickleak.devtoolsexamples.application.foo.update;

import lombok.NonNull;
import org.mickleak.devtoolsexamples.domain.foo.Foo;

public record UpdateFooResult(
        @NonNull Foo foo
) {}
