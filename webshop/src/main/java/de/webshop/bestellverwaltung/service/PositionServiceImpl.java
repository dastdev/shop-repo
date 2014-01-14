package de.webshop.bestellverwaltung.service;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import de.webshop.bestellverwaltung.domain.Position;
import de.webshop.util.interceptor.Log;

@Dependent
@Log
public class PositionServiceImpl implements PositionService, Serializable {
	
	private static final long serialVersionUID	= 1817223035927828153L;
	
	@Inject 
	private transient EntityManager em;
	
	@Inject
	@NeuePosition
	private transient Event<Position> event;
	
	@Override
	@NotNull(message = "{position.notFound.id}")
	public Position findPositionById(Long id) {
		if (id == null) {
			return null;
		}
		
		return em.find(Position.class, id);
	}
	
	
	/* 
	@Override
	@NotNull(message = "{position.notFound.bestellung.id}")
	public List<Position> findPositionenByBestellungId(Long id) {
		// Datenbankanbindung statt Mock
		final Bestellung bestellung = Mock.findBestellungById(id);
		return Mock.findPositionenByBestellung(bestellung);
	}
	
	@Override
	public Position createPosition(Position position, Bestellung bestellung, Locale locale) {
		// Datenbankanbindung statt Mock
		position = Mock.createPosition(position, bestellung);
		event.fire(position);
		
		return position;
	}
	*/
	
}
