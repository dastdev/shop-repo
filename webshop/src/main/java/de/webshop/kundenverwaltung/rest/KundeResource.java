package de.webshop.kundenverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import de.webshop.util.interceptor.Log;

@Path("/kunde")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.75" })
@Consumes
@Log
public class KundeResource {
	
	@GET
	public Response findAllKunden() {
		// FIXME Code eingeben
		return null;
	}
	/* Hallllllllllllo */
	
}
