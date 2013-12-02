package de.webshop.util;

//import java.lang.invoke.MethodHandles;
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

//import org.jboss.logging.Logger;

public final class Mock {
	
	// private static final Logger logger =
	
	private static final int	MAX_ID				= 99;
	private static final int	MAX_KUNDEN			= 8;
	private static final int	MAX_BESTELLUNGEN	= 4;
	
	public static Position findPositionById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Position position = new Position();
		position.setAnzahl(new Integer(2));
		
		position.setArtikel(Mock.findArtikelById(id));
		position.setID(id);
		
		return position;
	}
	
	public static Artikel findArtikelById(Long id) {
		Artikel artikel = new Artikel();
		artikel.setID(id);
		artikel.setArtikelnummer("R2D2uC3PO");
		artikel.setBezeichnung("Robobike");
		artikel.setKurzBeschreibung("Das Robobike weiss wohin ...");
		artikel.setBeschreibung("Lange Robobikebeschreibu...........");
		artikel.setKategorie(Kategorie.KOMPLETTRAEDER);
		artikel.setLagerbestand(13);
		artikel.setPreis(new BigDecimal(1300.50));
		
		return artikel;
	}
	
	public static List<Position> findPositionenByBestellung(Bestellung bestellung) {
		List<Position> positionen = bestellung.getPositionen();
		return positionen;
	}
	
	public static Kunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Kunde kunde = new Kunde();
		kunde.setID(id);
		kunde.setName("Nachname" + id);
		kunde.setEmail("" + id + "@hska.de");
		
		final Adresse adresse = new Adresse();
		
		if (id % 2 == 0) {
			adresse.setHausnummer("L4D");
			adresse.setID(id + 1);
			adresse.setLand(Land.DE);
			adresse.setPlz("76133");
			adresse.setStadt("Megashophausen");
			adresse.setStraße("Bikestraße");
		}
		else {
			adresse.setHausnummer("B2B");
			adresse.setID(id + 1);
			adresse.setLand(Land.AT);
			adresse.setPlz("1885");
			adresse.setStadt("Weizen");
			adresse.setStraße("Naturtrübweg");
		}
		
		return kunde;
	}
	
	public static List<Kunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final List<Kunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeById(Long.valueOf(i));
			kunden.add(kunde);
		}
		return kunden;
	}
	
	public static List<Kunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final List<Kunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeById(Long.valueOf(i));
			kunde.setName(nachname);
			kunden.add(kunde);
		}
		return kunden;
	}
	
	public static Bestellung createBestellung(Bestellung bestellung, Kunde kunde) {
		System.out.printf("Neue Bestellung: %s fuer Kunde: %s\n", bestellung, kunde);
		return bestellung;
	}
	
	public static List<Bestellung> findBestellungenByKunde(Kunde kunde) {
		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
		final int anzahl = kunde.getID().intValue() % MAX_BESTELLUNGEN + 1;
		// 1 2 3 oder 4 Bestellungen
		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findBestellungById(Long.valueOf(i));
			bestellung.setKunde(kunde);
			bestellungen.add(bestellung);
		}
		
		// kunde.setBestellungen(bestellungen);
		
		return bestellungen;
	}
	
	public static Bestellung findBestellungById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Kunde kunde = findKundeById(id + 1); // andere ID fuer den
													// Kunden
		
		final Bestellung bestellung = new Bestellung();
		bestellung.setID(id);
		bestellung.setKunde(kunde);
		bestellung.setBestelldatum(new Date(1234567890));
		
		// FIXME: setPositionen
		List<Position> positionen = new ArrayList<>();
		positionen.add(findPositionById((long) 1));
		positionen.add(findPositionById((long) 2));
		bestellung.setPositionen(positionen);
		
		return bestellung;
	}
	
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
	
	public static Position createPosition(Position position, Bestellung bestellung) {
		
		// TODO: position anpassen
		if (position == null) {
			position = new Position();
			System.out.println("uebergebene Position ungueltig");
		}
		else {
			System.out.println(String.format("Create Position %d", position.getID()));
		}
		
		position.setID(12L);
		return position;
	}
	
	public static void updatePosition(Position position) {
		// gespeicherte Position finden
		// gesp. Position mir Werten aus Parameter überschreiben
		
		if (position == null) {
			System.out.println("Update fehlgeschlagen");
		}
		else {
			System.out.println(String.format("Update position %d", position.getID()));
		}
	}
	
	public static void deletePosition(long positionId) {
		System.out.println(String.format("Delete Position %d", positionId));
	}
	
	private Mock() { /**/
	}
	
	public static Artikel updateArtikel(Artikel artikel) {
		if (artikel == null) {
			System.out.println("Update fehlgeschlagen");
			
			artikel = new Artikel();
		}
		else {
			System.out.println(String.format("Update artikel %d", artikel.getID()));
		}
		
		return artikel;
	}
	
	public static Artikel createArtikel(Artikel artikel) {
		if (artikel == null) {
			System.out.println("Create fehlgeschlagen");
			
			artikel = new Artikel();
		}
		else {
			System.out.println("Create artikel");
		}
		
		// TODO: calc ID
		artikel.setID(13L);
		
		return artikel;
	}
}
