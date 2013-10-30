package de.webshop.bestellverwaltung.domain;

import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Position {
	
	private long	id;
	
	@XmlTransient
	private Artikel	artikel;
	private URI		artikelUri;
	
	private int		anzahl;
	
}
