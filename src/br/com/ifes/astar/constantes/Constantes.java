package br.com.ifes.astar.constantes;

public interface Constantes {
	public static int TAM_LINHA = 13;
	public static int TAM_COLUNA = 10;

	static final int LIVRE = 1;
	static final int PAREDE = 2;
	static final int OBJETIVO = 3;
	static final int INICIO = 4;
	static final int VISITADO = 5;

	static int INICIO_X = 0;
	static int INICIO_Y = 0;

	static int FIM_X = 0;
	static int FIM_Y = 10;
}
