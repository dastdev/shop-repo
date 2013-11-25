package de.webshop.util.rest;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -866705588853138386L;

	public NotFoundException(String msg) {
		super(msg);
	}
	
	public NotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}
