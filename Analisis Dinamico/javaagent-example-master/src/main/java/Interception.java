import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 
 */

/**
 * @author federico
 *
 */
public class Interception {

	// Do it through wrapping
    public HellowWorld instrumentClass() throws IllegalAccessException, InstantiationException {
        Class<HellowWorld> offendingClass = HellowWorld.class;
        String offendingMethodName = "doesNotUseLogging";
 
        return new ByteBuddy()
                .subclass(offendingClass)
                .method(ElementMatchers.named("to.string()"))
                .intercept(MethodDelegation.to(HellowWorld.class))
                .make()
                .load(offendingClass.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded()
                .newInstance();
    }
 
    public void doStuff() throws IllegalAccessException, InstantiationException {
    	HellowWorld someLibraryClass = this.instrumentClass();
        someLibraryClass.intercepts();
    }
}
