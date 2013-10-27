package de.webshop.kundenverwaltung.domain;


public class Kunde {
	
	private String	Name;
	private String	Vorname;
	private long	ID;
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) throws Exception {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException();
		Name = name;
	}
	
	public String getVorname() {
		return Vorname;
	}
	
	public void setVorname(String vorname) throws Exception {
		if (vorname == null || vorname.isEmpty())
			throw new IllegalArgumentException();
		Vorname = vorname;
	}
	
	public Kunde(String name, String vorname) {
		super();
		Name = name;
		Vorname = vorname;
	}
	
	public long getID() {
		return ID;
	}
	
	public void setID(long id) throws Exception {
		if (id <= 0)
			throw new IllegalArgumentException();
		
		ID = id;
	}
	

}
