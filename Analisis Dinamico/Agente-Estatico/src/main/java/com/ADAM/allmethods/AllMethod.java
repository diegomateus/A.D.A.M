package com.ADAM.allmethods;

import net.bytebuddy.asm.Advice;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import org.jgrapht.DirectedGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.ADAM.allmethods.Conexion;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

public class AllMethod {
	
	public static Stack<String> trace = new Stack<>();
	public static Conexion graph = new Conexion();
	public static Boolean bandera = false;
	public static String nodoAnterior = null;
	public static LocalDateTime time = LocalDateTime.now();
	public static Boolean excelStatic = true;
	public static int enter = 0;
	
	@Advice.OnMethodEnter
    static void enterMethods(@Advice.Origin String method) {
		try {
			if(excelStatic){
				System.out.println("Ingrese la ruta del grafo del analisis estatico");
				Scanner enter = new Scanner(System.in); 
				String fileRute = enter.nextLine ();
				File file = new File (fileRute);
				try{
					graph.openBook(file);
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
			enter = trace.size();
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
				if (!graph.verificarNodo(auxString)) {
					graph.addNodo(auxString);
				}
				if(enter-trace.size()>1) {
					graph.addArista(auxString, nodoAnterior, "", "");
				}
	    	}
	    	nodoAnterior = auxString;

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
    
  