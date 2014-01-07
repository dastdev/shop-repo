package de.webshop.kundenverwaltung.domain;

import static javax.persistence.TemporalType.DATE;
import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.Email;
import de.webshop.bestellverwaltung.domain.Bestellung;

@Entity
@Table(indexes = @Index(columnList ="name") )
@XmlRootElement
public class Kunde implements Serializable {

	private static final long serialVersionUID = -8937961791375017L;
	
	//TODO @Id raus?
	@Id
	@GeneratedValue
	@Basic(optional=false)
	@Min(value = 1, message = "{kundenverwaltung.kunde.id.min}")
	private Long id;
	
	@NotNull(message = "{kundenverwaltung.kunde.name.notNull}")
	@Size(min = 2, max = 32, message = "{kundenverwaltung.kunde.name.length}")
	@Pattern(regexp = "[A-Z][a-z]+", message = "{kundenverwaltung.kunde.name.pattern}")
	private String name;
	
	@NotNull(message = "{kundenverwaltung.kunde.vorname.notNull}")
	@Size(min = 2, max = 32, message = "{kundenverwaltung.kunde.vorname.length}")
	@Pattern(regexp = "[A-Z][a-z]+", message = "{kundenverwaltung.kunde.vorname.pattern}")
	private String vorname;
	
	@Past(message = "{kundenverwaltung.kunde.geburtstag.date}")
	@Temporal(DATE)
	private Date geburtstag;
	
	@NotNull(message = "{kundenverwaltung.kunde.passwort.notNull}")
	@Size(min = 4, max = 16, message = "{kundenverwaltung.kunde.passwort.length}")
	private String passwort;
	
	@NotNull(message = "{kundenverwaltung.kunde.email.notNull}")
	@Email(message = "{kundenverwaltung.kunde.email.pattern}")
	@Column(unique=true)
	private String email;
	
	@NotNull(message = "{kundenverwaltung.kunde.typ.notNull}")
	@Column(length = 1)
	@Convert(converter = KundentypConverter.class)
	private Kundentyp typ;
	
	@Column
	private boolean geloescht;
	
	@Transient
	@OneToMany
	@JoinColumn(name="kunde")
	@XmlTransient
	private List<Bestellung> bestellungen;
	
	private URI uriBestellung;
	
	@Transient
	@OneToOne(mappedBy = "kunde")
	@NotNull(message = "{kundenverwaltung.kunde.adresse.notNull}")
	private Adresse adresse;

	public Kunde() {
	}
	
	//Konstruktor mit allen Pflichtattributen für DB-Zugriff
	public Kunde(String name, String vorname, String passwort, String email,
			Kundentyp typ) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.passwort = passwort;
		this.email = email;
		this.typ = typ;
	}

	//TODO (Checkstyle) Mehr als 7 Parameter
	public Kunde(Long id, String name, String vorname, Date geburtstag,
			String passwort, String email, Kundentyp typ, boolean geloescht,
			List<Bestellung> bestellungen, URI bestellungUri) {
		super();
		this.id = id;
		this.name = name;
		this.vorname = vorname;
		this.geburtstag = geburtstag;
		this.passwort = passwort;
		this.email = email;
		this.typ = typ;
		this.geloescht = geloescht;
		this.bestellungen = bestellungen;
		this.uriBestellung = bestellungUri;
	}

	/**
	 * Get- und Set-Methoden
	 * 
	 * @return: Jeweils Rueckgabe des entspr. Attributs (get-Methode)
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public Long getID() {
		return id;
	}

	public Date getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Kundentyp getTyp() {
		return typ;
	}

	public void setTyp(Kundentyp typ) {
		this.typ = typ;
	}

	public boolean isGeloescht() {
		return geloescht;
	}

	public void setGeloescht(boolean geloescht) {
		this.geloescht = geloescht;
	}

	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	public void setBestellungen(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}

	public URI getUriBestellung() {
		return uriBestellung;
	}

	public void setUriBestellung(URI uri) {
		this.uriBestellung = uri;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 * Geerbte Object-Methoden
	 */
	@Override
	public String toString() {
		return "Kunde " + id + ":\nNachname: " + name + " , Vorname: "
				+ vorname + "\nGeburtstag: " + geburtstag + "\nEmail: " + email
				+ "\nTyp: " + typ + "\nGeloescht: " + geloescht
				+ "\nBestellungen: " + uriBestellung;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((geburtstag == null) ? 0 : geburtstag.hashCode());
		result = prime * result + (geloescht ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((passwort == null) ? 0 : passwort.hashCode());
		result = prime * result + ((typ == null) ? 0 : typ.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
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
		final Kunde other = (Kunde) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		}
		else if (!email.equals(other.email))
			return false;
		if (geburtstag == null) {
			if (other.geburtstag != null)
				return false;
		}
		else if (!geburtstag.equals(other.geburtstag))
			return false;
		if (geloescht != other.geloescht)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (passwort == null) {
			if (other.passwort != null)
				return false;
		}
		else if (!passwort.equals(other.passwort))
			return false;
		if (typ != other.typ)
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		}
		else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}

}
