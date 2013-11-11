package de.webshop.bestellverwaltung.service;

import de.webshop.util.AbstractShopException;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen
 *         Zimmermann</a>
 */
public abstract class AbstractBestellungServiceException extends
		AbstractShopException {
	private static final long serialVersionUID = -2849585609393128387L;

	public AbstractBestellungServiceException(String msg) {
		super(msg);
	}

	public AbstractBestellungServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
