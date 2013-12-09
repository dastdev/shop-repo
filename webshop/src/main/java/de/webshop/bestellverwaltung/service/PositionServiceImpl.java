package de.webshop.bestellverwaltung.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.bestellverwaltung.domain.Position;
import de.webshop.util.Mock;
import de.webshop.util.interceptor.Log;

@Dependent
@Log
public class PositionServiceImpl implements PositionService, Serializable {
	
	private static final long			serialVersionUID	= 1817223035927828153L;
	
	@Inject
	@NeuePosition
	private transient Event<Position>	event;
	
	@Override
	@NotNull(message = "{position.notFound.id}")
	public Position findPositionById(Long id) {
		// TODO Datenbankanbindung statt Mock
		return Mock.findPositionById(id);
	}
	
	@Override
	@NotNull(message = "{position.notFound.bestellung.id}")
	public List<Position> findPositionenByBestellungId(Long id) {
		// // TODO Datenbankanbindung statt Mock
		Bestellung bestellung = Mock.findBestellungById(id);
		return Mock.findPositionenByBestellung(bestellung);
	}
	
	@Override
	public Position createPosition(Position position, Bestellung bestellung, Locale locale) {
		// TODO Datenbankanbindung statt Mock
		position = Mock.createPosition(position, bestellung);
		event.fire(position);
		
		return position;
	}
	
}
