package br.com.ifes.astar.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import br.com.ifes.astar.constantes.Constantes;
import br.com.ifes.astar.modelo.Node;
import br.com.ifes.astar.visao.GRID;

/* 
 * Classe principal do algoritmo A Star 
 * Aqui eu aplico o algoritmo sobre o mapa especificando o OBJETIVO, etc
 * 
 * para definir um novo ponto de entrada é necessario:
 * - ALTERAR NA VARIAVEL MAPA O NOVO PONTO DE INICIO.
 * - ALTERAR NA CLASSE CONSTANTES A POSICAO X,Y DO INICIO
 * 
 * para definir um novo ponto de objetivo basta seguir as mesmas 
 * instrucoes para o ponto de entrada
 * 
 * Author: Antonio Ricardo Alexandre Brasil
 * MPECA 2017-2
 */

public class Application implements Constantes {

	// Mapa do nosso maze
	public static int[][] mapa = { 
			{ INICIO, LIVRE, PAREDE, LIVRE, LIVRE, LIVRE,   LIVRE, PAREDE, LIVRE, OBJETIVO }, 
			{ LIVRE, LIVRE, PAREDE, LIVRE, PAREDE,  PAREDE, LIVRE, PAREDE, LIVRE, PAREDE },
			{ LIVRE, LIVRE, PAREDE, LIVRE, PAREDE,  PAREDE, LIVRE, LIVRE, LIVRE, PAREDE },
			{ LIVRE, LIVRE, PAREDE, LIVRE, PAREDE,  PAREDE, PAREDE, PAREDE, PAREDE, PAREDE },
			{ LIVRE, LIVRE, PAREDE, LIVRE, PAREDE,  PAREDE, PAREDE, PAREDE, PAREDE, LIVRE },
			{ LIVRE, LIVRE, PAREDE, LIVRE, LIVRE, LIVRE, LIVRE, LIVRE, LIVRE, LIVRE },
			{ LIVRE, PAREDE, PAREDE, PAREDE, PAREDE,PAREDE, PAREDE, PAREDE, PAREDE, LIVRE }, 
			{ LIVRE, PAREDE, LIVRE, LIVRE, LIVRE,  LIVRE, LIVRE, LIVRE, LIVRE, LIVRE}, 
			{ LIVRE, PAREDE, LIVRE, PAREDE, LIVRE, LIVRE, LIVRE, LIVRE, LIVRE, LIVRE }, 
			{ LIVRE, PAREDE, LIVRE, PAREDE, PAREDE,PAREDE, PAREDE, PAREDE, PAREDE, PAREDE }, 
			{ LIVRE, PAREDE, LIVRE, LIVRE, LIVRE,  LIVRE, LIVRE, LIVRE, LIVRE, LIVRE }, 
			{ LIVRE, PAREDE, PAREDE, PAREDE, PAREDE, PAREDE, PAREDE, PAREDE, PAREDE, LIVRE }, 
			{ LIVRE, LIVRE, LIVRE, LIVRE, LIVRE,   LIVRE, LIVRE, LIVRE, LIVRE, LIVRE }, 
	};
	// Lista que armazena todos os nos visitados ate o objetivo
	public static List<Node> visitados = new ArrayList<Node>();

	public static void main(String[] args) {
		GRID grid = new GRID();
		// Aqui eu chamo a busca heuristica
		int iteracoes = buscaHeuristica();
		// Printando a quantidade de iteracoes ate o objetivo
		System.out.println("Quantidade de iteracoes: " + iteracoes);
		// Funcao para imprimir o mapa em texto
		imprimirMapa();
		visitados.parallelStream().forEach(v -> System.out.print(v));
		// Caso voce nao queira ver o GRID, basta comentar a linha abaixo
		// Essa funcao cria o grid usando Panel em Java
		 grid = new GRID();
	}

	/*
	 * Esse algoritmo foi implementado a partir do pseudo-codigo fornecido em:
	 * http://web.mit.edu/eranki/www/tutorials/search/
	 */
	public static int buscaHeuristica() {
		int iteracoes = 0;
		// Crio o no inicial
		Node no_inicio = new Node(INICIO_X, INICIO_Y);
		// Crio a closedlist
		Set<Node> closedlist = new HashSet<Node>();
		// Priority queue implementando o metodo comparable dos NOS
		PriorityQueue<Node> openlist = new PriorityQueue<Node>();
		// De acordo com o pseudo codigo, adicionamos o no inicial na open list
		openlist.add(no_inicio);
		// Enquanto a open list nao e vazia
		while ((!openlist.isEmpty())) {
			// Encontramos o no com o menor f e chamamos ele de 'q' (atual)
			Node atual = openlist.poll();
			
			// Apenas para deixar o NO inicial com a cor original
			if (mapa[atual.x][atual.y] != INICIO) 
				mapa[atual.x][atual.y] = VISITADO;
			iteracoes += 1;

			// Criamos os sucessores do no atual
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (ultrapassaLimites(atual.x + i, atual.y + j)) {
						boolean adicionar = true;

						Node sucessor = new Node(atual, atual.x + i, atual.y + j, FIM_X, FIM_Y);
						if (mapa[sucessor.x][sucessor.y] == PAREDE || mapa[sucessor.x][sucessor.y] == VISITADO) 
							adicionar = false;
						if (mapa[sucessor.x][sucessor.y] == OBJETIVO) 
							return iteracoes;
						for (Node no : openlist) {
							if (no.x == sucessor.x && no.y == sucessor.y && no.f < sucessor.f) 
								adicionar = false;
						}

						for (Node no : closedlist) {
							if (no.x == sucessor.x && no.y == sucessor.y && no.f < sucessor.f) {
								adicionar = false;
							}
						}

						if (adicionar)
							openlist.add(sucessor);
					}
				}
			}

			closedlist.add(atual);
		}

		
		return iteracoes;

	}

	// Funcao para verificar se o no criado nao ultrapassa os limites do mapa
	public static boolean ultrapassaLimites(int x, int y) {
		return ((x >= 0) && (x < TAM_LINHA) && (y >= 0) && (y < TAM_COLUNA));
	}

	// Impressao do mapa em texto
	public static void imprimirMapa() {

		for (int i = 0; i < TAM_LINHA; i++) {
			for (int j = 0; j < TAM_COLUNA; j++) {
				switch (mapa[i][j]) {
				case LIVRE:
					System.out.print("L");
					break;
				case PAREDE:
					System.out.print("|");
					break;
				case INICIO:
					System.out.print("I");
					break;
				case VISITADO: {
					visitados.add(new Node(i, j));
					System.out.print("X");
					break;
				}
				case OBJETIVO:
					System.out.print("O");
					break;
				}
			}
			System.out.println("");
		}
	}
}
