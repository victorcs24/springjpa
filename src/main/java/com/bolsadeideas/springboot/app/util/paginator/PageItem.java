package com.bolsadeideas.springboot.app.util.paginator;

public class PageItem {
	private int numPaginas;
	private boolean actual;
	public PageItem(int numPaginas, boolean actual) {
		super();
		this.numPaginas = numPaginas;
		this.actual = actual;
	}
	public int getNumPaginas() {
		return numPaginas;
	}
	public boolean isActual() {
		return actual;
	}
	
}
