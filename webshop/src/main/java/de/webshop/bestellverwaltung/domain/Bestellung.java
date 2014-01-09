package de.webshop.bestellverwaltung.domain;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.TemporalType.DATE;
import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;
import de.webshop.kundenverwaltung.domain.Kunde;

@XmlRootElement
@Entity
@Table(indexes = {
		@Index(columnList = "kunde_fk")
	})
@Cacheable
public class Bestellung implements Serializable {
	
	private static final long serialVersionUID = 8645031681820165535L;
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	@Min(value = 1, message = "{bestellverwaltung.bestellung.id.min}")
	private Long id = null;
	
	@ManyToOne
	@JoinColumn(name = "kunde_fk", nullable = false, insertable = false, updatable = false)
	@OrderColumn(name = "index")
	@XmlTransient
	private Kunde kunde;
	
	@Transient
	private URI	kundeUri;
	
	@Temporal(DATE)
	@Column(updatable = false)
	@NotNull(message = "{bestellverwaltung.bestellung.date.notNull}")
	private Date bestelldatum;
	
	@OneToMany(fetch = EAGER, cascade = { PERSIST, REMOVE }) // FOLIE 121 05jpa.pdf - fetch = EAGER = DEFAULT?
	@JoinColumn(name = "bestellung_fk", nullable = false)
	// FIXME: @OrderColumn(name = "index") -> Führt zu ERROR!
	@NotEmpty(message = "{bestellverwaltung.bestellung.positionen.notEmpty}")
	@Valid
	private List<Position> positionen;
	
	
	public Bestellung() {
		super();
	}
	
	public Bestellung(List<Position> positionen) {
		super();
		this.positionen = positionen;
	}
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestelldatum == null) ? 0 : bestelldatum.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		// Checkstyle TODO Variable "other" sollte als final deklariert werden
		final Bestellung other = (Bestellung) obj;
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
		return true;
	}
	
	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", kunde=" + kunde + ", kundeUri=" + kundeUri
				+ ", bestelldatum=" + bestelldatum + ", positionen=" + positionen + "]";
	}
	
}
