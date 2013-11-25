package de.webshop.util.interceptor;

import java.io.Serializable;
import java.util.Collection;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;


/**
 * Interceptor zum Tracing von public-Methoden der CDI-faehigen Beans und der Session Beans.
 * Sowohl der Methodenaufruf als auch der Rueckgabewert (nicht: Exception) werden mit
 * Level DEBUG protokolliert.
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Interceptor
@Log
public class LogInterceptor implements Serializable {
	private static final long serialVersionUID = -1669632586717470360L;
	private static final String COUNT = "Anzahl = ";
	private static final int MAX_ELEM = 4;  // bei Collections wird ab 5 Elementen nur die Anzahl ausgegeben
	private static final int CHAR_POS_AFTER_IS = 2; // isX...

	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		
		final Object bean = ctx.getTarget();
		final Class<?> clazz = bean.getClass();
		final String classname = clazz.getName();
		final Logger logger = Logger.getLogger(classname);

		if (!logger.isDebugEnabled()) {
			return ctx.proceed();
		}

		final String methodName = ctx.getMethod().getName();

		if ((methodName.startsWith("is")) && Character.isUpperCase(methodName.charAt(CHAR_POS_AFTER_IS))) {
			return ctx.proceed();
		}
		if (("toString".equals(methodName)) && Character.isUpperCase(methodName.charAt(CHAR_POS_AFTER_IS))) {
			return ctx.proceed();
		}
		
		final Object[] params = ctx.getParameters();

		// Methodenaufruf protokollieren
		final StringBuilder sb = new StringBuilder();
		if (params != null) {
			final int anzahlParams = params.length;
			sb.append(": ");
			for (int i = 0; i < anzahlParams; i++) {
				if (params[i] == null) {
					sb.append("null");
				}
				else {
					final String paramStr = toString(params[i]);
					sb.append(paramStr);
				}
				sb.append(", ");
			}
			final int laenge = sb.length();
			sb.delete(laenge - 2, laenge - 1);
		}
		logger.debug(methodName + " BEGINN" + sb);
		
		Object result = null;
//		try {
			// Eigentlicher Methodenaufruf
			result = ctx.proceed();
			
		// Keine Protokollierung der geworfenen Exception:
		// 1) Stacktrace wuerde abgeschnitten werden
		// 2) Exception wird an der Ursprungsstelle bereits protokolliert.
		//    Wenn der LoggingInterceptor in ejb-jar.xml abgeklemmt wird,
		//    muss naemlich immer noch eine Protokollierung stattfinden.

//		}
//		catch (Exception e) {
//			// Methode hat eine Exception geworfen
//			log.error(methodName + ": " + e.getMessage());
//			throw e;
//		}

		if (result == null) {
			// Methode vom Typ void oder Rueckgabewert null
			logger.debug(methodName + " ENDE");
		}
		else {
			final String resultStr = toString(result);
			logger.debug(methodName + " ENDE: " + resultStr);
		}
		
		return result;
	}
	
	/**
	 * Collection oder Array oder Objekt in einen String konvertieren
	 */
	private static String toString(Object obj) {
		if (obj instanceof Collection<?>) {
			// Collection: Elemente bei kleiner Anzahl ausgeben; sonst nur die Anzahl
			final Collection<?> coll = (Collection<?>) obj;
			final int anzahl = coll.size();
			if (anzahl > MAX_ELEM) {
				return COUNT + coll.size();
			}

			return coll.toString();
		}
		
		if (obj.getClass().isArray()) {
			// Array in String konvertieren: Element fuer Element
			final String str = arrayToString(obj);
			return str;
		}

		// Objekt, aber keine Collection und kein Array
		return obj.toString();
	}
	
	/**
	 * Array in einen String konvertieren
	 */
	private static String arrayToString(Object obj) {
		final Class<?> componentClass = obj.getClass().getComponentType();

		if (!componentClass.isPrimitive()) {
			// Array von Objekten
			final Object[] arr = (Object[]) obj;
			if (arr.length > MAX_ELEM) {
				return COUNT + arr.length;
			}

			final StringBuilder sbEnd = new StringBuilder("[");
			final int anzahl = arr.length;
			for (int i = 0; i < anzahl; i++) {
				if (arr[i] == null) {
					sbEnd.append("null");
				}
				else {
					sbEnd.append(arr[i]);
				}
				sbEnd.append(", ");
			}
			if (anzahl > 0) {
				final int laenge = sbEnd.length();
				sbEnd.delete(laenge - 2, laenge - 1);
			}
			sbEnd.append(']');
			return sbEnd.toString();
		}
		
		// Array von primitiven Werten: byte, short, int, long, ..., float, double, boolean, char
		
		if ("short".equals(componentClass.getName())) {
			final short[] arr = (short[]) obj;
			if (arr.length > MAX_ELEM) {
				return COUNT + arr.length;
			}

			final StringBuilder sbEnd = new StringBuilder("[");
			final int anzahl = arr.length;
			for (int i = 0; i < anzahl; i++) {
				sbEnd.append(arr[i]);
				sbEnd.append(", ");
			}
			final int laenge = sbEnd.length();
			if (anzahl > 0) {
				sbEnd.delete(laenge - 2, laenge - 1);
				sbEnd.append(']');
			}
			return sbEnd.toString();
		}
		
		if ("int".equals(componentClass.getName())) {
			final int[] arr = (int[]) obj;
			if (arr.length > MAX_ELEM) {
				return COUNT + arr.length;
			}

			final StringBuilder sbEnd = new StringBuilder("[");
			final int anzahl = arr.length;
			for (int i = 0; i < anzahl; i++) {
				sbEnd.append(arr[i]);
				sbEnd.append(", ");
			}
			final int laenge = sbEnd.length();
			if (anzahl > 0) {
				sbEnd.delete(laenge - 2, laenge - 1);
				sbEnd.append(']');
			}
			return sbEnd.toString();
		}
		
		if ("long".equals(componentClass.getName())) {
			final long[] arr = (long[]) obj;
			if (arr.length > MAX_ELEM) {
				return COUNT + arr.length;
			}

			final StringBuilder sbEnd = new StringBuilder("[");
			final int anzahl = arr.length;
			for (int i = 0; i < anzahl; i++) {
				sbEnd.append(arr[i]);
				sbEnd.append(", ");
			}
			final int laenge = sbEnd.length();
			if (anzahl > 0) {
				sbEnd.delete(laenge - 2, laenge - 1);
				sbEnd.append(']');
			}
			return sbEnd.toString();
		}
		
		if ("byte".equals(componentClass.getName())) {
			return "<byte-array>";
		}

		if ("float".equals(componentClass.getName())) {
			final float[] arr = (float[]) obj;
			if (arr.length > MAX_ELEM) {
				return COUNT + arr.length;
			}

			final StringBuilder sbEnd = new StringBuilder("[");
			final int anzahl = arr.length;
			for (int i = 0; i < anzahl; i++) {
				sbEnd.append(arr[i]);
				sbEnd.append(", ");
			}
			if (anzahl > 0) {
				final int laenge = sbEnd.length();
				sbEnd.delete(laenge - 2, laenge - 1);
			}
			sbEnd.append(']');
			return sbEnd.toString();
		}
		
		if ("double".equals(componentClass.getName())) {
			final double[] arr = (double[]) obj;
			if (arr.length > MAX_ELEM) {
				return COUNT + arr.length;
			}

			final StringBuilder sbEnd = new StringBuilder("[");
			final int anzahl = arr.length;
			for (int i = 0; i < anzahl; i++) {
				sbEnd.append(arr[i]);
				sbEnd.append(", ");
			}
			if (anzahl > 0) {
				final int laenge = sbEnd.length();
				sbEnd.delete(laenge - 2, laenge - 1);
			}
			sbEnd.append(']');
			return sbEnd.toString();
		}

		if ("char".equals(componentClass.getName())) {
			final char[] arr = (char[]) obj;
			if (arr.length > MAX_ELEM) {
				return COUNT + arr.length;
			}

			final StringBuilder sbEnd = new StringBuilder("[");
			final int anzahl = arr.length;
			for (int i = 0; i < anzahl; i++) {
				sbEnd.append(arr[i]);
				sbEnd.append(", ");
			}
			if (anzahl > 0) {
				final int laenge = sbEnd.length();
				sbEnd.delete(laenge - 2, laenge - 1);
			}
			sbEnd.append(']');
			return sbEnd.toString();
		}

		if ("boolean".equals(componentClass.getName())) {
			final boolean[] arr = (boolean[]) obj;
			if (arr.length > MAX_ELEM) {
				return COUNT + arr.length;
			}

			final StringBuilder sbEnd = new StringBuilder("[");
			final int anzahl = arr.length;
			for (int i = 0; i < anzahl; i++) {
				sbEnd.append(arr[i]);
				sbEnd.append(", ");
			}
			if (anzahl > 0) {
				final int laenge = sbEnd.length();
				sbEnd.delete(laenge - 2, laenge - 1);
			}
			sbEnd.append(']');
			return sbEnd.toString();
		}

		return "<<UNKNOWN ARRAY>>";
	}
}
