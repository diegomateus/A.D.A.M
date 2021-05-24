package co.edu.javeriana.adam;

@SuppressWarnings("unused")
public class StackHelper {

    private static final String SPACE = "   ";
    static String indent = "";

    public static void push() {
        indent += SPACE;
    }

    public static void pop() {
        indent = indent.substring(indent.length()-1);
    }

    public static void log(String string){
        System.out.println(indent + string);
    }

}
