package de.webshop.kundenverwaltung.domain;

public enum Kundentyp {
	FIRMENKUNDE("F"),
	PRIVATKUNDE("P"),
	ERMAESSIGT("E");
	
	private String internal;
	
	private Kundentyp(String internal) {
		this.internal = internal;
	}
	
	public String getInternal() {
		return internal;
	}
	
	public static Kundentyp build(String internal) {
		switch (internal) {
			case "F": return FIRMENKUNDE;
			case "P": return PRIVATKUNDE;
			case "E": return ERMAESSIGT;
			default: throw new IllegalArgumentException();
		}
	}
}
