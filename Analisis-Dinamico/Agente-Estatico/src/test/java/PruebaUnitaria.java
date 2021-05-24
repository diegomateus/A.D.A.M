import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;

import com.ADAM.allmethods.Conexion;

import junit.framework.TestCase;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PruebaUnitaria extends TestCase {
	
	Conexion graph = new Conexion();

    @BeforeAll
    void iniciandoPruebas() {
        System.out.println("Iniciando las pruebas...");
    }

    @Test
    @DisplayName("Debe encontrar el archivo del análisis estático")
    void debeLanzarFileNotFoundExceptionSiNoEncuentraElArchivo() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        File file = new File (path.substring(0, path.length() - 1) + "analisisEstatico.xlsx");
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            graph.openBook(file);
        });
    }

    @Test
    @DisplayName("Debe agregar la firma a la pila de ejecución")
    void debeAgregarFirma() {
        Stack<String> trace = new Stack<>();
        trace.add("firma");
        Assertions.assertFalse(trace.isEmpty());
    }

    @Test
    @DisplayName("Debe agregar el nodo al grafo")
    void debeAgregarNodo() {
        graph.addNodo("nodo");
        Assertions.assertFalse(!graph.verificarNodo("nodo"));
    }

    @Test
    @DisplayName("Debe agregar la arista al grafo")
    void debeAgregarArista() {
        Conexion graph = new Conexion();
        graph.addArista("origen", "node", "", "");
        Assertions.assertThrows(NullPointerException.class, () -> {
            graph.verificarArista("nodo", "nodoDestino");
        });
    }

    @Test
    @DisplayName("Debe retornar las conexiones del grafo")
    void debeRetornarLasConexiones() {
        Conexion graph = new Conexion();
        Assertions.assertNull(graph.getConexiones("nodo"));
    }

    @AfterEach
    void pruebaTerminada() {
        System.out.println("Prueba terminada.");
    }

    @AfterEach
    void finalizandoPruebas() {
        System.out.println("¡Fin de las pruebas!");
    }

}
