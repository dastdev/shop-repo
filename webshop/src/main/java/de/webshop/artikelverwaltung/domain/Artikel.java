package de.webshop.artikelverwaltung.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URI;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table (indexes =  @Index(columnList = "bezeichnung"))
public class Artikel implements Serializable {

	private static final long serialVersionUID = -4294751998308802696L;

	private static final String PREFIX = "Artikel.";
	
	public static final String UPDATE_KUNDE = PREFIX + "findKundenByNachname";

	// Eigenschaften
	@Id
	@GeneratedValue
	@Basic(optional = false)
	@Min(value = 1, message = "{artikelverwaltung.artikel.id.min}")
	@Column(nullable = false, updatable = false)
	private Long id;
	
	@NotNull(message = "{artikelverwaltung.artikel.artikelnummer.notNull}")
	@Column (unique = true)
	private String artikelnummer;

	@Transient
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

	@Column(length = 1) // Da Enum verkürzt in DB abgebildet wird
	@Convert(converter = KategorieConverter.class)
	private Kategorie kategorie;
	
	@Basic(optional = false)
	@Temporal(TIMESTAMP)
	@XmlTransient
	private Date erzeugt;

	@Basic(optional = false)
	@Temporal(TIMESTAMP)
	@XmlTransient
	private Date aktualisiert;

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
			default: throw new RuntimeException(internal + " ist kein gueltiger Wert fuer eine Kategorie");
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
	
	@PrePersist
	private void prePersist() {
		this.erzeugt = new Date();
		this.aktualisiert = new Date();
	}
	
	@PostPersist
	private void postPersist() {
		//LOGGER.debugf("Neuer Artikel mit ID=%d", id);
	}
	
	@PreUpdate
	private void preUpdate() {
		this.aktualisiert = new Date();
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
	
	public Date getErzeugt() {
		return erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public void setErzeugt(Date erzeugt) {
		this.erzeugt = erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public Date getAktualisiert() {
		return aktualisiert == null ? null : (Date) aktualisiert.clone();
	}

	public void setAktualisiert(Date aktualisiert) {
		this.aktualisiert = aktualisiert == null ? null : (Date) aktualisiert.clone();
	}


	// Basismethoden
	@Override
	public String toString() {
		return "Artikel\nID: " + id + "\nArtikelnummer: " + artikelnummer
				+ " \nArtikelbild: " + artikelbild + "\nBezeichnung: "
				+ bezeichnung + "\nKurzbeschreibung: " + kurzBeschreibung
				+ "\nArtikelbeschreibung: " + beschreibung + "\nPreis: "
				+ preis + "\nLagerbestand: " + lagerbestand + "\nKategorie: "
				+ kategorie + "\nErzeugt: ";
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
		if (preis == null) {
			if (other.preis != null)
				return false;
		}
		else if (!preis.equals(other.preis))
			return false;
		return true;
	}

}
