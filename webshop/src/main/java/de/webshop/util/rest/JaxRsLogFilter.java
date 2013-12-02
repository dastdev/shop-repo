package de.webshop.util.rest;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.security.Principal;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Provider
@ApplicationScoped
public class JaxRsLogFilter implements ContainerRequestFilter, ContainerResponseFilter {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Override
	public void filter(ContainerRequestContext requestCtx) throws IOException {
		LOGGER.debugf("URI: %s", requestCtx.getUriInfo().getAbsolutePath());
		LOGGER.debugf("Method: %s", requestCtx.getMethod());
		LOGGER.debugf("Acceptable Media Types: %s", requestCtx.getAcceptableMediaTypes());
		LOGGER.debugf("Content Type: %s", requestCtx.getHeaderString("content-type"));
		final SecurityContext securityCtx = requestCtx.getSecurityContext();
		if (securityCtx == null) {
			LOGGER.debug("Security Context: null");
		}
		else {
			LOGGER.debugf("Authentication Scheme: %s", securityCtx.getAuthenticationScheme());
			final Principal principal = securityCtx.getUserPrincipal();
			final String principalName = principal == null ? null : principal.getName();
			LOGGER.debugf("Principal: %s", principalName);
		}
		LOGGER.debugf("Authorization: %s", requestCtx.getHeaderString("authorization"));
		LOGGER.debugf("Acceptable Languages: %s", requestCtx.getAcceptableLanguages());
	}

	@Override
	public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) throws IOException {
		LOGGER.debugf("Status Info: %d %s", responseCtx.getStatus(), responseCtx.getStatusInfo());
		LOGGER.debugf("Location: %s", responseCtx.getLocation());		
	}
}
