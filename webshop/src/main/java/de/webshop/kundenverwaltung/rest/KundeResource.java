package de.webshop.kundenverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import static de.webshop.util.Constants.SELF_LINK;
import static de.webshop.util.Constants.LIST_LINK;
import static de.webshop.util.Constants.ADD_LINK;
import static de.webshop.util.Constants.UPDATE_LINK;
import static de.webshop.util.Constants.REMOVE_LINK;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.jboss.logging.Logger;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.bestellverwaltung.rest.BestellungResource;
import de.webshop.bestellverwaltung.service.BestellungService;
import de.webshop.kundenverwaltung.domain.Kunde;
import de.webshop.kundenverwaltung.service.KundeService;
import de.webshop.util.rest.UriHelper;

@RequestScoped
@Path("/kunde")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.75" })
@Consumes
// @Log
public class KundeResource implements Serializable {
	
	private static final long	serialVersionUID			= -3183019727204066374L;
	
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final String	KUNDEN_NACHNAME_QUERY_PARAM	= "nachname";
	
	// FIXME Kundensuche nach PLZ implementieren
	// private static final String KUNDEN_PLZ_QUERY_PARAM = "plz";
	
	private static final String	SELF_LINK					= null;
	private static final String	FIRST_LINK					= null;
	private static final String	LAST_LINK					= null;
	
	@Context
	private UriInfo				uriInfo;
	
	@Inject
	private KundeService		ks;
	
	@Inject
	private BestellungResource	bestellungResource;
	
	@Inject
	private BestellungService	bs;
	
	@Inject
	private UriHelper			uriHelper;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	// Kunde ueber ID suchen
	public Response findKundeById(@PathParam("id") Long id) {
		final Kunde kunde = ks.findKundeById(id);
		if (kunde == null) {
			throw new NotFoundException(String.format("Kein Kunde mit der ID %d gefunden.", id));
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
		return uriHelper.getUri(KundeResource.class, "findBestellungenByKundeId", kunde.getID(),
								uriInfo);
	}
	
	private URI getUriBestellungen(Kunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class, "findBestellungenByKundeId", kunde.getID(),
								uriInfo);
	}
	
	private Link[] getTransitionalLinks(Kunde kunde, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriKunde(kunde, uriInfo)).rel(SELF_LINK)
								.build();
		
		final Link list = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
								.rel(LIST_LINK).build();
		
		final Link add = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
								.rel(ADD_LINK).build();
		
		final Link update = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
								.rel(UPDATE_LINK).build();
		
		final Link remove = Link.fromUri(uriHelper.getUri(KundeResource.class, "deleteKunde",
																kunde.getID(), uriInfo))
								.rel(REMOVE_LINK).build();
		
		return new Link[] {self, list, add, update, remove };
	}
	
	public URI getUriKunde(Kunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class, "findKundeById", kunde.getID(), uriInfo);
	}
	
	public Response findKundeByNachname(@QueryParam(KUNDEN_NACHNAME_QUERY_PARAM) String nachname) {
		List<Kunde> kunden = null;
		if (nachname != null) {
			kunden = ks.findKundenByNachname(nachname);
			if (kunden.isEmpty()) {
				throw new NotFoundException(String.format("Kein Kunde mit Nachname %s gefunden.",
															nachname));
			}
		}
		else {
			kunden = ks.findAllKunden();
			if (kunden.isEmpty()) {
				throw new NotFoundException("Keine Kunden vorhanden.");
			}
		}
		
		for (Kunde k : kunden) {
			setStructuralLinks(k, uriInfo);
		}
		
		return Response.ok(new GenericEntity<List<Kunde>>(kunden) { })
						.links(getTransitionalLinksKunden(kunden, uriInfo)).build();
	}
	
	private Link[] getTransitionalLinksKunden(List<Kunde> kunden, UriInfo uriInfo2) {
		if (kunden == null || kunden.isEmpty()) {
			return null;
		}
		
		final Link first = Link.fromUri(getUriKunde(kunden.get(0), uriInfo)).rel(FIRST_LINK)
								.build();
		final int lastPos = kunden.size() - 1;
		
		final Link last = Link.fromUri(getUriKunde(kunden.get(lastPos), uriInfo)).rel(LAST_LINK)
								.build();
		
		return new Link[] {first, last};
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}/bestellungen")
	public Response findBestellungenByKundeId(@PathParam("id") Long kundeId) {
		final Kunde kunde = ks.findKundeById(kundeId);
		if (kunde == null) {
			throw new NotFoundException(
										String.format("Es wurden keine Bestellungen fuer den Kunden %d gefunden",
														kundeId));
		}
		final List<Bestellung> bestellungen = bs.findBestellungenByKunde(kunde);
		// URIs innerhalb der gefundenen Bestellungen anpassen
		if (bestellungen != null) {
			for (Bestellung bestellung : bestellungen) {
				bestellungResource.setStructuralLinks(bestellung, uriInfo);
			}
		}
		
		return Response.ok(new GenericEntity<List<Bestellung>>(bestellungen) { })
						.links(getTransitionalLinksBestellungen(bestellungen, kunde, uriInfo))
						.build();
	}
	
	private Link[] getTransitionalLinksBestellungen(List<Bestellung> bestellungen, Kunde kunde,
			UriInfo uriInfo2) {
		if (bestellungen == null || bestellungen.isEmpty()) {
			return new Link[0];
		}
		
		final Link self = Link.fromUri(getUriBestellungen(kunde, uriInfo)).rel(SELF_LINK).build();
		
		final Link first = Link.fromUri(bestellungResource.getUriBestellung(bestellungen.get(0),
																			uriInfo))
								.rel(FIRST_LINK).build();
		
		final int lastPos = bestellungen.size() - 1;
		final Link last = Link.fromUri(bestellungResource.getUriBestellung(bestellungen.get(lastPos),
																			uriInfo))
								.rel(LAST_LINK).build();
		
		return new Link[] {self, first, last};
	}
	
	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createKunde(@Valid Kunde kunde) {
		kunde = ks.createKunde(kunde);
		
		LOGGER.tracef("Kunde: %s", kunde);
		return Response.created(getUriKunde(kunde, uriInfo)).build();
	}
	
	@PUT
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateKunde(@Valid Kunde kunde) {
		ks.updateKunde(kunde);
		LOGGER.tracef("Kunde: %s", kunde);
	}
	
	@DELETE
	@Path("{id:[1-9][0-9]*}")
	@Produces
	public void deleteKunde(@PathParam("id") Long kundeId) {
		ks.deleteKunde(kundeId);
	}
}
