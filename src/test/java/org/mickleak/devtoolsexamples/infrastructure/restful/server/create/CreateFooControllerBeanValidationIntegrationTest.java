package org.mickleak.devtoolsexamples.infrastructure.restful.server.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CreateFooControllerBeanValidationIntegrationTest {

	public static final String CREATE_FOO_PATH = "/foo/create";

	@Autowired
	MockMvc mockMvc;

	@Test
	void create_validRequest_returns201() throws Exception {
		mockMvc.perform( post( CREATE_FOO_PATH )
				.contentType( MediaType.APPLICATION_JSON )
				.content( """
						{"fooName": "John Doe", "email": "john@example.com"}
						""" ) )
		       .andExpect( status().isCreated() );
	}

	@Test
	void create_missingName_returns400() throws Exception {
		mockMvc.perform( post( CREATE_FOO_PATH )
				.contentType( MediaType.APPLICATION_JSON )
				.content( """
						{"id": "abc-123", "email": "john@example.com"}
						""" ) )
		       .andExpect( status().isBadRequest() );
	}

	@Test
	void create_blankName_returns400() throws Exception {
		mockMvc.perform( post( CREATE_FOO_PATH )
				.contentType( MediaType.APPLICATION_JSON )
				.content( """
						{"id": "abc-123", "fooName": "", "email": "john@example.com"}
						""" ) )
		       .andExpect( status().isBadRequest() );
	}

	@Test
	void create_missingEmail_returns400() throws Exception {
		mockMvc.perform( post( CREATE_FOO_PATH )
				.contentType( MediaType.APPLICATION_JSON )
				.content( """
						{"id": "abc-123", "fooName": "John Doe"}
						""" ) )
		       .andExpect( status().isBadRequest() );
	}

	@Test
	void create_invalidEmailFormat_returns400() throws Exception {
		mockMvc.perform( post( CREATE_FOO_PATH )
				.contentType( MediaType.APPLICATION_JSON )
				.content( """
						{"id": "abc-123", "fooName": "John Doe", "email": "not-an-email"}
						""" ) )
		       .andExpect( status().isBadRequest() );
	}
}
