package de.webshop.artikelverwaltung.service;

import java.io.Serializable;

public class ArtikelnummerExistsException extends AbstractArtikelServiceException implements Serializable {

	private static final long serialVersionUID = 1276693990289060330L;
	private static final String MESSAGE_KEY = "artikel.artikelnummerExists";
	private final String artikelnummer;
	
	public ArtikelnummerExistsException(String artikelnummer) {
		super("Die Artikelnummer " + artikelnummer + " existiert bereits");
		this.artikelnummer = artikelnummer;
	}

	public String getArtikelnummer() {
		return artikelnummer;
	}

	@Override
	public String getMessageKey() {
		return MESSAGE_KEY;
	}
}
