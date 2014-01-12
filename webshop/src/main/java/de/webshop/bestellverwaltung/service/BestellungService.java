package de.webshop.bestellverwaltung.service;

import java.util.List;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.kundenverwaltung.domain.Kunde;

public interface BestellungService {
	
	/**
	 * Bestellung zu gegebener ID suchen
	 * @param id ID der gesuchten Bestellung
	 * @return Die gesuchte Bestellung
	 */
	Bestellung findBestellungById(Long id);
	
	/**
	 * Bestellungen zu einem gegebenen Kunden suchen
	 * @param kunde Gegebener Kunde
	 * @return Liste der Bestellungen
	 */
	List<Bestellung> findBestellungenByKunde(Kunde kunde);
	
	/**
	 * Bestellung zu einem vorhandenen Kunden anlegen
	 * @param bestellung neue Bestellung
	 * @param kundeId ID des Kunden
	 * @return Neue Bestellung einschliesslich generierter ID
	 */
	Bestellung createBestellung(Bestellung bestellung, Long kundeId);
	
	/**
	 * Neue Bestellung zu einem vorhandenen Kunden anlegen
	 * @param bestellung Neue Bestellung
	 * @param kunde Der vorhandene Kunde
	 * @return Neue Bestellung einschliesslich generierter ID
	 */
	Bestellung createBestellung(Bestellung bestellung, Kunde kunde);
}
