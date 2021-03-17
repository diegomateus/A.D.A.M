import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Listener;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.StringMatcher;

/**
 * 
 */

/**
 * @author federico
 *
 */
public class AgentBased {
	
	public static void premain() {
        try {
            String className = "SomeOtherLibraryClass";
            String methodName = "doesNotUseLogging";
 
            // The debugging listener shows what classes are being picked up by the instrumentation
            Listener.Filtering debuggingListener = new Listener.Filtering(
                    new StringMatcher(className, StringMatcher.Mode.CONTAINS),
                    Listener.StreamWriting.toSystemOut());
 
            // This gives a bit of a speedup when going through classes...
            RawMatcher ignoreMatcher = new RawMatcher.ForElementMatchers(ElementMatchers.nameStartsWith("net.bytebuddy.").or(isSynthetic()), any(), any());
 
            // Create and install the byte buddy remapper
            new AgentBuilder.Default()
                    .with(new AgentBuilder.InitializationStrategy.SelfInjection.Eager())
                    .ignore(ignoreMatcher)
                    .with(debuggingListener)
                    .type(ElementMatchers.nameContains(className))
                    .transform((builder, type, classLoader, module) ->
                            builder.visit(Advice.to(HellowWorld.class).on(named(methodName)))
                    )
                    .installOnByteBuddyAgent();
        } catch (RuntimeException e) {
            System.out.println("Exception instrumenting code : " + e);
            e.printStackTrace();
        }
    };
    
    public void doStuff() {
        // No code change necessary here, you can wrap completely in the agent...
        HellowWorld someOtherLibraryClass = new HellowWorld();
        someOtherLibraryClass.intercepts();
    }

}
