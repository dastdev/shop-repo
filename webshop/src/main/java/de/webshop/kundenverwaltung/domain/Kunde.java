package de.webshop.kundenverwaltung.domain;

public class Kunde {
	
	private String	Name;
	private String	Vorname;
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getVorname() {
		return Vorname;
	}
	
	public void setVorname(String vorname) {
		Vorname = vorname;
	}
	
	public Kunde(String name, String vorname) {
		super();
		Name = name;
		Vorname = vorname;
	}
}
