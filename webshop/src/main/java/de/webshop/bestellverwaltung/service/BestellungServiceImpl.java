package de.webshop.bestellverwaltung.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.kundenverwaltung.domain.Kunde;
import de.webshop.util.Mock;
import de.webshop.util.interceptor.Log;

@Dependent
@Log
public class BestellungServiceImpl implements BestellungService, Serializable {
	
	private static final long			serialVersionUID	= -9035064120402892464L;
	
	@Inject
	@NeueBestellung
	private transient Event<Bestellung>	event;
	
	@Override
	@NotNull(message = "{bestellung.notFound.id}")
	public Bestellung findBestellungById(Long id) {
		// TODO Datenbankzugriffsschicht
		return Mock.findBestellungById(id);
	}
	
	@Override
	@Size(min = 1, message = "{bestellung.notFound.kunde}")
	public List<Bestellung> findBestellungenByKunde(Kunde kunde) {
		// TODO Datenbankzugriffsschicht
		return Mock.findBestellungenByKunde(kunde);
	}
	
	@Override
	public Bestellung createBestellung(Bestellung bestellung, Kunde kunde, Locale locale) {
		// TODO Datenbankzugriffsschicht
		bestellung = Mock.createBestellung(bestellung, kunde);
		event.fire(bestellung);
		
		return bestellung;
	}
	
}
