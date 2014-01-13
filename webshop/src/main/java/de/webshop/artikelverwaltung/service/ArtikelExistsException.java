package de.webshop.artikelverwaltung.service;


public class ArtikelExistsException extends AbstractArtikelServiceException {
	
	private static final long	serialVersionUID	= -3063302103474693099L;
	
	private static final String	MESSAGE_KEY	= "artikelverwaltung.artikel.emailExists";
	private final String artikel;
	
	public ArtikelExistsException(String artikel) {
		super("Der Artikel " + artikel + "existiert bereits!");
		this.artikel = artikel;
	}

	public String getArtikel() {
		return artikel;
	}
	
	@Override
	public String getMessageKey() {
		return MESSAGE_KEY;
	}
	
}
