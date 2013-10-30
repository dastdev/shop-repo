/**
 * 
 */
package de.webshop.artikelverwaltung.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Stephan-PC
 *
 */
public class Artikelpreishistorie {
	
	private long id;
	private long artikelid;
	private Date gueltigVon;
	private BigDecimal preis;
	
	
	public Artikelpreishistorie(long artikelid, Date gueltigVon,
			BigDecimal preis) {
		super();
		this.artikelid = artikelid;
		this.gueltigVon = gueltigVon;
		this.preis = preis;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getArtikelid() {
		return artikelid;
	}
	public void setArtikelid(long artikelid) {
		this.artikelid = artikelid;
	}
	public Date getGueltigVon() {
		return gueltigVon;
	}
	public void setGueltigVon(Date gueltigVon) {
		this.gueltigVon = gueltigVon;
	}
	public BigDecimal getPreis() {
		return preis;
	}
	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
	
	

}
