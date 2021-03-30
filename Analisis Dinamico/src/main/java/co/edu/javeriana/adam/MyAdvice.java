package co.edu.javeriana.adam;

import net.bytebuddy.asm.Advice;

public class MyAdvice {
    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Class klass, @Advice.Origin("#m") String methodName) {
        Stack.push();
        Stack.log(klass.getSimpleName() + "." + methodName + "() ");
    }

    @Advice.OnMethodExit
    public static void exit() {
        Stack.pop();
    }
}

