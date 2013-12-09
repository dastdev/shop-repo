package de.webshop.bestellverwaltung.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import de.webshop.bestellverwaltung.domain.Bestellung;
import de.webshop.bestellverwaltung.domain.Position;
import de.webshop.kundenverwaltung.domain.Kunde;
import de.webshop.util.interceptor.Log;
import de.webshop.util.mail.AbsenderMail;
import de.webshop.util.mail.AbsenderName;
import org.jboss.logging.Logger;

@ApplicationScoped
@Log
public class BestellungObserver implements Serializable {

	private static final long serialVersionUID = -3681804367549323893L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	private static final String NEWLINE = System.getProperty("line.separator");
	
	@Inject
	private transient Session session;
	
	@Inject
	@AbsenderMail
	private String absenderMail;
	
	@Inject
	@AbsenderName
	private String absenderName;
	
	@PostConstruct
	private void postConstruct() {
		if (absenderMail == null) {
			LOGGER.warn("Absender für Mail nicht gesetzt.");
			return;
		}
		
		LOGGER.infof("Absender fuer Bestellung-Emails: %s <%s>", absenderName, absenderMail);
	}
	
	public void onCreateBestellung(@Observes @NeueBestellung Bestellung bestellung) {
		final Kunde kunde = bestellung.getKunde();
		final String empfaengerMail = kunde.getEmail();
		if (absenderMail == null || empfaengerMail == null) {
			return;
		}
		final String vorname = kunde.getVorname() == null ? "" : kunde.getVorname();
		final String empfaengerName = vorname + " " + kunde.getName();
		
		final MimeMessage message = new MimeMessage(session);

		try {
			final InternetAddress absenderObj = new InternetAddress(absenderMail, absenderName);
			message.setFrom(absenderObj);
			
			// Empfaenger setzen
			final InternetAddress empfaenger = new InternetAddress(empfaengerMail, empfaengerName);
			message.setRecipient(RecipientType.TO, empfaenger);

			// Subject setzen
			message.setSubject("Neue Bestellung Nr. " + bestellung.getID());
			
			// Text setzen mit MIME Type "text/plain"
			final StringBuilder sb = new StringBuilder(256);
			sb.append("<h3>Neue Bestellung Nr. <b>" + bestellung.getID() + "</b></h3>" + NEWLINE);
			for (Position p : bestellung.getPositionen()) {
				sb.append(p.getAnzahl() + "\t" + p.getArtikel().getBezeichnung() + "<br/>" + NEWLINE);
			}
			final String text = sb.toString();
			LOGGER.trace(text);
			message.setContent(text, "text/html;charset=iso-8859-1");

			Transport.send(message);
		}
		catch (MessagingException | UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage());
			return;
		}
	}
}
