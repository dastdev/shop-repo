package de.webshop.bestellverwaltung.service;

import java.util.List;
import java.util.Locale;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.bestellverwaltung.domain.Position;

public interface PositionService {
	
	Position findPositionById(Long id);
	
	List<Position> findPositionenByBestellungId(Long id);
	
	Position createPosition(Position position, Bestellung bestellung, Locale locale);
}