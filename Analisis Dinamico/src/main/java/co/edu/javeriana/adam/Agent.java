package co.edu.javeriana.adam;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation instrumentation) {

    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("[Agent] In agentmain method");
        new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .disableClassFormatChanges()
                .type(ElementMatchers.nameStartsWith("org.springframework.samples"))
                .transform((builder, typeDescription, classLoader, module) -> {
                    return builder
                            .visit(Advice.to(MyAdvice.class).on(ElementMatchers.any()));
                })
                .installOn(inst);
    }
}

