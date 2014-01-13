package de.webshop.artikelverwaltung.service;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import de.webshop.artikelverwaltung.domain.Artikel;
import de.webshop.util.interceptor.Log;

@Log
@Dependent
public class ArtikelService implements Serializable {

	private static final long serialVersionUID = -3006652195760480390L;

	@Inject
	private transient EntityManager em;
	
	// / Gibt eine Artikelinstanz des gesuchten Artikels via ID zurueck
	@NotNull(message = "{artikelverwaltung.artikel.notFound.id}")
	public Artikel findArtikelById(Long id) {
		return em.find(Artikel.class, id);
	}

	// / Ersetzt den angegebenen Artikel und gibt die neue Version als Instanz
	// zurueck
	public Artikel updateArtikel(Artikel artikel) {
		if(artikel == null) {
			return null;
		}
		
		// Artikel von EM trennen
		em.detach(artikel);
		
		
		em.merge(artikel);
		
		return artikel;
	}

	// / Erstellt einen neuen Artikel und gibt diesen als Instanz zurueck
	public Artikel createArtikel(Artikel artikel) {
		if(artikel == null) {
			return null;
		}
		
		final Artikel tmp = findArtikelById(artikel.getID());
		if(tmp != null) {
			throw new ArtikelExistsException(artikel.getBezeichnung());
		}
		
		em.persist(artikel);
			
		return artikel;
	}

}
