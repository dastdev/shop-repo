package de.webshop.bestellverwaltung.service;

import de.webshop.bestellverwaltung.domain.Position;

public interface PositionService {
	
	Position findPositionById(Long id);
	
	// Positionen werden ueber Bestellung gefunden
	// List<Position> findPositionenByBestellungId(Long id);
	
	// Positionen werden ueber Bestellungen erzeugt
	// Position createPosition(Position position, Bestellung bestellung, Locale locale);
}
