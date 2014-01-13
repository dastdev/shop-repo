package de.webshop.artikelverwaltung.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URI;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table (indexes =  @Index(columnList = "bezeichnung"))
public class Artikel implements Serializable {

	private static final long serialVersionUID = 2034010908161771924L;

	// Eigenschaften
	@Id
	@GeneratedValue
	@Basic(optional = false)
	@Min(value = 1, message = "{artikelverwaltung.artikel.id.min}")
	private Long id;
	
	@NotNull(message = "{artikelverwaltung.artikel.artikelnummer.notNull}")
	@Column (unique = true)
	private String artikelnummer;

	// mre: @Transient ???? 
	// dst: ich würde es drin lassen, kommt halt darauf an, wie und wo wir das Bild ablegen
	private URI artikelbild;

	private String bezeichnung;

	@Size(max = 200, message = "{artikelverwaltung.artikel.kurzBeschreibung.size}")
	private String kurzBeschreibung;

	private String beschreibung;
	
	@DecimalMin(value = "0.0", message = "{artikelverwaltung.artikel.preis.min}")
	@NotNull(message = "{artikelverwaltung.artikel.preis.notNull}")
	@Digits(integer = 5, fraction = 2, message = "{artikelverwaltung.artikel.preis.digits}")
	private BigDecimal preis;

	@Min(value = 0, message = "{artikelverwaltung.artikel.lagerbestand.min}")
	private Integer lagerbestand;
	
	@Min(value = 1, message = "{artikelverwaltung.artikel.parentID.min}")
	private Long parentID;

	@Column(length = 1) // Da Enum verkürzt in DB abgebildet wird
	@Convert(converter = KategorieConverter.class)
	private Kategorie kategorie;

	public enum Kategorie {
		KOMPLETTRAEDER("K"),
		RADTEILE("R"),
		ERSATZTEILE("E"),
		ZUBEHOER("Z");
		
		private String internal;
		
		private Kategorie(String internal) {
			this.internal = internal;
		}
		
		public String getInternal() {
			return internal;
		}
		
		public static Kategorie build(String internal) {
			switch(internal) {
			case "K": return KOMPLETTRAEDER;
			case "R": return RADTEILE;
			case "E": return ERSATZTEILE;
			case "Z": return ZUBEHOER;
			default: throw new IllegalArgumentException("...");	//TODO Fehlermeldung einbauen!
			}
		}
	}


	// Konstruktoren
	public Artikel() {

	}

	public Artikel(Long id, String artikelnummer, BigDecimal preis) {
		super();
		this.id = id;
		this.artikelnummer = artikelnummer;
		this.preis = preis;
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

	public Kategorie getKategorie() {
		return kategorie;
	}

	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public Long getID() {
		return id;
	}

	public Long getParentID() {
		return parentID;
	}

	// Basismethoden
	@Override
	public String toString() {
		return "Artikel\nID: " + id + "\nArtikelnummer: " + artikelnummer
				+ " \nArtikelbild: " + artikelbild + "\nBezeichnung: "
				+ bezeichnung + "\nKurzbeschreibung: " + kurzBeschreibung
				+ "\nArtikelbeschreibung: " + beschreibung + "\nPreis: "
				+ preis + "\nLagerbestand: " + lagerbestand + "\nParentID: "
				+ parentID + "\nKategorie: " + kategorie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artikelbild == null) ? 0 : artikelbild.hashCode());
		result = prime * result
				+ ((artikelnummer == null) ? 0 : artikelnummer.hashCode());
		result = prime * result
				+ ((beschreibung == null) ? 0 : beschreibung.hashCode());
		result = prime * result
				+ ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((kategorie == null) ? 0 : kategorie.hashCode());
		result = prime
				* result
				+ ((kurzBeschreibung == null) ? 0 : kurzBeschreibung.hashCode());
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
		final Artikel other = (Artikel) obj;
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
		if (id != other.id.longValue())
			return false;
		if (kategorie != other.kategorie)
			return false;
		if (kurzBeschreibung == null) {
			if (other.kurzBeschreibung != null)
				return false;
		}
		else if (!kurzBeschreibung.equals(other.kurzBeschreibung))
			return false;
		if (lagerbestand != other.lagerbestand.intValue())
			return false;
		if (parentID != other.parentID.longValue())
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
