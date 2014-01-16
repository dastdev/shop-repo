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
	//@NotNull(message = "{artikelverwaltung.artikel.notFound.ID}")
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
			System.out.println("[ERROR] UPDATE ARTIKEL fehlgeschlagen.");
			return null;			
		}
		
		// kunde vom EntityManager trennen, weil anschliessend z.B. nach Id und Email gesucht wird
		em.detach(artikel);
		em.merge(artikel);
		System.out.println("Artikel (ID: "+ artikel.getID() + ") updated.");
		return artikel;
	}

	// / Erstellt einen neuen Artikel und gibt diesen als Instanz zurueck
	public Artikel createArtikel(Artikel artikel) {
		if (artikel == null) {
			System.out.println("[ERROR] CREATE ARTIKEL fehlgeschlagen.");			
			return null;
		}
		else {
			artikel.setID(null);
			em.persist(artikel);
			System.out.println("Artikel created.");
			return artikel;
		}
		
		/// Von David:
//		final Artikel tmp = findArtikelById(artikel.getID());
//		if(tmp != null) {
//			throw new ArtikelExistsException(artikel.getBezeichnung());
//		}
//		
//		em.persist(artikel);
	}

}
