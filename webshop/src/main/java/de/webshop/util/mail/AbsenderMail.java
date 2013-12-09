package de.webshop.util.mail;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

@Qualifier
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface AbsenderMail {
	
}
