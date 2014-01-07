package de.webshop.kundenverwaltung.domain;

import java.io.Serializable;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class KundentypConverter implements AttributeConverter<Kundentyp, String>, Serializable {

	private static final long	serialVersionUID	= 2014166488282066910L;

	@Override
	public String convertToDatabaseColumn(Kundentyp kundentyp) {
		return kundentyp.getInternal();
	}
	
	@Override
	public Kundentyp convertToEntityAttribute(String internal) {
		return Kundentyp.build(internal);
	}
}
