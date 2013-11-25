package de.webshop.util.rest;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
	@Override
	public Response toResponse(NotFoundException e) {
		final String msg = e.getMessage();
		final Response response = Response.status(NOT_FOUND)
		                                  .type(TEXT_PLAIN)
		                                  .entity(msg)
		                                  .build();
		return response;
	}

}
