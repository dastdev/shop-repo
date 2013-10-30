package de.webshop.bestellverwaltung.domain;

import java.net.URI;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import de.webshop.kundenverwaltung.domain.Kunde;

@XmlRootElement
public class Bestellung {
	
	private long			id;
	
	@XmlTransient
	private Kunde			kunde;
	private URI				kundeUri;
	
	private Date			bestelldatum;
	
	// FIXME: welche @Xml Annotation? PostitionUri's speichern?
	private List<Position>	positionen;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
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
	
	// FIXME: Kunde.hashCode() und Kunde.equals() implementieren!
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestelldatum == null) ? 0 : bestelldatum.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + ((kundeUri == null) ? 0 : kundeUri.hashCode());
		result = prime * result + ((positionen == null) ? 0 : positionen.hashCode());
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
		if (id != other.id)
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
		return true;
	}
	
	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", kunde=" + kunde + ", kundeUri=" + kundeUri
				+ ", bestelldatum=" + bestelldatum + ", positionen=" + positionen + "]";
	}
	
}