package de.webshop.bestellverwaltung.domain;

import java.net.URI;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import de.webshop.artikelverwaltung.domain.Artikel;

@XmlRootElement
public class Position {
	
	private Long	id;
	
	@XmlTransient
	private Artikel	artikel;
	private URI		artikelUri;
	
	@Size(min = 0)
	private Integer	anzahl;
	
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
		result = prime * result + ((artikelUri == null) ? 0 : artikelUri.hashCode());
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
		Position other = (Position) obj;
		if (anzahl == null) {
			if (other.anzahl != null)
				return false;
		}
		else if (!anzahl.equals(other.anzahl))
			return false;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		}
		else if (!artikel.equals(other.artikel))
			return false;
		if (artikelUri == null) {
			if (other.artikelUri != null)
				return false;
		}
		else if (!artikelUri.equals(other.artikelUri))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Position [id=" + id + ", artikel=" + artikel + ", artikelUri=" + artikelUri
				+ ", anzahl=" + anzahl + "]";
	}
	
}
