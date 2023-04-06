package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelPaintColorFavorites extends JPanel {

	private static final long serialVersionUID = 1L;

	Color colorFavorite;
	
	PanelPaintColorFavorites(int r, int g, int b, int o) {
		colorFavorite = new Color(r, g, b , o);
	}
	
	public void paint (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(colorFavorite);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
}
