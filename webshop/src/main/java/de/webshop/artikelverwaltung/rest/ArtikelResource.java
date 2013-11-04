package de.webshop.artikelverwaltung.rest;

import java.math.BigDecimal;
import java.net.URI;

import javax.inject.Inject;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.webshop.artikelverwaltung.domain.Artikel;
import de.webshop.artikelverwaltung.domain.Artikel.Kategorie;
import de.webshop.artikelverwaltung.service.ArtikelServiceMock;
import de.webshop.util.rest.UriHelper;
import static de.webshop.util.Constants.SELF_LINK;

/**
 * @author Mario Reinholdt
 */
@Path("/artikel")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + ";qs=0.75", MediaType.TEXT_XML + ";qs=0.5" })
@Consumes
public class ArtikelResource {
	// This method is called if XMLis request

	@Inject
	private ArtikelServiceMock as;
	
	@Inject
	private UriHelper uriHelper;
	
	@GET
	@Path("{id:[1-9][0-9]{0,7}}")
	public Response findArtikelById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
		final Artikel artikel = as.findArtikelByID(id);
		if (artikel == null) {
			// TODO Sprachabhängige Fehlermeldung
			throw new NotFoundException("Der angegebene Artikel konnte leider nicht gefunden werden. Bitte überprüfen Sie die ArtikelID.");
		}

		return Response.ok(artikel)
                       .links(getTransitionalLinks(artikel, uriInfo))
                       .build();
	}
	
	@POST
	// TODO wandle MOCK um in reale Methode
	// TODO Rückgabetyp über Erfolg oder Artikel als Rückgabewert
	public void createArtikel() {
		Artikel artikel = new Artikel();
		artikel.setID(2L);
		artikel.setArtikelnummer("AEAE99AE");
		artikel.setBezeichnung("Fahrradschlauch 26\" 255mm");
		artikel.setKurzBeschreibung("Mit diesem Fahrradschlauch haben die Pannen endlich ein Ende...");
		artikel.setBeschreibung("Der Ultra Duba Mega Schlauch 26\" mit Pannenstoppeffekt macht dem Ärger endlich ein Ende, ...");
		artikel.setKategorie(Kategorie.ERSATZTEILE);
		artikel.setLagerbestand(70);
		artikel.setPreis(new BigDecimal(14.99));
	}
	
	@PUT
	//TODO wandle MOCK in reale Methode um
	public Artikel updateArtikel(Artikel alt) {
		alt.setID(2L);
		alt.setArtikelnummer("99AA99AA");
		alt.setBezeichnung("Geupdatetes Produkt");
		alt.setKurzBeschreibung("Dieses Produkt wurde geupdatet.");
		alt.setBeschreibung("Lange Beschreibung eines aktualisierten Produktes.");
		alt.setKategorie(Kategorie.ERSATZTEILE);
		alt.setLagerbestand(12000);
		alt.setPreis(new BigDecimal(99.99));
		return alt;
	}
	
	private Link[] getTransitionalLinks(Artikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo))
                              .rel(SELF_LINK)
                              .build();

		return new Link[] { self };
	}
	
	public URI getUriArtikel(Artikel artikel, UriInfo uriInfo) {
		return uriHelper.getUri(ArtikelResource.class, "findArtikelById", artikel.getID(), uriInfo);
	}
}
