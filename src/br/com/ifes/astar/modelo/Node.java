package br.com.ifes.astar.modelo;

public class Node implements Comparable<Node> {
	public double g;
	public double h;
	public double f = 0;
	public int x;
	public int y;
	public Node pai;
	// Esse construtor e utilizado para criar um sucessor
	public Node(Node pai, int x1, int y1, int x2, int y2) {
		this.pai = pai;
	    this.x = x1;
	    this.y = y1;
	    this.g = pai.g + 1;
	    // Essa e a implementacao da distancia euclideana.. entre dois pontos.
	    this.h = Math.sqrt( Math.pow(x1-x2,2)+Math.pow(y1 - y2,2) );
	    this.f = this.g + this.h;
	}
	// Esse e o construtor do no de origem
	public Node(int x, int y) {
		this.pai = null;
	    this.x = x;
	    this.y = y;
	    this.g = 0;
	    this.h = 0;
	    this.f = 0;
	}
	// Necessario para organizar a fila de prioridades
	@Override
	public int compareTo(Node o) {
		if ( this.f > o.f) return 1;
		if ( this.f < o.f) return -1;
		return 0;
	}
	
	 @Override
	public String toString() {
		return String.format("(%d, %d)",x,y);
	}
	
}
