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
        /*long pid = ProcessHandle.current().pid();
        System.out.println(pid);*/
        VirtualMachine vm = VirtualMachine.attach("12077");
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
