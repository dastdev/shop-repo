package de.webshop.artikelverwaltung.service;

import de.webshop.util.AbstractShopException;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
public abstract class ArtikelServiceException extends AbstractShopException {
	private static final long serialVersionUID = -2849585609393128387L;

	public ArtikelServiceException(String msg) {
		super(msg);
	}
	
	public ArtikelServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
