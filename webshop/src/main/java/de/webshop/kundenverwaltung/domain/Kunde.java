package de.webshop.kundenverwaltung.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//Test
public class Kunde {
	//TODO (Validations, Constructors, Get/Set)
	private long	id;
	@NotNull
	@Size (min = 2, max = 32)
	@Pattern (regexp = "[A-Z][a-z]+")
	private String	name;
	@NotNull
	@Size (min = 2, max = 32)
	@Pattern (regexp = "[A-Z][a-z]+")
	private String	vorname;
	private Date geburtstag;
	private String passwort;
	private String email;
	private Kundentyp typ;
	private boolean geloescht;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) throws Exception {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException();
		this.name = name;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public void setVorname(String vorname) throws Exception {
		if (vorname == null || vorname.isEmpty())
			throw new IllegalArgumentException();
		this.vorname = vorname;
	}
	
	public long getID() {
		return id;
	}
	
	public void setID(long id) throws Exception {
		if (id <= 0)
			throw new IllegalArgumentException();
		
		this.id = id;
	}
	
}
