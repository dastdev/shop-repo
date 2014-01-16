package de.webshop.artikelverwaltung.service;

import java.io.Serializable;
//import java.math.BigDecimal;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
//import javax.validation.constraints.NotNull;

import de.webshop.artikelverwaltung.domain.Artikel;
//import de.webshop.artikelverwaltung.domain.Artikel.Kategorie;
import de.webshop.util.interceptor.Log;

@Log
@Dependent
public class ArtikelService implements Serializable {

	private static final long serialVersionUID = -3006652195760480390L;

	@Inject
	private transient EntityManager em;
	
	/**
	 * Findet einen vorhandenen Artikel entsprechend seiner ID
	 * @param id : Die ID des gesuchten Artikels
	 * @return Der Artikel, falls vorhanden
	 */
	public Artikel findArtikelById(Long id) {
		return em.find(Artikel.class, id);
	}

	
	/**
	 * Aktualisiert einen vorhandenen Artikel
	 * @param artikel : Der Artikel mit vorhandenen Attributwerten
	 * @return Der aktualisierte Artikel
	 */
	public Artikel updateArtikel(Artikel artikel) {	
		if (artikel == null) {
			return null;			
		}
		
		// kunde vom EntityManager trennen, weil anschliessend z.B. nach Id und Email gesucht wird
		em.detach(artikel);
		em.merge(artikel);
		return artikel;
	}

	// / Erstellt einen neuen Artikel und gibt diesen als Instanz zurueck
	public Artikel createArtikel(Artikel artikel) {
		if (artikel == null) {			
			return null;
		}
		
		artikel.setID(null);
		em.persist(artikel);
		return artikel;
	}

}
