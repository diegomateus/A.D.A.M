package co.edu.javeriana.adam;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation instrumentation) {
        /*AgentBuilder agentBuilder = createAgentBuilder(instrumentation);
        agentBuilder.installOn(instrumentation);*/

        /*System.out.println("[Agent] In premain method");
        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("org.springframework.samples"))
                .transform((builder, type, classLoader, module) -> builder
                        .method(ElementMatchers.any())
                        .intercept(MethodDelegation.to(TimingInterceptor.class))
                ).installOn(instrumentation);*/
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("[Agent] In agentmain method");
        new AgentBuilder.Default()
                .disableClassFormatChanges()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .type(ElementMatchers.nameStartsWith("org.springframework.samples"))
                .transform((builder, typeDescription, classLoader, module) -> builder
                        .visit(Advice.to(MyAdvice.class).on(ElementMatchers.any())))
                .installOn(inst);

        //Trying to call TimingInterceptor dynamically...
        /*new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("org.springframework.samples"))
                .transform((builder, type, classLoader, module) -> builder
                        .method(ElementMatchers.any())
                        .intercept(MethodDelegation.to(TimingInterceptor.class))
                ).installOn(inst);*/
    }

    /*private static AgentBuilder createAgentBuilder(Instrumentation instrumentation) {
        return new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("org.springframework.samples"))
                .transform((builder, typeDescription, classLoader, module) -> {
                    System.out.println("Transforming: " + typeDescription);
                    return builder.visit(Advice.to(MyAdvice.class).on(ElementMatchers.any()));
                });
    }*/
}

