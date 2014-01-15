package de.webshop.bestellverwaltung.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.bestellverwaltung.domain.Position;
import de.webshop.kundenverwaltung.domain.Kunde;
import de.webshop.kundenverwaltung.service.KundeService;
import de.webshop.kundenverwaltung.service.KundeService.FetchType;
import de.webshop.util.interceptor.Log;

@Dependent
@Log
public class BestellungServiceImpl implements BestellungService, Serializable {
	
	private static final long serialVersionUID	= -9035064120402892464L;
	
	@Inject 
	private transient EntityManager em;
	
	@Inject
	private KundeService ks;
	
	@Inject
	@NeueBestellung
	private transient Event<Bestellung>	event;
	
	@Override
	@NotNull(message = "{bestellung.notFound.id}")
	public Bestellung findBestellungById(Long id) {
		if (id == null) {
			return null;
		}
		
		return em.find(Bestellung.class, id);
	}
	
	@Override
	@Size(min = 1, message = "{bestellung.notFound.kunde}")
	public List<Bestellung> findBestellungenByKunde(Kunde kunde) {
		if (kunde == null) {
			return new ArrayList<Bestellung>();
		}
		
		return em.createNamedQuery(Bestellung.FIND_BESTELLUNGEN_BY_KUNDE, Bestellung.class).setParameter(Bestellung.PARAM_KUNDE, kunde).getResultList();
	}
	
	@Override
	public Bestellung createBestellung(Bestellung bestellung, Long kundeId) {
		if (bestellung == null) {
			return null;
		}
		
		Kunde kunde = ks.findKundeById(kundeId, FetchType.NUR_KUNDE);
		return createBestellung(bestellung, kunde);
	}
	
	@Override
	public Bestellung createBestellung(Bestellung bestellung, Kunde kunde) {
		if (bestellung == null) {
			return null;
		}
		
		// Kunde mit Bestellung verknuepfen
		if(!em.contains(kunde)) {	
			kunde = ks.findKundeById(kunde.getID(), FetchType.NUR_KUNDE);
		}
			
		kunde.addBestellung(bestellung);
		bestellung.setKunde(kunde);
		
		// IDs zuruecksetzen
		bestellung.setID(null);
		for (Position p : bestellung.getPositionen()) {
			p.setID(null);
		}
		
		em.persist(bestellung);
		//event.fire(bestellung);
		
		return bestellung;
	}
	
}
