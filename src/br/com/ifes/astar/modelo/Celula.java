package br.com.ifes.astar.modelo;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Celula extends JPanel {

	private static final long serialVersionUID = 1L;
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }    
}