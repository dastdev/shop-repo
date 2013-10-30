package de.webshop.kundenverwaltung.domain;

//Test
public class Kunde {
	
	private long	id;
	private String	name;
	private String	vorname;
	
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
