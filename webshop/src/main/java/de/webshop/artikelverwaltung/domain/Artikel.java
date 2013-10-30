package de.webshop.artikelverwaltung.domain;

import java.math.BigDecimal;
import java.net.URI;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Mario Reinholdt
 * 
 */
public class Artikel {
	
	// Eigenschaften
	@Min(1)
	private Long		id;
	@Pattern(regexp = "\\d{8}")
	private String		artikelnummer;
	private URI			artikelbild;
	private String		bezeichnung;
	@Size(max = 200)
	private String		kurzBeschreibung;
	private String		beschreibung;
	@DecimalMin("0.0")
	private BigDecimal	preis;
	@Min(0)
	private Integer			lagerbestand;
	@Min(1)
	private Long		parentID;
	private kategorie	kategorie;
	
	public enum kategorie {
			KOMPLETTRÄDER,
			RADTEILE,
			ERSATZTEILE,
			ZUBEHÖR
	}
	
	// Konstruktoren
	public Artikel() {
		
	}
	
	public Artikel(Long id, String artikelnummer, URI artikelbild, String bezeichnung,
			String kurzBeschreibung, String beschreibung, BigDecimal preis, Integer lagerbestand,
			Long parentID, de.webshop.artikelverwaltung.domain.Artikel.kategorie kategorie) {
		super();
		this.id = id;
		this.artikelnummer = artikelnummer;
		this.artikelbild = artikelbild;
		this.bezeichnung = bezeichnung;
		this.kurzBeschreibung = kurzBeschreibung;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.lagerbestand = lagerbestand;
		this.parentID = parentID;
		this.kategorie = kategorie;
	}
	
	// Getter und Setter
	
	public String getArtikelnummer() {
		return artikelnummer;
	}
	
	public void setArtikelnummer(String artikelnummer) {
		this.artikelnummer = artikelnummer;
	}
	
	public URI getArtikelbild() {
		return artikelbild;
	}
	
	public void setArtikelbild(URI artikelbild) {
		this.artikelbild = artikelbild;
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public String getKurzBeschreibung() {
		return kurzBeschreibung;
	}
	
	public void setKurzBeschreibung(String kurzBeschreibung) {
		this.kurzBeschreibung = kurzBeschreibung;
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public BigDecimal getPreis() {
		return preis;
	}
	
	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
	
	public Integer getLagerbestand() {
		return lagerbestand;
	}
	
	public void setLagerbestand(Integer lagerbestand) {
		this.lagerbestand = lagerbestand;
	}
	
	public kategorie getKategorie() {
		return kategorie;
	}
	
	public void setKategorie(kategorie kategorie) {
		this.kategorie = kategorie;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getParentID() {
		return parentID;
	}
	
	// Basismethoden
	@Override
	public String toString() {
		return "Artikel\nID: " + id + "\nArtikelnummer: " + artikelnummer + " \nArtikelbild: "
				+ artikelbild + "\nBezeichnung: " + bezeichnung + "\nKurzbeschreibung: "
				+ kurzBeschreibung + "\nArtikelbeschreibung: " + beschreibung + "\nPreis: " + preis
				+ "\nLagerbestand: " + lagerbestand + "\nParentID: " + parentID + "\nKategorie: "
				+ kategorie;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artikelbild == null) ? 0 : artikelbild.hashCode());
		result = prime * result + ((artikelnummer == null) ? 0 : artikelnummer.hashCode());
		result = prime * result + ((beschreibung == null) ? 0 : beschreibung.hashCode());
		result = prime * result + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((kategorie == null) ? 0 : kategorie.hashCode());
		result = prime * result + ((kurzBeschreibung == null) ? 0 : kurzBeschreibung.hashCode());
		result = prime * result + lagerbestand;
		result = prime * result + (int) (parentID ^ (parentID >>> 32));
		result = prime * result + ((preis == null) ? 0 : preis.hashCode());
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
		Artikel other = (Artikel) obj;
		if (artikelbild == null) {
			if (other.artikelbild != null)
				return false;
		}
		else if (!artikelbild.equals(other.artikelbild))
			return false;
		if (artikelnummer == null) {
			if (other.artikelnummer != null)
				return false;
		}
		else if (!artikelnummer.equals(other.artikelnummer))
			return false;
		if (beschreibung == null) {
			if (other.beschreibung != null)
				return false;
		}
		else if (!beschreibung.equals(other.beschreibung))
			return false;
		if (bezeichnung == null) {
			if (other.bezeichnung != null)
				return false;
		}
		else if (!bezeichnung.equals(other.bezeichnung))
			return false;
		if (id != other.id)
			return false;
		if (kategorie != other.kategorie)
			return false;
		if (kurzBeschreibung == null) {
			if (other.kurzBeschreibung != null)
				return false;
		}
		else if (!kurzBeschreibung.equals(other.kurzBeschreibung))
			return false;
		if (lagerbestand != other.lagerbestand)
			return false;
		if (parentID != other.parentID)
			return false;
		if (preis == null) {
			if (other.preis != null)
				return false;
		}
		else if (!preis.equals(other.preis))
			return false;
		return true;
	}
	
}
