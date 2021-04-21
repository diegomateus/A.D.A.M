package co.edu.javeriana.adam;

import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.util.Stack;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.jgrapht.DirectedGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

public class MyAdvice {
    //static long id = 0;
    public static Stack<String> trace = new Stack<>();
    public static Conexion graph = new Conexion();
    public static Boolean bandera = false;
    public static String nodoAnterior = null;
    public static LocalDateTime time = LocalDateTime.now();
    public static Boolean excelStatic = true;
    public static int enter = 0;

    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin("#t") Class klass, @Advice.Origin("#m") String methodName) {
        //id += 1;
        StackHelper.push();
        String firma = klass.getSimpleName() + "." + methodName + "()";
        StackHelper.log("Entra: " + firma);

        trace.add(firma);
        if(trace.size()>2) {
            bandera = true;
        }
    }

    @Advice.OnMethodExit
    public static void exit(@Advice.Origin Class kl29713ass, @Advice.Origin("#m") String methodName) {
        //StackHelper.log("Sale: " + klass.getSimpleName() + "." + methodName + "()");
        StackHelper.pop();
        //id -= 1;

        try {
            String auxString = trace.pop();
            if(bandera) {
                if (!graph.verificarNodo(auxString)) {
                    graph.addNodo(auxString);
                }
                if(enter-trace.size()>1) {
                    graph.addArista(auxString, nodoAnterior, "", "");
                }
            }
            nodoAnterior = auxString;

            //System.out.println(trace.size()+"--- exit   "+auxString);
            if(trace.empty()&&bandera) {
                System.out.println("nuevo grafo");
	    		/*if(java.time.Duration.between(LocalDateTime.now(),time).toMinutes()>2) {
		    		DirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

		        	for(Entry<String, Conexion> entry : graph.entrySet()) {
		        		g.addVertex(entry.getKey());

		        	}
		        	for(Entry<String, Conexion> entry : graph.entrySet()) {
		        		String auxNode = entry.getKey();
		        		for (String temp : entry.getValue().getNodosConectados()) {
		        		    g.addEdge(auxNode, temp);
		        		}
		        	}
		        	JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(g);
		        	mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
		            layout.execute(graphAdapter.getDefaultParent());

		            BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
		            File imgFile = new File("./temp/graph.png");
		            ImageIO.write(image, "PNG", imgFile);
	    		}*/
                bandera = false;
            }
        }catch(Exception e) {
            //id = 1;
        }
    }
}

