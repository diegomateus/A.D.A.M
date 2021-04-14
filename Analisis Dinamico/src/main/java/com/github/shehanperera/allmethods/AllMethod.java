package com.github.shehanperera.allmethods;

import controller.Controller;
import net.bytebuddy.asm.Advice;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

public class AllMethod {

	public static Stack<String> trace = new Stack<>();
	public static HashMap<String, Conexion> graph = new HashMap<>();
	public static Boolean bandera = false;
	public static String nodoAnterior = null;
	public static LocalDateTime time = LocalDateTime.now();
	public static Boolean excelStatic = true;
	
	@Advice.OnMethodEnter
    static void enterMethods(@Advice.Origin String method) {
		try {
			if(excelStatic){
				System.out.println("Ingrese la ruta del grafo del analisis estatico");
				Scanner enter = new Scanner(System.in); //Creación de un objeto Scanner
				String fileRute = enter.nextLine ();
				File file = new File (fileRute);
				try{
					Controller controller = new Controller(file);
					controller.openBook();
					excelStatic = false;
				}
				catch(Exception e){
					//TODO: POR SI NO EXISTE EL DIRECTORIO
				}
			}
		
		String firma ="";
		StringTokenizer st1 = new StringTokenizer(method, " ");
        while (st1.hasMoreTokens()) {
     	   String aux = st1.nextToken();
            if(aux.contains("org.springframework")) {
         	   StringTokenizer st2 = new StringTokenizer(aux, "(");
         	   firma =  st2.nextToken();
            }
        }
		trace.add(firma);
		System.out.println(trace.size()+"---enter   "+firma);
		if(trace.size()>2) {
			bandera = true;
		}
		}catch(Exception e) {
			//trace.clear();
		}
    }

    @Advice.OnMethodExit
    static void getAllMethods() {
    	
    	try {
    		String auxString = trace.pop();
	    	if(bandera) {
				if (!graph.containsKey(auxString)) {
					graph.put(auxString, new Conexion());
				}
				if(graph.get(auxString).addNodosConectados(nodoAnterior)) graph.get(auxString).setContador();
				//System.out.println("conexiones->"+graph.size());
	    	}
	    	nodoAnterior = auxString;

	    	//System.out.println(trace.size()+"--- exit   "+auxString); &&java.time.Duration.between(LocalDateTime.now(),time).toMinutes()>2
	    	if(trace.empty()&&bandera) {
	    		System.out.println("nuevo grafo");
	    		if(java.time.Duration.between(LocalDateTime.now(),time).toMinutes()>2) {
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
	    		}
	            bandera = false;
	    	}
	    }catch(Exception e) {
			//id = 1;
		}
    }
    
   /* static void drawGraph() throws IOException {
    	DirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
    	
    	for(Entry<String, HashSet<String>> entry : graph.entrySet()) {
    		g.addVertex(entry.getKey());
    	}
    	for(Entry<String, HashSet<String>> entry : graph.entrySet()) {
    		String auxNode = entry.getKey();
    		for (String temp : entry.getValue()) {
    		    g.addEdge(auxNode, temp);
    		}
    	}
    	JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<String, DefaultEdge>(g);
    	mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
        
        BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
        File imgFile = new File("/Users/williambaquero/Documents/GitHub/spring-petclinic/graph.png");
        ImageIO.write(image, "PNG", imgFile);

        //assertTrue(imgFile.exists());
        
    }
    
    public static String getClass(String method) {
    	StringTokenizer st1 = new StringTokenizer(method, " ");
           while (st1.hasMoreTokens()) {
        	   String aux = st1.nextToken();
               if(aux.contains("org.springframework")) {
            	   StringTokenizer st2 = new StringTokenizer(aux, "(");
            	   return st2.nextToken();
               }
           }
		return method;
    	
    }*/

}