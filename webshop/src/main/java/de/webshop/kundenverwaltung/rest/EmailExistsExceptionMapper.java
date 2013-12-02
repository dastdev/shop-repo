package de.webshop.kundenverwaltung.rest;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import de.webshop.kundenverwaltung.service.EmailExistsException;
import de.webshop.util.interceptor.Log;
import de.webshop.util.rest.Messages;


@Provider
@ApplicationScoped
@Log
public class EmailExistsExceptionMapper implements ExceptionMapper<EmailExistsException> {
	@Context
	private HttpHeaders headers;
	
	@Inject
	private Messages messages;
	
	@Override
	public Response toResponse(EmailExistsException e) {
		final String msg = messages.getMessage(headers, e.getMessageKey(), e.getEmail());
		return Response.status(BAD_REQUEST)
		               .type(TEXT_PLAIN)
		               .entity(msg)
		               .build();
	}
}
