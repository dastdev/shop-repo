package de.webshop.util.mail;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Producers implements Serializable {
	private static final long serialVersionUID = 3916523726340426731L;

	// In src\webapp\WEB-INF\web.xml koennen die Werte gesetzt bzw. ueberschrieben werden
	
	@Resource(name = "absenderMail")
	@Produces
	@AbsenderMail
	private String absenderMail;
		
	@Resource(name = "absenderName")
	@Produces
	@AbsenderName
	private String absenderName;
}
