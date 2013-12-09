package de.webshop.bestellverwaltung.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import de.webshop.kundenverwaltung.domain.Kunde;

@XmlRootElement
public class Bestellung implements Serializable {
	
	private static final long	serialVersionUID	= 8645031681820165535L;
	
	private Long				id;
	
	@XmlTransient
	private Kunde				kunde;
	private URI					kundeUri;
	
	@NotNull
	private Date				bestelldatum;
	
	@XmlTransient
	// TODO: @XmlTransient ausbauen damit Positionen per POST übergeben werden
	// können!
	private List<Position>		positionen;
	
	private URI					positionenUri;
	
	public Long getID() {
		return id;
	}
	
	public void setID(Long id) {
		this.id = id;
	}
	
	public Kunde getKunde() {
		return kunde;
	}
	
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	
	public URI getKundeUri() {
		return kundeUri;
	}
	
	public void setKundeUri(URI kundeUri) {
		this.kundeUri = kundeUri;
	}
	
	public Date getBestelldatum() {
		return bestelldatum;
	}
	
	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}
	
	public List<Position> getPositionen() {
		return positionen;
	}
	
	public void setPositionen(List<Position> positionen) {
		this.positionen = positionen;
	}
	
	public URI getPositionenUri() {
		return positionenUri;
	}
	
	public void setPositionenUri(URI positionenUri) {
		this.positionenUri = positionenUri;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestelldatum == null) ? 0 : bestelldatum.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + ((kundeUri == null) ? 0 : kundeUri.hashCode());
		result = prime * result + ((positionen == null) ? 0 : positionen.hashCode());
		result = prime * result + ((positionenUri == null) ? 0 : positionenUri.hashCode());
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
		Bestellung other = (Bestellung) obj;
		if (bestelldatum == null) {
			if (other.bestelldatum != null)
				return false;
		}
		else if (!bestelldatum.equals(other.bestelldatum))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		}
		else if (!kunde.equals(other.kunde))
			return false;
		if (kundeUri == null) {
			if (other.kundeUri != null)
				return false;
		}
		else if (!kundeUri.equals(other.kundeUri))
			return false;
		if (positionen == null) {
			if (other.positionen != null)
				return false;
		}
		else if (!positionen.equals(other.positionen))
			return false;
		if (positionenUri == null) {
			if (other.positionenUri != null)
				return false;
		}
		else if (!positionenUri.equals(other.positionenUri))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", kunde=" + kunde + ", kundeUri=" + kundeUri
				+ ", bestelldatum=" + bestelldatum + ", positionen=" + positionen
				+ ", positionenUri=" + positionenUri + "]";
	}
	
}
