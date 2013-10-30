package de.webshop.kundenverwaltung.domain;

import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Kunde {
	
	// Validierung, um Plausibilitätsprüfungen einzusparen
	@Min(1)
	private long		id;
	@NotNull
	@Size(min = 2, max = 32)
	@Pattern(regexp = "[A-Z][a-z]+")
	private String		name;
	@NotNull
	@Size(min = 2, max = 32)
	@Pattern(regexp = "[A-Z][a-z]+")
	private String		vorname;
	@Past
	private Date		geburtstag;
	@NotNull
	@Size(min = 4, max = 16)
	private String		passwort;
	@NotNull
	@Pattern(regexp = "[\\w.%-]+@[\\w.%-]+\\.[A-Za-z]{2,4}")
	private String		email;
	@NotNull
	private Kundentyp	typ;
	private boolean		geloescht;
	
	public Kunde(long id, String name, String vorname, Date geburtstag, String passwort,
			String email, Kundentyp typ, boolean geloescht) {
		super();
		this.id = id;
		this.name = name;
		this.vorname = vorname;
		this.geburtstag = geburtstag;
		this.passwort = passwort;
		this.email = email;
		this.typ = typ;
		this.geloescht = geloescht;
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
	
	public long getID() {
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
	
	/**
	 * Ererbte Methoden aus Object
	 */
	@Override
	public String toString() {
		return "Kunde:\nID: " + id + "\nNachname: " + name + "\nVorname: " + vorname
				+ "\nGeburtstag: " + geburtstag + "\nPasswort: " + passwort + "\nEmail: " + email
				+ "\nTyp: " + typ + "\nGeloescht: " + geloescht;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((geburtstag == null) ? 0 : geburtstag.hashCode());
		result = prime * result + (geloescht ? 1231 : 1237);
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Kunde other = (Kunde) obj;
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
		if (id != other.id)
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
