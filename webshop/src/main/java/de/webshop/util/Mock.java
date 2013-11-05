package de.webshop.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import de.webshop.artikelverwaltung.domain.Artikel;
import de.webshop.artikelverwaltung.domain.Artikel.Kategorie;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.bestellverwaltung.domain.Position;
import de.webshop.kundenverwaltung.domain.Adresse;
import de.webshop.kundenverwaltung.domain.Adresse.Land;
import de.webshop.kundenverwaltung.domain.Kunde;

public final class Mock {
	
	// Reihenfolge pro Themenbereich:
	// CREATE
	// UPDATE
	// DELETE
	// Sonstiges...
	
	private static final int	MAX_ID				= 99;
	private static final int	MAX_KUNDEN			= 8;
	private static final int	MAX_BESTELLUNGEN	= 4;
	
	// Artikel
	//
	public static Artikel createArtikel(Artikel artikel) {
		System.out.println("create Artikel");
		
		artikel.setID(12L);
		
		return artikel;
	}
	
	public static void updateArtikel(Artikel artikel) {
		System.out.println("update Artikel");
	}
	
	public static Artikel findArtikelByID(long id) {
		Artikel artikel = new Artikel();
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
	
	// Bestellung
	//
	public static Bestellung createBestellung(Bestellung bestellung) {
		System.out.println("create Betsellung");
		
		bestellung.setID(12L);
		
		return bestellung;
	}
	
	public static void updateBestellung(Bestellung bestellung) {
		System.out.println("update Bestellung");
	}
	
	public static List<Bestellung> findBestellungenByKunde(Kunde kunde) {
		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
		final int anzahl = kunde.getID().intValue() % MAX_BESTELLUNGEN + 1;
		// 1 2 3 oder 4 Bestellungen
		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findBestellungByID(Long.valueOf(i));
			bestellung.setKunde(kunde);
			bestellungen.add(bestellung);
		}
		kunde.setBestellungen(bestellungen);
		
		return bestellungen;
	}
	
	public static Bestellung findBestellungByID(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Kunde kunde = findKundeByID(id + 1); // andere ID fuer den
													// Kunden
		
		final Bestellung bestellung = new Bestellung();
		bestellung.setID(id);
		bestellung.setKunde(kunde);
		bestellung.setBestelldatum(new Date(1234567890));
		
		// FIXME: setPositionen
		List<Position> positionen = new ArrayList<>();
		positionen.add(findPositionByID((long) 1));
		positionen.add(findPositionByID((long) 2));
		bestellung.setPositionen(positionen);
		
		return bestellung;
	}
	
	// Kunde
	//
	public static Kunde createKunde(Kunde kunde) {
		// Neue IDs fuer Kunde und zugehoerige Adresse
		// Ein neuer Kunde hat auch keine Bestellungen
		final String nachname = kunde.getName();
		kunde.setID(Long.valueOf(nachname.length() ^ kunde.hashCode()));
		
		// FIXME: Kundenadresse
		// final Adresse adresse = kunde.getAdresse();
		// adresse.setId((Long.valueOf(nachname.length())) + 1);
		// adresse.setKunde(kunde);
		
		System.out.println("Neuer Kunde: " + kunde);
		return kunde;
	}
	
	public static void updateKunde(Kunde kunde) {
		System.out.println("Aktualisierter Kunde: " + kunde);
	}
	
	public static void deleteKunde(Long kundeId) {
		System.out.println("Delete Kunde");
	}
	
	public static Kunde findKundeByID(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Kunde kunde = new Kunde();
		kunde.setID(id);
		kunde.setVorname("Vorname");
		kunde.setName("Nachname" + id);
		kunde.setPasswort("Texttest");
		kunde.setGeloescht(false);
		kunde.setEmail("" + id + "@hska.de");
		
		final Adresse adresse = new Adresse();
		
		if (id % 2 == 0) {
			adresse.setHausnummer("L4D");
			adresse.setID(id + 1);
			adresse.setLand(Land.DE);
			adresse.setPlz("76133");
			adresse.setStadt("Megashophausen");
			adresse.setStraﬂe("Bikestraﬂe");
		}
		else {
			adresse.setHausnummer("B2B");
			adresse.setID(id + 1);
			adresse.setLand(Land.AT);
			adresse.setPlz("1885");
			adresse.setStadt("Weizen");
			adresse.setStraﬂe("Naturtr¸bweg");
		}
		kunde.setAdresse(adresse);
		
		return kunde;
	}
	
	public static List<Kunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final List<Kunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeByID(Long.valueOf(i));
			kunden.add(kunde);
		}
		return kunden;
	}
	
	public static List<Kunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final List<Kunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeByID(Long.valueOf(i));
			kunde.setName(nachname);
			kunden.add(kunde);
		}
		return kunden;
	}
	
	// Position
	//
	public static Position createPosition(Position position) {
		System.out.println("create Position");
		
		// TODO: position anpassen
		
		position.setID(12L);
		return position;
	}
	
	public static void updatePosition(Position position) {
		System.out.println("update Position");
	}
	
	public static void deletePosition(long positionId) {
		System.out.println("delete Position");
	}
	
	public static Position findPositionByID(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Position position = new Position();
		position.setAnzahl(new Integer(2));
		
		Artikel artikel = new Artikel();
		artikel.setID(id);
		artikel.setArtikelnummer("R2D2uC3PO");
		artikel.setBezeichnung("Robobike");
		artikel.setKurzBeschreibung("Das Robobike weiﬂ wohin ...");
		artikel.setBeschreibung("Lange Robobikebeschreibu...........");
		artikel.setKategorie(Kategorie.KOMPLETTRAEDER);
		artikel.setLagerbestand(13);
		artikel.setPreis(new BigDecimal(1300.50));
		
		position.setArtikel(artikel);
		position.setID(id);
		
		return position;
	}
	
	public static List<Position> findPositionenByBestellung(Bestellung bestellung) {
		List<Position> positionen = bestellung.getPositionen();
		return positionen;
	}
	
	private Mock() { /**/
	}
}
