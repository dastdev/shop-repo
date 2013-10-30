package de.webshop.bestellverwaltung.domain;

import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import de.webshop.artikelverwaltung.domain.Artikel;

@XmlRootElement
public class Position {
	
	private long	id;
	
	@XmlTransient
	private Artikel	artikel;
	private URI		artikelUri;
	
	private int		anzahl;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
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
	
	public int getAnzahl() {
		return anzahl;
	}
	
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anzahl;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result + ((artikelUri == null) ? 0 : artikelUri.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (anzahl != other.anzahl)
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
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Position [id=" + id + ", artikel=" + artikel + ", artikelUri=" + artikelUri
				+ ", anzahl=" + anzahl + "]";
	}
	
}
