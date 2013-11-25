package de.webshop.artikelverwaltung.rest;


import java.io.Serializable;
import java.net.URI;

import javax.faces.bean.ApplicationScoped;
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
import de.webshop.util.Mock;
import de.webshop.util.rest.UriHelper;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static de.webshop.util.Constants.SELF_LINK;


@ApplicationScoped
@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
public class ArtikelResource implements Serializable {
	
	private static final long serialVersionUID = -6459320654424521991L;

	@Context
	private UriInfo		uriInfo;
	
	@Inject
	private UriHelper	uriHelper;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findArtikelByID(@PathParam("id") long id) {
		
		final Artikel artikel = Mock.findArtikelByID(id);
		
		if (artikel == null) {
			throw new NotFoundException(String.format("Keine Position mit der ID %d gefunden.", id));
		}
		
		// Link-Header setzen
		final Response response = Response.ok(artikel)
											.links(getTransitionalLinks(artikel, uriInfo)).build();
		
		return response;
	}
	
	private Link[] getTransitionalLinks(Artikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo)).rel(SELF_LINK).build();
		return new Link[] { self };
	}
	
	public URI getUriArtikel(Artikel artikel, UriInfo uriInfo) {
		return uriHelper.getUri(ArtikelResource.class, "findArtikelByID", artikel.getID(), uriInfo);
	}
	
	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createArtikel(@Valid Artikel artikel) {
		
		artikel = Mock.createArtikel(artikel);
		return Response.created(getUriArtikel(artikel, uriInfo)).build();
	}
	
	@PUT
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateArtikel(@Valid Artikel artikel) {
		Mock.updateArtikel(artikel);
	}
}
