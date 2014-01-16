package de.webshop.kundenverwaltung.domain;

import static de.webshop.util.Constants.START_ID_NULL;

import java.lang.invoke.MethodHandles;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jboss.logging.Logger;
import de.webshop.util.persistence.AbstractAuditable;

@XmlRootElement
@Entity
@Table(indexes = @Index(columnList = "plz"))
public class Adresse extends AbstractAuditable {

	private static final long serialVersionUID = -6129881639235747224L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final int STRASSE_LENGTH_MIN = 2;
	private static final int STRASSE_LENGTH_MAX = 32;
	private static final int HAUSNUMMER_LENGTH_MAX = 4;
	private static final int STADT_LENGTH_MIN = 2;
	private static final int STADT_LENGTH_MAX = 32;
	
	protected static final String PATTERN_PLZ = "\\d{4,5}";
	
	// Eigenschaften
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = START_ID_NULL;
	
	@OneToOne
	@JoinColumn(name = "kunde_fk", nullable = false, unique = true)
	@XmlTransient
	private Kunde kunde;
	
	@Size(min = STRASSE_LENGTH_MIN, max = STRASSE_LENGTH_MAX)
	@NotNull(message = "{kundenverwaltung.adresse.strasse.notNull}")
	private String strasse;
	
	@Size(max = HAUSNUMMER_LENGTH_MAX, message = "{kundenverwaltung.adresse.hausnummer.size}")
	//@NotNull(message = "{kundenverwaltung.adresse.hausnummer.notNull}")	//Unsinnig, da manche Adressen keine Hausnr. besitzen
	private String hausnummer;
	
	@Pattern(regexp = PATTERN_PLZ, message = "{kundenverwaltung.adresse.plz.pattern}")
	@NotNull(message = "{kundenverwaltung.adresse.plz.notNull }")
	private String plz;
	
	@NotNull(message = "{kundenverwaltung.adresse.stadt.notNull}")
	@Size(min = STADT_LENGTH_MIN, max = STADT_LENGTH_MAX)
	private String stadt;
	
	@NotNull(message = "{kundenverwaltung.adresse.land.notNull}")
	private Land land;
	
	// Laenderkuerzel nach ISO 3166
	public enum Land {
			AT, CH, DE
	}
	
	@PostPersist
	protected void postPersist() {
		LOGGER.debugf("Neue Adresse mit ID %d hinzugefuegt", id);
	}
		
	// get/set-Methoden
	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	// Rueckverweis auf Kunden, zu dem die Adresse gehoert
	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	// Basismethoden
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hausnummer == null) ? 0 : hausnummer.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
		result = prime * result + ((stadt == null) ? 0 : stadt.hashCode());
		result = prime * result + ((strasse == null) ? 0 : strasse.hashCode());
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
		final Adresse other = (Adresse) obj;
		if (hausnummer == null) {
			if (other.hausnummer != null)
				return false;
		}
		else if (!hausnummer.equals(other.hausnummer))
			return false;
		if (id != other.id.longValue())
			return false;
		if (land != other.land)
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		}
		else if (!plz.equals(other.plz))
			return false;
		if (stadt == null) {
			if (other.stadt != null)
				return false;
		}
		else if (!stadt.equals(other.stadt))
			return false;
		if (strasse == null) {
			if (other.strasse != null)
				return false;
		}
		else if (!strasse.equals(other.strasse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", strasse=" + strasse + ", hausnummer="
				+ hausnummer + ", plz=" + plz + ", stadt=" + stadt + ", land="
				+ land + "]";
	}

}
