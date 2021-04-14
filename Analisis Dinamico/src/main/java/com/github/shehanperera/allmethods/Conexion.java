package com.github.shehanperera.allmethods;

import java.util.HashSet;

public class Conexion {
	
	private HashSet<String> nodosConectados;
	private int contador;
	public Conexion() {
		super();
		this.nodosConectados = new HashSet<String>();
		this.contador = 0;
	}
	public HashSet<String> getNodosConectados() {
		return nodosConectados;
	}
	public boolean addNodosConectados(String nodo) {
		return this.nodosConectados.add(nodo);
	}
	public int getContador() {
		return contador;
	}
	public void setContador() {
		this.contador++;
	}

}
