package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import dev.ArkNLA.pixelTiles.PixelTilesMain;

public class PanelDraw extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	JLabel userGridX = new JLabel("");
	JLabel userGridY = new JLabel("");
	
	Border border = new LineBorder(Color.black, 2);
	
	int pX, pY, line, stepX, stepY;
	
	PanelDraw() {
		
		userGridX.setText("Rows: " + PixelTilesMain.userGridLineRows);
		userGridY.setText("Cols: " + PixelTilesMain.userGridLineCols);
				
		pX = this.getWidth();
		pY = this.getHeight();

		add(userGridX);
		add(userGridY);
		
		this.setBorder(border);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Get size of Panel
		pX = this.getSize().width;
		pY = this.getSize().height;
		
		// Divide panel size by rows and cols
		stepX = (int)pY/PixelTilesMain.userGridLineRows;
		stepY = (int)pX/PixelTilesMain.userGridLineCols;
		
		// Correct panel size to evenly divide by rows cols
		pX -= pX%stepY;
		pY -= pY%stepX;
		
		// Draw rows
		line = stepX;
		for(int i = 0; i < PixelTilesMain.userGridLineRows; i++) {
			g.drawLine(0, line, pX, line);
			line += stepX;
		}

		// Draw Cols
		line = stepY;
		for(int i = 0; i < PixelTilesMain.userGridLineCols; i++) {
			g.drawLine(line, 0, line, pY);
			line += stepY;
		}
		
		// DEVELOPMENT: Show sizes
		userGridX.setText("Rows: " + stepX + "/" + pY);
		userGridY.setText("Cols: " + stepY + "/" + pX);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
