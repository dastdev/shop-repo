package de.webshop.kundenverwaltung.service;

import static de.webshop.util.Constants.LOADGRAPH;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.jboss.logging.Logger;
import com.google.common.collect.ImmutableMap;
import de.webshop.kundenverwaltung.domain.Kunde;
import de.webshop.util.interceptor.Log;

@Dependent
@Log
public class KundeService implements Serializable {

	private static final long serialVersionUID = -7742705898377634324L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Inject
	private transient EntityManager em;
	
	public enum FetchType { 
		NUR_KUNDE,
		MIT_BESTELLUNGEN
	}
	
	public enum OrderType {
		KEINE,
		ID
	}
	
	/**
	 * Suche einen Kunden zu gegebener ID
	 * @param id Angegebene ID
	 * @return Der Kunde, zu dem die gegebene ID gehört.
	 */
	@NotNull(message = "{kunde.notFound.id}")
	public Kunde findKundeById(Long id, FetchType fetchType) {
		if (id == null)
			return null;
		
		EntityGraph<?> entityGraph;
		Map<String, Object> props;
		Kunde kunde;
		
		switch (fetchType) {
			case NUR_KUNDE:
				kunde = em.find(Kunde.class, id);
				break;
				
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(Kunde.GRAPH_BESTELLUNGEN);
				props = ImmutableMap.of(LOADGRAPH, (Object) entityGraph);
				kunde = em.find(Kunde.class, id, props);
				break;
				
			default:
				kunde = em.find(Kunde.class, id);
				break;
		}
		return kunde;
	}

	/**
	 * Sucht einen Kunden zu einer gegebenen Emailadresse
	 * @param email Die gegebene Emailadresse
	 * @return Der zur Mailadresse gehörende Kunde
	 */
	@NotNull(message = "{kunde.notFound.email}")
	public Kunde findKundeByEmail(String email) {
			try {
			return em.createNamedQuery(Kunde.FIND_KUNDE_BY_EMAIL, Kunde.class)
					 .setParameter(Kunde.PARAM_KUNDE_EMAIL, email)
					 .getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Suche nach Kunden über Nachnamen
	 * @param name Der angegebene Nachname
	 * @return Liste der Kunden, die den angegebenen Nachnamen besitzen
	 */
	@NotNull(message = "{kunde.notFound.name}")
	public List<Kunde> findKundenByNachname(String name, FetchType fetchType) {
		final TypedQuery<Kunde> query = em.createNamedQuery(Kunde.FIND_KUNDEN_BY_NACHNAME, Kunde.class)
					 					  .setParameter(Kunde.PARAM_KUNDE_NACHNAME, name);
		
		EntityGraph<?> entityGraph;
		switch(fetchType) {
			case NUR_KUNDE:
				break;
					
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(Kunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;
				
			default:
				break;
			}
		return query.getResultList();
	}

	/**
	 * Suche nach Liste aller Kunden
	 * @return Gibt alle vorhandenen Kunden zurück
	 */
	public List<Kunde> findAllKunden(FetchType fetchType, OrderType orderType) {
		final TypedQuery<Kunde> query = OrderType.ID.equals(orderType)
										? em.createNamedQuery(Kunde.FIND_KUNDEN_ORDER_BY_ID, Kunde.class)
										: em.createNamedQuery(Kunde.FIND_KUNDEN, Kunde.class);
		
		EntityGraph<?> entityGraph;
		switch(fetchType) {
			case NUR_KUNDE:
				break;
			
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(Kunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;
			
			default:
				break;
		}
		return query.getResultList();
	}

	/**
	 * Anlegen eines neuen Kunden
	 * @param kunde Der anzulegende Kunde (mit zugehörigen Pflichtattributen)
	 * @return Der neu angelegte Kunde samt neuer ID
	 */
	public Kunde createKunde(Kunde kunde) {
		if (kunde == null) {
		System.out.println("Kein anzulegender Kunde angegeben");
			return kunde;
		}
		
		//Prüfung, ob es schon einen Kunden mit dieser Mailadresse gibt
		final Kunde tmpKunde = findKundeByEmail(kunde.getEmail());
		if (tmpKunde != null)
			throw new EmailExistsException(kunde.getEmail());
		
		em.persist(kunde);
		return kunde;
	}

	/**
	 * Aktualisieren eines Kunden
	 * @param kunde Der zu aktualisierende Kunde mit Attributen
	 * @return Aktualisierter Kunde
	 */
	public Kunde updateKunde(Kunde kunde) {
		if (kunde == null)
			return null;

		//Prüfung, ob es einen anderen Kunden mit gleicher Emailadresse gibt
		final Kunde tmpkunde = findKundeByEmail(kunde.getEmail());
		if (tmpkunde != null) {
			em.detach(tmpkunde);
			if (tmpkunde.getID().longValue() != kunde.getID().longValue()) {
				throw new EmailExistsException(tmpkunde.getEmail());
			}
		}
		
		em.merge(kunde);
		return kunde;
	}

	/**
	 * Löschen eines Kunden, sofern keine Bestellungen vorhanden
	 * @param kunde Der zu entfernende Kunde
	 */
	//TODO deleteKunde: Wenn Bestellungen >1, ausblenden
	public void deleteKunde(Kunde kunde) {
		if (kunde == null)
			return;
		
		kunde = findKundeById(kunde.getID(), FetchType.NUR_KUNDE);
		if (kunde == null)
			return;
		
		if (!kunde.getBestellungen().isEmpty()) {
			kunde.setGeloescht(true);
			throw new KundeDeleteBestellungException(kunde);
		}
		
			em.remove(kunde);
	}
}
