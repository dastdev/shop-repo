package de.webshop.kundenverwaltung.domain;

import static de.webshop.util.Constants.START_ID_NULL;
import static javax.persistence.TemporalType.TIMESTAMP;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.Email;
import org.jboss.logging.Logger;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.util.persistence.AbstractAuditable;

@Entity
@Table(indexes = @Index(columnList = "name"))
@NamedEntityGraphs({
	@NamedEntityGraph (name = "bestellungen", attributeNodes = @NamedAttributeNode ("bestellungen"))
})

@NamedQueries({ 
	@NamedQuery(name = Kunde.FIND_KUNDEN, 
			   query = "SELECT k " 
					   + "FROM Kunde k"),
	@NamedQuery(name = Kunde.FIND_KUNDEN_ORDER_BY_ID, 
			   query = "SELECT k " 
					   + "FROM Kunde k " 
					   + "ORDER BY k.id"),
	@NamedQuery(name = Kunde.FIND_KUNDE_BY_EMAIL, 
			   query = "SELECT k " 
					   + "FROM Kunde k " 
					   + "WHERE k.email = :" 
					   + Kunde.PARAM_KUNDE_EMAIL),
	@NamedQuery(name = Kunde.FIND_KUNDEN_BY_NACHNAME, 
			   query = "SELECT k " 
					   + "FROM Kunde k " 
					   + "WHERE k.name = :" 
					   + Kunde.PARAM_KUNDE_NACHNAME),
	@NamedQuery(name = Kunde.FIND_KUNDEN_BY_NACHNAME_PREFIX, 
			   query = "SELECT k " 
					   + "FROM Kunde k " 
					   + "WHERE UPPER(k.name) LIKE (:" 
					   + Kunde.PARAM_KUNDE_NACHNAME_PREFIX + ")")
	})
@XmlRootElement
public class Kunde extends AbstractAuditable {

	private static final long serialVersionUID = -8937961791375017L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final String NAME_PATTERN = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+";
	private static final String PREFIX_ADEL = "(o'|von|von der|von und zu|van)?";
	public static final String NACHNAME_PATTERN = PREFIX_ADEL + NAME_PATTERN + "(-" + NAME_PATTERN + ")?";
	
	public static final String KUNDE_PREFIX = "Kunde.";
	public static final String FIND_KUNDEN = KUNDE_PREFIX + "findKunden";
	public static final String FIND_KUNDEN_ORDER_BY_ID = KUNDE_PREFIX + "findKundenOrderByID";
	public static final String FIND_KUNDEN_BY_NACHNAME = KUNDE_PREFIX + "findKundenByNachname";
	public static final String FIND_KUNDEN_BY_NACHNAME_PREFIX = KUNDE_PREFIX + "findKundenByNachnamePrefix";
	public static final String FIND_KUNDE_BY_EMAIL = KUNDE_PREFIX + "findKundeByEmail";
	
	public static final String PARAM_KUNDE_EMAIL = "email";
	public static final String PARAM_KUNDE_NACHNAME = "name";
	public static final String PARAM_KUNDE_NACHNAME_PREFIX = "namePrefix";
	public static final String GRAPH_BESTELLUNGEN = KUNDE_PREFIX + "bestellungen";
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = START_ID_NULL;
	
	@NotNull(message = "{kundenverwaltung.kunde.name.notNull}")
//	@Size(min = 2, max = 32, message = "{kundenverwaltung.kunde.name.length}")
	@Pattern(regexp = NACHNAME_PATTERN, message = "{kundenverwaltung.kunde.name.pattern}")
	private String name;
	

	@Size(min = 2, max = 32, message = "{kundenverwaltung.kunde.vorname.length}")
	@Pattern(regexp = NAME_PATTERN, message = "{kundenverwaltung.kunde.vorname.pattern}")
	private String vorname;
	
	@Temporal(TIMESTAMP)
	@Past(message = "{kundenverwaltung.kunde.geburtstag.date}")
	private Date geburtstag;
	
	@Size(min = 4, max = 16, message = "{kundenverwaltung.kunde.passwort.length}")
	@Column(nullable = false)
	private String passwort;
	
	@NotNull(message = "{kundenverwaltung.kunde.email.notNull}")
	@Email(message = "{kundenverwaltung.kunde.email.pattern}")
	@Column(unique = true)
	private String email;
	
	@NotNull(message = "{kundenverwaltung.kunde.typ.notNull}")
	@Column(length = 1)
	@Convert(converter = KundentypConverter.class)
	private Kundentyp typ;
	
	private Boolean geloescht = false;

	@OneToMany
	@JoinColumn(name = "kunde_fk", nullable = false)
	@OrderColumn(name = "idx", nullable = false)
	@XmlTransient
	private List<Bestellung> bestellungen;
	
	@OneToOne(mappedBy = "kunde", cascade = { PERSIST, REMOVE })
	@NotNull(message = "{kundenverwaltung.kunde.adresse.notNull}")
	@Valid
	private Adresse adresse;
	
	@Transient
	private URI uriBestellung;
	
	@PostPersist
	protected void postPersist() {
		LOGGER.debugf("Neuer Kunde angelegt mit ID: %d", id);
	}

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
		return (Date) geburtstag.clone();
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag == null ? null : (Date) geburtstag.clone();
	}

	public String getGeburtstagAsString(int style, Locale locale) {
		Date temp = geburtstag;
		if (temp == null) {
			temp = new Date();
		}
		final DateFormat f = DateFormat.getDateInstance(style, locale);
		return f.format(temp);
	}
	
	// Parameter, z.B. DateFormat.MEDIUM, Locale.GERMANY
	// MEDIUM fuer Format dd.MM.yyyy
	public void setGeburtstag(String seitStr, int style, Locale locale) {
		final DateFormat f = DateFormat.getDateInstance(style, locale);
		try {
			this.geburtstag = f.parse(seitStr);
		}
		catch (ParseException e) {
			throw new RuntimeException("Kein gueltiges Datumsformat fuer: " + seitStr, e);
		}
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

	public Boolean isGeloescht() {
		return geloescht;
	}

	public void setGeloescht(Boolean geloescht) {
		this.geloescht = geloescht;
	}

	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	public void setBestellungen(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}
	
	public Kunde addBestellung(Bestellung bestellung) {
		if (bestellungen == null) {
			bestellungen = new ArrayList<>();
		}
		bestellungen.add(bestellung);
		return this;
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
	 * Geerbte Object-Methoden toString, hashCode, equals mit primitiven Attributen
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

		return true;
	}

	public void setValues(Kunde kunde) {
		name = kunde.name;
		vorname = kunde.vorname;
		email = kunde.email;
		passwort = kunde.passwort;
		typ = kunde.typ;
		geloescht = kunde.geloescht;
		geburtstag = kunde.geburtstag;
	}

}
