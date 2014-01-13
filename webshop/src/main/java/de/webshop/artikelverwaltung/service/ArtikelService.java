package de.webshop.artikelverwaltung.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import de.webshop.artikelverwaltung.domain.Artikel;
import de.webshop.artikelverwaltung.domain.Artikel.Kategorie;
import de.webshop.util.interceptor.Log;

@Log
@Dependent
public class ArtikelService implements Serializable {

	private static final long serialVersionUID = -3006652195760480390L;

	@Inject
	private EntityManager em;
	
	// / Gibt eine Artikelinstanz des gesuchten Artikels via ID zurueck
	public Artikel findArtikelById(Long id) {
		return em.find(Artikel.class, id);

//		final Artikel artikel = new Artikel();
//		artikel.setID(id);
//		artikel.setArtikelnummer("R2D2uC3PO");
//		artikel.setBezeichnung("Robobike");
//		artikel.setKurzBeschreibung("Das Robobike weiss wohin ...");
//		artikel.setBeschreibung("Lange Robobikebeschreibu...........");
//		artikel.setKategorie(Kategorie.KOMPLETTRAEDER);
//		final Integer tempLaBe = new Integer(11);
//		artikel.setLagerbestand(tempLaBe);
//		final BigDecimal tempPreis = new BigDecimal(1337.55);
//		artikel.setPreis(tempPreis);
//
//		return artikel;
	}

	// / Ersetzt den angegebenen Artikel und gibt die neue Version als Instanz
	// zurueck
//	public Artikel updateArtikel(Artikel artikel) {
//		if (artikel == null) {
//			System.out.println("[ERROR] UPDATE ARTIKEL fehlgeschlagen.");
//			
//
//			artikel = new Artikel();
//		}
//		else {
//			System.out.println(String.format("Artikel %d updated.",
//					artikel.getID()));
//		}
//
//		return artikel;
//	}
	
	/**
	 * Aktualisiert einen vorhandenen Artikel
	 * @param artikel : Der Artikel mit vorhandenen Attributwerten
	 * @return Der aktualisierte Artikel
	 */
	public Artikel updateArtikel(Artikel artikel) {
		if (artikel == null) {
			System.out.println("[ERROR] UPDATE ARTIKEL fehlgeschlagen.");
			return null;			
		}
		
		// kunde vom EntityManager trennen, weil anschliessend z.B. nach Id und Email gesucht wird
		em.detach(artikel);
		
		// Gibt es ein anderes Objekt mit gleicher Email-Adresse?
		final Artikel tmp = findArtikelByArtikelnummer(artikel.getArtikelnummer());
		if (tmp != null) {
			em.detach(tmp);
			if (tmp.getID().longValue() != artikel.getID().longValue()) {
				// anderes Objekt mit gleichem Attributwert fuer email
				throw new ArtikelnummerExistsException(artikel.getArtikelnummer());
			}
		}

		em.merge(artikel);
		return artikel;
	}

	

	private Artikel findArtikelByArtikelnummer(String artikelnummer) {
		// TODO Auto-generated method stub
		return null;
	}

	// / Erstellt einen neuen Artikel und gibt diesen als Instanz zurueck
	public Artikel createArtikel(Artikel artikel) {
		if (artikel == null) {
			System.out.println("[ERROR] CREATE ARTIKEL fehlgeschlagen.");			
			return null;
		}
		else {
			System.out.println("Artikel created.");
			artikel.setID(null);
			em.persist(artikel);
			return artikel;
		}
		
//		final Long tempId = new Long(66);
//		artikel.setID(tempId);
	}

}
