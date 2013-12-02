package de.webshop.bestellverwaltung.service;

import java.util.List;
import java.util.Locale;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.kundenverwaltung.domain.Kunde;

public interface BestellungService {
	
	Bestellung findBestellungById(Long id);
	
	List<Bestellung> findBestellungenByKunde(Kunde kunde);
	
	Bestellung createBestellung(Bestellung bestellung, Kunde kunde, Locale locale);
}