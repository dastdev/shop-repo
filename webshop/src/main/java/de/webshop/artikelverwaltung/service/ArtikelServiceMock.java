package de.webshop.artikelverwaltung.service;

import java.math.BigDecimal;

import de.webshop.artikelverwaltung.domain.Artikel;
import de.webshop.artikelverwaltung.domain.Artikel.Kategorie;


public class ArtikelServiceMock {

	public Artikel findArtikelByID(Long id) {
		final Artikel artikel = new Artikel();
		artikel.setID(id);
		artikel.setArtikelnummer("R2D2uC3PO");
		artikel.setBezeichnung("Robobike");
		artikel.setKurzBeschreibung("Das Robobike weiﬂ wohin ...");
		artikel.setBeschreibung("Lange Robobikebeschreibu...........");
		artikel.setKategorie(Kategorie.KOMPLETTRAEDER);
		artikel.setLagerbestand(13);
		artikel.setPreis(new BigDecimal(1300.50));
		return artikel;
	}

//	public Artikel updateArtikel(Artikel artikel) {
//
//			if (artikel == null) {
//				return null;
//			}
//			
//			// TODO Datenbanzugriffsschicht statt Mock
//						
//			return artikel;
//	}
	
}
