package de.webshop.kundenverwaltung.service;

import de.webshop.util.AbstractShopException;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen
 *         Zimmermann</a>
 */

public abstract class AbstractKundeServiceException extends
		AbstractShopException {
	private static final long serialVersionUID = -2849585609393128387L;

	public AbstractKundeServiceException(String msg) {
		super(msg);
	}

	public AbstractKundeServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
