package com.ADAM.allmethods;


public class InfoArista {
	
	private int contador;
	private String tipo;
	public String label;
	
	
	public InfoArista( String tipo, String label) {
		super();
		this.contador = 1;
		this.tipo = tipo;
		this.label = label;
	}


	public int getContador() {
		return contador;
	}


	public void setContador() {
		this.contador++;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	
	
}