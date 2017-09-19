package br.com.ifes.astar.visao;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import br.com.ifes.astar.application.Application;
import br.com.ifes.astar.constantes.Constantes;
import br.com.ifes.astar.modelo.Celula;

public class PainelGrid extends JPanel implements Constantes {

	private static final long serialVersionUID = 1L;

	public PainelGrid() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		for (int linha = 0; linha < Application.TAM_LINHA; linha++) {
			for (int col = 0; col < Application.TAM_COLUNA; col++) {
				gbc.gridx = col;
				gbc.gridy = linha;
				Celula celula = new Celula();
				switch (Application.mapa[linha][col]) {
				case LIVRE:
					celula.setBackground(Color.WHITE);
					break;
				case PAREDE:
					celula.setBackground(Color.BLACK);
					break;
				case INICIO:
					celula.setBackground(Color.YELLOW);
					break;
				case VISITADO:
					celula.setBackground(Color.GREEN);
					break;
				case OBJETIVO:
					celula.setBackground(Color.RED);
					break;
				}
				Border border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
				celula.setBorder(border);
				add(celula, gbc);
			}
		}
	}
}
