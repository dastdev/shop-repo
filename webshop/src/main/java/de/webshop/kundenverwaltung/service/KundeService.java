package de.webshop.kundenverwaltung.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;

import de.webshop.kundenverwaltung.domain.Kunde;
import de.webshop.util.Mock;
import de.webshop.util.interceptor.Log;

//Naechste Testversion
@Dependent
@Log
public class KundeService implements Serializable {

	private static final long serialVersionUID = -7742705898377634324L;

	@NotNull(message = "{kunde.notFound.id}")
	public Kunde findKundeById(Long id) {
		if (id == null)
			return null;

		// TODO Datenbankanbindung statt Mock
		return Mock.findKundeById(id);
	}

	@NotNull(message = "{kunde.notFound.email}")
	public Kunde findKundeByEmail(String email) {
		if (email == null)
			return null;

		// TODO Datenbankanbindung statt Mock
		return Mock.findKundeByEmail(email);
	}

	@NotNull(message = "{kunde.notFound.name}")
	public List<Kunde> findKundenByNachname(String name) {
		if (name == null)
			return null;

		// TODO DB-Anbindung statt Mock
		return Mock.findKundenByNachname(name);
	}

	public List<Kunde> findAllKunden() {
		// TODO Datenbankanbindung statt Mock
		return Mock.findAllKunden();
	}

	public Kunde createKunde(Kunde kunde) {
		if (kunde == null) {
		System.out.println("Kein anzulegender Kunde angegeben");
		return kunde;
		}

		final Kunde tmpKunde = Mock.findKundeByEmail(kunde.getEmail());
		if (tmpKunde != null)
			throw new EmailExistsException(kunde.getEmail());

		// TODO Datenbankanbindung statt Mock
		kunde = Mock.createKunde(kunde);
		return kunde;
	}

	public Kunde updateKunde(Kunde kunde) {
		if (kunde == null)
			return null;

		final Kunde tmpkunde = findKundeByEmail(kunde.getEmail());
		if (tmpkunde.getID().longValue() != kunde.getID().longValue())
			throw new EmailExistsException(tmpkunde.getEmail());

		Mock.updateKunde(kunde);
		return kunde;
	}

	public void deleteKunde(Long kundeId) {
		Mock.deleteKunde(kundeId);
	}
}
