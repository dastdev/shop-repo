package de.webshop.util.rest;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import java.lang.invoke.MethodHandles;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import de.webshop.util.AbstractShopException;
import de.webshop.util.interceptor.Log;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Provider
@ApplicationScoped
@Log
public class ShopExceptionMapper implements ExceptionMapper<AbstractShopException> {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Override
	public Response toResponse(AbstractShopException e) {
		LOGGER.warnf(e, "Kein ExceptionMapper fuer die Exception %s", e.getClass().getSimpleName());
		final Response response = Response.status(INTERNAL_SERVER_ERROR)
		                                  .build();
		return response;
	}

}
