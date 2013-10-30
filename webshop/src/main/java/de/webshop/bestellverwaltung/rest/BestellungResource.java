package de.webshop.bestellverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.kundenverwaltung.domain.Kunde;
import de.webshop.kundenverwaltung.rest.KundeResource;
import de.webshop.util.rest.UriHelper;

@Path("/bestellung")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
public class BestellungResource {
	
	@Context
	private UriInfo			uriInfo;
	
	@Inject
	private UriHelper		uriHelper;
	
	@Inject
	private KundeResource	kundeResource;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findBestellungById(@PathParam("id") long id) {
		
		// FIXME: Benutze Mock-Klasse statt new Bestellung()
		final Bestellung bestellung = new Bestellung();
		
		if (bestellung == null) {
			throw new NotFoundException(
										String.format(	"Keine Bestellung mit der ID {0} gefunden.",
														id));
		}
		setStructuralLinks(bestellung, uriInfo);
		
		// Link-Header setzen
		final Response response = Response.ok(bestellung)
											.links(getTransitionalLinks(bestellung, uriInfo))
											.build();
		
		return response;
	}
	
	public void setStructuralLinks(Bestellung bestellung, UriInfo uriInfo) {
		// URI fuer Kunde setzen
		final Kunde kunde = bestellung.getKunde();
		if (kunde != null) {
			// FIXME: kundeResource.getUriKunde(...)
			final URI kundeUri = null; // kundeResource.getUriKunde(bestellung.getKunde(),
										// uriInfo);
			bestellung.setKundeUri(kundeUri);
		}
	}
	
	private Link[] getTransitionalLinks(Bestellung bestellung, UriInfo uriInfo) {
		// FIXME: Konstante SELF_LINK statt "self"
		final Link self = Link.fromUri(getUriBestellung(bestellung, uriInfo)).rel("self").build();
		return new Link[] { self };
	}
	
	public URI getUriBestellung(Bestellung bestellung, UriInfo uriInfo) {
		return uriHelper.getUri(BestellungResource.class, "findBestellungById", bestellung.getId(),
								uriInfo);
	}
	
}
