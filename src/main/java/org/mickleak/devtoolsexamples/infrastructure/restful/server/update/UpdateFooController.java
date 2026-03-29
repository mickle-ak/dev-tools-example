package org.mickleak.devtoolsexamples.infrastructure.restful.server.update;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.mickleak.devtoolsexamples.application.foo.update.UpdateFooUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings( "JvmTaintAnalysis" )
@RestController
@RequiredArgsConstructor
public class UpdateFooController {

	private final UpdateFooUseCase updateFooUseCase;
	private final UpdateFooRestMapper mapper;

	@PostMapping("/foo/update")
	@Nullable
	public ResponseEntity<UpdateFooResponse> create(@RequestBody @Valid UpdateFooRequest request) {
//		var command = mapper.toCommand(request);
//		var result = updateFooUseCase.execute(command);
//		var response = mapper.toResponse(result);
//		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		return null;
	}
}
