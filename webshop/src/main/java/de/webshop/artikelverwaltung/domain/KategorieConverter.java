package de.webshop.artikelverwaltung.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import de.webshop.artikelverwaltung.domain.Artikel.Kategorie;

@Converter(autoApply = true)
public class KategorieConverter implements AttributeConverter<Kategorie, String> {
	
	@Override
	public String convertToDatabaseColumn(Kategorie kategorie) {
	return kategorie.getInternal();
	}
	
	@Override
	public Kategorie convertToEntityAttribute(String internal) {
	return Kategorie.build(internal);
	}	

}