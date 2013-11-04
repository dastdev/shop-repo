package de.webshop.artikelverwaltung.rest;

import java.math.BigDecimal;
import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
	public Response findArtikelByID(@PathParam("id") Long id, @Context UriInfo uriInfo) {
		final Artikel artikel = as.findArtikelByID(id);
		if (artikel == null) {
			// TODO Sprachabhängige Fehlermeldung
			throw new NotFoundException("Der angegebene Artikel konnte leider nicht gefunden werden. Bitte überprüfen Sie die ArtikelID.");
		}

		return Response.ok(artikel)
                       .links(getTransitionalLinks(artikel, uriInfo))
                       .build();
	}
	
//	@POST
//	// TODO wandle MOCK um in reale Methode
//	// TODO Rückgabetyp über Erfolg oder Artikel als Rückgabewert
//	public static Artikel createArtikel(	@FormParam("ID") Long ID,
//									@FormParam("artikelnummer") String Artikelnummer,
//									@FormParam("bezeichnung") String Bezeichnung,
//									@FormParam("kurzBeschreibung") String Kurzbeschreibung,
//									@FormParam("beschreibung") String Beschreibung,
//									@FormParam("kategorie") Kategorie Kategorie,
//									@FormParam("lagerbestand") Integer Lagerbestand,
//									@FormParam("preis") BigDecimal Preis									
//									){
//		Artikel artikel = new Artikel();
//		artikel.setID(ID);
//		artikel.setArtikelnummer(Artikelnummer);
//		artikel.setBezeichnung(Bezeichnung);
//		artikel.setKurzBeschreibung(Kurzbeschreibung);
//		artikel.setBeschreibung(Beschreibung);
//		artikel.setKategorie(Kategorie);
//		artikel.setLagerbestand(Lagerbestand);
//		artikel.setPreis(Preis);
//		return artikel;
//	}
//	
//	@PUT
//	//TODO wandle MOCK in reale Methode um
//	public Response updateArtikelbyID(	@FormParam("ID") Long ID,
//										@FormParam("artikelnummer") String Artikelnummer,
//										@FormParam("bezeichnung") String Bezeichnung,
//										@FormParam("kurzBeschreibung") String Kurzbeschreibung,
//										@FormParam("beschreibung") String Beschreibung,
//										@FormParam("kategorie") Kategorie Kategorie,
//										@FormParam("lagerbestand") Integer Lagerbestand,
//										@FormParam("preis") BigDecimal Preis,
//										@Context UriInfo uriInfo
//									){
//		final Artikel alt = as.findArtikelByID(ID);
//		alt.setID(ID);
//		alt.setArtikelnummer(Artikelnummer);
//		alt.setBezeichnung(Bezeichnung);
//		alt.setKurzBeschreibung(Kurzbeschreibung);
//		alt.setBeschreibung(Beschreibung);
//		alt.setKategorie(Kategorie);
//		alt.setLagerbestand(Lagerbestand);
//		alt.setPreis(Preis);
//
//		return Response.accepted(alt)
//                .links(getTransitionalLinks(alt, uriInfo))
//                .build();
//		
//	}
	
	private Link[] getTransitionalLinks(Artikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo))
                              .rel(SELF_LINK)
                              .build();

		return new Link[] { self };
	}
	
	public URI getUriArtikel(Artikel artikel, UriInfo uriInfo) {
		return uriHelper.getUri(ArtikelResource.class, "findArtikelByID", artikel.getID(), uriInfo);
	}
}
