package de.webshop.bestellverwaltung.domain;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlTransient;
import org.jboss.logging.Logger;
import de.webshop.artikelverwaltung.domain.Artikel;
import de.webshop.util.persistence.AbstractAuditable;

@Entity
@Table(indexes = {
	@Index(columnList = "bestellung_fk"),
	@Index(columnList = "artikel_fk")
})
public class Position extends AbstractAuditable {
	
	private static final long serialVersionUID	= -3474235149599204012L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	//@Min(value = 1, message = "{bestellverwaltung.position.id.min}")
	private Long id = null;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "artikel_fk", nullable = false)
	@XmlTransient
	private Artikel artikel;
	
	@Transient
	private URI artikelUri;
	
	@Min(value = 1, message = "{bestellverwaltung.position.anzahl.min}")
	@Basic(optional = false)
	private Integer anzahl;
	
	public Position() {
		super();
	}
	
	public Position(Artikel artikel) {
		super();
		this.artikel = artikel;
		this.anzahl = 1;
	}
	
	public Position(Artikel artikel, Integer anzahl) {
		super();
		this.artikel = artikel;
		this.anzahl = anzahl;
	}
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neue Bestellposition mit ID = %d", id);
	}
	
	public Long getID() {
		return id;
	}
	
	public void setID(Long id) {
		this.id = id;
	}
	
	public Artikel getArtikel() {
		return artikel;
	}
	
	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}
	
	public URI getArtikelUri() {
		return artikelUri;
	}
	
	public void setArtikelUri(URI artikelUri) {
		this.artikelUri = artikelUri;
	}
	
	public Integer getAnzahl() {
		return anzahl;
	}
	
	public void setAnzahl(Integer anzahl) {
		this.anzahl = anzahl;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anzahl == null) ? 0 : anzahl.hashCode());
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Position other = (Position) obj;
		if (anzahl == null) {
			if (other.anzahl != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		
		if (artikel == null) {
			if (other.artikel != null) {
				return false;
			}
		}
		else if (!artikel.equals(other.artikel)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Position [id=" + id + ", artikel=" + (artikel == null ? null : artikel.getID()) + ", artikelUri=" + artikelUri
				+ ", anzahl=" + anzahl + "]";
	}
	
}
