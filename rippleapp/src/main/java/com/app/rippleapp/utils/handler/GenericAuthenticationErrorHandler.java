package com.app.rippleapp.utils.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.app.rippleapp.bean.RestApiErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericAuthenticationErrorHandler implements AuthenticationFailureHandler {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		final RestApiErrorMessage restApiErrorMessage = new RestApiErrorMessage(HttpStatus.UNAUTHORIZED,
				exception.getLocalizedMessage(), exception.getMessage());

		response.getOutputStream().println(objectMapper.writeValueAsString(restApiErrorMessage));

	}

}
