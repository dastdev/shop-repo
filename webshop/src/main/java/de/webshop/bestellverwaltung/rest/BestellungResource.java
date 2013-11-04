package de.webshop.bestellverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static de.webshop.util.Constants.FIRST_LINK;
import static de.webshop.util.Constants.LAST_LINK;
import static de.webshop.util.Constants.SELF_LINK;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.bestellverwaltung.domain.Position;
import de.webshop.kundenverwaltung.domain.Kunde;
import de.webshop.kundenverwaltung.rest.KundeResource;
import de.webshop.util.Mock;
import de.webshop.util.rest.UriHelper;

@Path("/bestellung")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
public class BestellungResource {
	
	@Context
	private UriInfo				uriInfo;
	
	@Inject
	private UriHelper			uriHelper;
	
	@Inject
	private KundeResource		kundeResource;
	
	@Inject
	private PositionResource	positionResource;
	
	@GET
	@Produces({ TEXT_PLAIN, APPLICATION_JSON })
	@Path("version")
	public String getVersion() {
		return "1.0";
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findBestellungById(@PathParam("id") long id) {
		
		final Bestellung bestellung = Mock.findBestellungById(id);
		
		if (bestellung == null) {
			throw new NotFoundException(
										String.format(	"Keine Bestellung mit der ID %li gefunden.",
														id));
		}
		setStructuralLinks(bestellung, uriInfo);
		
		// Link-Header setzen
		final Response response = Response.ok(bestellung)
											.links(getTransitionalLinks(bestellung, uriInfo))
											.build();
		
		return response;
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}/positionen")
	public Response findPositionenByBestellungId(@PathParam("id") long id) {
		
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		final Bestellung bestellung = Mock.findBestellungById(id);
		final List<Position> positionen = Mock.findPositionenByBestellung(bestellung);
		
		if (positionen.isEmpty()) {
			throw new NotFoundException("Zur ID " + id + " wurden keine Position gefunden");
		}
		
		// URIs innerhalb der gefundenen Positionen anpassen
		for (Position position : positionen) {
			positionResource.setStructuralLinks(position, uriInfo);
		}
		
		return Response.ok(new GenericEntity<List<Position>>(positionen) {})
						.links(getTransitionalLinksPositionen(positionen, bestellung, uriInfo))
						.build();
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
		
		// URI fuer positionen setzen
		final List<Position> positionen = bestellung.getPositionen();
		
		if (positionen != null) {
			final URI positionenUri = getUriPositionen(bestellung, uriInfo);
			bestellung.setPositionenUri(positionenUri);
		}
	}
	
	private Link[] getTransitionalLinks(Bestellung bestellung, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriBestellung(bestellung, uriInfo)).rel(SELF_LINK)
								.build();
		return new Link[] { self };
	}
	
	private Link[] getTransitionalLinksPositionen(List<Position> positionen, Bestellung bestellung,
			UriInfo uriInfo) {
		if (positionen == null || positionen.isEmpty()) {
			return new Link[0];
		}
		
		final Link self = Link.fromUri(getUriPositionen(bestellung, uriInfo)).rel(SELF_LINK)
								.build();
		
		final Link first = Link.fromUri(positionResource.getUriPositionen(	positionen.get(0),
																			uriInfo))
								.rel(FIRST_LINK).build();
		final int lastPos = positionen.size() - 1;
		
		final Link last = Link.fromUri(	positionResource.getUriPositionen(	positionen.get(lastPos),
																			uriInfo))
								.rel(LAST_LINK).build();
		
		return new Link[] { self, first, last };
	}
	
	private URI getUriPositionen(Bestellung bestellung, UriInfo uriInfo) {
		return uriHelper.getUri(BestellungResource.class, "findPositionByBestellungId",
								bestellung.getID(), uriInfo);
	}
	
	public URI getUriBestellung(Bestellung bestellung, UriInfo uriInfo) {
		return uriHelper.getUri(BestellungResource.class, "findBestellungById", bestellung.getID(),
								uriInfo);
	}
	
}
