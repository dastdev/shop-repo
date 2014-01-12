package de.webshop.artikelverwaltung.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.Dependent;

import de.webshop.artikelverwaltung.domain.Artikel;
import de.webshop.artikelverwaltung.domain.Artikel.Kategorie;
import de.webshop.util.interceptor.Log;

@Log
@Dependent
public class ArtikelService implements Serializable {

	private static final long serialVersionUID = -3006652195760480390L;

	// / Gibt eine Artikelinstanz des gesuchten Artikels via ID zurueck
	public Artikel findArtikelById(Long id) {
		final Artikel artikel = new Artikel();
		artikel.setID(id);
		artikel.setArtikelnummer("R2D2uC3PO");
		artikel.setBezeichnung("Robobike");
		artikel.setKurzBeschreibung("Das Robobike weiss wohin ...");
		artikel.setBeschreibung("Lange Robobikebeschreibu...........");
		artikel.setKategorie(Kategorie.KOMPLETTRAEDER);
		final Integer tempLaBe = new Integer(11);
		artikel.setLagerbestand(tempLaBe);
		final BigDecimal tempPreis = new BigDecimal(1337.55);
		artikel.setPreis(tempPreis);

		return artikel;
	}

	// / Ersetzt den angegebenen Artikel und gibt die neue Version als Instanz
	// zurueck
	public Artikel updateArtikel(Artikel artikel) {
		if (artikel == null) {
			System.out.println("[ERROR] UPDATE ARTIKEL fehlgeschlagen.");
			

			artikel = new Artikel();
		}
		else {
			System.out.println(String.format("Artikel %d updated.",
					artikel.getID()));
		}

		return artikel;
	}

	// / Erstellt einen neuen Artikel und gibt diesen als Instanz zurueck
	public Artikel createArtikel(Artikel artikel) {
		if (artikel == null) {
			System.out.println("[ERROR] CREATE ARTIKEL fehlgeschlagen.");			
			artikel = new Artikel();
		}
		else {
			System.out.println("Artikel created.");
		}
		
		final Long tempId = new Long(66);
		artikel.setID(tempId);

		return artikel;
	}

}
