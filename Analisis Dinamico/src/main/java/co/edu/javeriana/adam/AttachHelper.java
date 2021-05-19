package co.edu.javeriana.adam;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class AttachHelper {
    Instrumentation inst;

    public static void main(String[] args) throws IOException, AttachNotSupportedException {
        VirtualMachine vm = VirtualMachine.attach("20378");
        try {
            vm.loadAgent("build/libs/Proyecto_ADAM-1.0-SNAPSHOT.jar");
            System.out.println("AGENT HAS BEEN LOADED");
        } catch (AgentLoadException e) {
            e.printStackTrace();
        } catch (AgentInitializationException e) {
            e.printStackTrace();
        } finally {
            vm.detach();
        }
    }
}
