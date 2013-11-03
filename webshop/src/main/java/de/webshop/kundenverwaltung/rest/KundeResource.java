package de.webshop.kundenverwaltung.rest;

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
import de.webshop.bestellverwaltung.rest.BestellungResource;
import de.webshop.kundenverwaltung.domain.Kunde;
import de.webshop.util.Constants;
import de.webshop.util.Mock;
import de.webshop.util.interceptor.Log;
import de.webshop.util.rest.UriHelper;

@Path("/kunde")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.75" })
@Consumes
@Log
public class KundeResource {
	
	private static final String	KUNDEN_NACHNAME_QUERY_PARAM	= "nachname";
	
	private static final String	KUNDEN_PLZ_QUERY_PARAM		= "plz";
	
	@Context
	private UriInfo				uriInfo;
	
	@Inject
	private BestellungResource	bestellungResource;
	
	@Inject
	private UriHelper			uriHelper;
	
	// TODO "findAllKunden", "findKundeByNachname"... (Klasse "KundeServe"?)
	/*
	 * @GET public Response
	 * findAllKunden(@QueryParam(KUNDEN_NACHNAME_QUERY_PARAM) //FIXME Pattern
	 * "Nachname_Pattern"? Woher? //@Pattern(regexp = Kunde.NACHNAME_PATTERN,
	 * message = "{kunde.nachname.pattern}") String nachname,
	 * 
	 * @QueryParam(KUNDEN_PLZ_QUERY_PARAM)
	 * 
	 * @Pattern(regexp = "\\d{5}", message = "{adresse.plz}") String plz) {
	 * List<Kunde> kunden = null; if (nachname != null) { //return null; } }
	 */
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	// Kunde über ID suchen
	public Response findKundeById(@PathParam("id") Long id) {
		final Kunde kunde = Mock.findKundeById(id);
		if (kunde == null) {
			throw new NotFoundException(String.format("Kein Kunde mit der ID {0] gefunden.", id));
		}
		setStructuralLinks(kunde, uriInfo);
		// Link-Header setzen
		final Response response = Response.ok(kunde).links(getTransitionalLinks(kunde, uriInfo))
											.build();
		return response;
	}
	
	public void setStructuralLinks(Kunde kunde, UriInfo uriInfo) {
		// URI fuer Bestellung setzen
		final URI bestellung = getUriBestellung(kunde, uriInfo);
		kunde.setUriBestellung(bestellung);
	}
	
	private URI getUriBestellung(Kunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class, "findBestellungenByKundeId", kunde.getId(),
								uriInfo);
	}
	
	private Link[] getTransitionalLinks(Kunde kunde, UriInfo uriInfo) {
		// FIXME LINK_LIST einfügen?
		final Link self = Link.fromUri(getUriKunde(kunde, uriInfo)).rel(Constants.SELF_LINK)
								.build();
		
		final Link add = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
								.rel(Constants.ADD_LINK).build();
		
		final Link update = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
								.rel(Constants.UPDATE_LINK).build();
		
		final Link remove = Link.fromUri(	uriHelper.getUri(	KundeResource.class, "deleteKunde",
																kunde.getId(), uriInfo))
								.rel(Constants.REMOVE_LINK).build();
		
		// TODO "list" einfügen
		return new Link[] { self, add, update, remove };
	}
	
	private URI getUriKunde(Kunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class, "findKundeById", kunde.getId(), uriInfo);
	}
	
}
