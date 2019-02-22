package com.toubasoft.secured;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	// @Inject
	// private EmployeDAO employeDAO;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get the HTTP Authorization header from the request
		// String authorizationHeader =
		// requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		//
		// // Check if the HTTP Authorization header is present and formatted correctly
		// if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer
		// ")) {
		// throw new NotAuthorizedException("Authorization header must be provided");
		// }
		//
		// // Extract the token from the HTTP Authorization header
		// String token = authorizationHeader.substring("Bearer".length()).trim();
		//
		// try {
		// if(token.equals("connexion")) {
		//
		// } else {
		// // Validate the token
		// validateToken(token);
		// }
		//
		//
		// } catch (Exception e) {
		// requestContext.abortWith(
		// Response.status(Response.Status.UNAUTHORIZED).build());
		// }
	}

	private void validateToken(String token) {

	}

}
