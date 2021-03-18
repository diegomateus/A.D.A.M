import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 
 */

/**
 * @author federico
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		Class<?> dynamicType = new ByteBuddy()
				  .subclass(Object.class)
				  .method(ElementMatchers.named("toString"))
				  .intercept(MethodDelegation.to(OriginHellowWorld.class))
				  .make()
				  .load(Main.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				  .getLoaded();
				 
				//assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
				System.out.println(dynamicType.newInstance());
	}

}
