package de.webshop.util.rest;

import java.io.Serializable;
import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen
 *         Zimmermann</a>
 */
@ApplicationScoped
public class UriHelper implements Serializable {
	
	private static final long serialVersionUID = -6300713312190086184L;

	public URI getUri(Class<?> clazz, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder().path(clazz).build();
	}
	
	public URI getUri(Class<?> clazz, String methodName, Long id, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder().path(clazz).path(clazz, methodName).build(id);
	}
}
