package de.webshop.kundenverwaltung.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Adresse {

	// Eigenschaften
	@Min(1)
	private long id;
	private String straﬂe;
	@Size(max = 4)
	@Pattern(regexp = "\\d+")
	private String hausnummer;
	@Pattern(regexp = "\\d{4,5}")
	private String plz;
	private String stadt;
	private land land;

	// L‰nderk¸rzel nach ISO 3166
	public enum land {
		AT, CH, DE
	}

	// get/set-Methoden
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStraﬂe() {
		return straﬂe;
	}

	public void setStraﬂe(String straﬂe) {
		this.straﬂe = straﬂe;
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

	public land getLand() {
		return land;
	}

	public void setLand(land land) {
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
		result = prime * result + ((straﬂe == null) ? 0 : straﬂe.hashCode());
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
		if (straﬂe == null) {
			if (other.straﬂe != null)
				return false;
		} else if (!straﬂe.equals(other.straﬂe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", straﬂe=" + straﬂe + ", hausnummer="
				+ hausnummer + ", plz=" + plz + ", stadt=" + stadt + ", land="
				+ land + "]";
	}
}
