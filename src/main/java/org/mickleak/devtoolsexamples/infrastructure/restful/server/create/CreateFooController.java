package org.mickleak.devtoolsexamples.infrastructure.restful.server.create;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mickleak.devtoolsexamples.application.foo.create.CreateFooUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings( "JvmTaintAnalysis" )
@RestController
@RequiredArgsConstructor
public class CreateFooController {

	private final CreateFooUseCase createFooUseCase;
	private final CreateFooRestMapper mapper;

	@PostMapping("/foo/create")
	public ResponseEntity<CreateFooResponse> create(@RequestBody @Valid CreateFooRequest request) {
		var command = mapper.toCommand(request);
		var result = createFooUseCase.execute(command);
		var response = mapper.toResponse(result);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
