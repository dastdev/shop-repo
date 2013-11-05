package de.webshop.kundenverwaltung.domain;

import java.net.URI;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import de.webshop.bestellverwaltung.domain.Bestellung;

@XmlRootElement
public class Kunde {
	
	// Validierung, um Plausibilitätsprüfungen einzusparen
	@Min(1)
	private Long				id;
	@NotNull
	@Size(min = 2, max = 32)
	@Pattern(regexp = "[A-Z][a-z]+")
	private String				name;
	@NotNull
	@Size(min = 2, max = 32)
	@Pattern(regexp = "[A-Z][a-z]+")
	private String				vorname;
	@Past
	private Date				geburtstag;
	@NotNull
	@Size(min = 4, max = 16)
	private String				passwort;
	@NotNull
	@Pattern(regexp = "[\\w.%-]+@[\\w.%-]+\\.[A-Za-z]{2,4}")
	private String				email;
	@NotNull
	private Kundentyp			typ;
	private boolean				geloescht;
	@XmlTransient
	private List<Bestellung>	bestellungen;
	private URI					uriBestellung;
	private Adresse				adresse;
	
	public Kunde() {
	}
	
	/**
	 * Get- und Set-Methoden
	 * 
	 * @return: Jeweils Rückgabe des entspr. Attributs (get-Methode)
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
		return "Kunde " + id + ":\nNachname: " + name + " , Vorname: " + vorname + "\nGeburtstag: "
				+ geburtstag + "\nEmail: " + email + "\nTyp: " + typ + "\nGeloescht: " + geloescht
				+ "\nBestellungen: " + uriBestellung;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestellungen == null) ? 0 : bestellungen.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((geburtstag == null) ? 0 : geburtstag.hashCode());
		result = prime * result + (geloescht ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passwort == null) ? 0 : passwort.hashCode());
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
		if (bestellungen == null) {
			if (other.bestellungen != null)
				return false;
		}
		else if (!bestellungen.equals(other.bestellungen))
			return false;
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
