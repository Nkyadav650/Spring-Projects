
package com.eidiko.employee.config;

import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override public void commence(HttpServletRequest request,
  HttpServletResponse response, AuthenticationException authException) throws
  IOException, ServletException {
  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	if (authException != null) {
		String exceptionClassName = authException.getClass().getName();
		System.out.println("hello");
		System.out.println(exceptionClassName);
		System.out.println(ExpiredJwtException.class.getName());
		System.out.println("hiiii");
		if (exceptionClassName.equals(ExpiredJwtException.class.getName())) {
			// Handle expired token exception
			String expiredTokenResponse = "{\"message\": \"Token has expired\"}";
			response.getWriter().write(expiredTokenResponse);
		} else {
			// Handle other authentication exceptions
			String accessDeniedResponse = "{\"message\": \"Access denied12313\"}";
			response.getWriter().write(accessDeniedResponse);
		}
	}
  
	}
	}
