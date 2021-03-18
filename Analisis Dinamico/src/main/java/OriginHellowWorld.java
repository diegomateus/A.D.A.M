import net.bytebuddy.implementation.bind.annotation.Origin;

import java.lang.reflect.Method;


/**
 * 
 */

/**
 * @author federico
 *
 */
public class OriginHellowWorld {
	public static String intercept(@Origin Method m) {
		return "Se interceptó el siguiente método: " + m.getName();
		
	}
}
