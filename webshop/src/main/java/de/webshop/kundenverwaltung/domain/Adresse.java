package de.webshop.kundenverwaltung.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Adresse {

	// Eigenschaften
	@Min(1)
	private Long id;
	private String stra\u00DFe;
	@Size(max = 4)
	@Pattern(regexp = "\\d+")
	private String hausnummer;
	@Pattern(regexp = "\\d{4,5}")
	private String plz;
	private String stadt;
	private Land land;

	// Länderkürzel nach ISO 3166
	public enum Land {
		AT, CH, DE
	}

	// get/set-Methoden
	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getStra\u00DFe() {
		return stra\u00DFe;
	}

	public void setStra\u00DFe(String stra\u00DFe) {
		this.stra\u00DFe = stra\u00DFe;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	// Basismethoden
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hausnummer == null) ? 0 : hausnummer.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
		result = prime * result + ((stadt == null) ? 0 : stadt.hashCode());
		result = prime * result
				+ ((stra\u00DFe == null) ? 0 : stra\u00DFe.hashCode());
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
		Adresse other = (Adresse) obj;
		if (hausnummer == null) {
			if (other.hausnummer != null)
				return false;
		} else if (!hausnummer.equals(other.hausnummer))
			return false;
		if (id != other.id)
			return false;
		if (land != other.land)
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		} else if (!plz.equals(other.plz))
			return false;
		if (stadt == null) {
			if (other.stadt != null)
				return false;
		} else if (!stadt.equals(other.stadt))
			return false;
		if (stra\u00DFe == null) {
			if (other.stra\u00DFe != null)
				return false;
		} else if (!stra\u00DFe.equals(other.stra\u00DFe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", stra\u00DFe=" + stra\u00DFe
				+ ", hausnummer=" + hausnummer + ", plz=" + plz + ", stadt="
				+ stadt + ", land=" + land + "]";
	}
}
