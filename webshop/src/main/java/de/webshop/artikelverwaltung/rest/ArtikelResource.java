package de.webshop.artikelverwaltung.rest;

import java.net.URI;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import de.webshop.artikelverwaltung.domain.Artikel;
import de.webshop.artikelverwaltung.service.ArtikelServiceMock;
import de.webshop.util.rest.UriHelper;
import static de.webshop.util.Constants.SELF_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
public class ArtikelResource {
	
	// This method is called if XMLis request
	
	@Inject
	private ArtikelServiceMock	as;
	
	@Inject
	private UriHelper			uriHelper;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findArtikelByID(@PathParam("id") long id, @Context UriInfo uriInfo) {
		final Artikel artikel = as.findArtikelByID(id);
		if (artikel == null) {
			// TODO Sprachabhängige Fehlermeldung
			throw new NotFoundException(
										"Der angegebene Artikel konnte leider nicht gefunden werden. Bitte überprüfen Sie die ArtikelID.");
		}
		
		return Response.ok(artikel).links(getTransitionalLinks(artikel, uriInfo)).build();
	}
	
	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public static Artikel createArtikel(@Valid Artikel artikel) {
		artikel.setID(13L);
		return artikel;
	}
	
	@PUT
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateArtikelbyID(@Valid Artikel artikel) {
		System.out.println("update Artikel");
	}
	
	private Link[] getTransitionalLinks(Artikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo)).rel(SELF_LINK).build();
		
		return new Link[] { self };
	}
	
	public URI getUriArtikel(Artikel artikel, UriInfo uriInfo) {
		return uriHelper.getUri(ArtikelResource.class, "findArtikelById", artikel.getID(), uriInfo);
	}
}
