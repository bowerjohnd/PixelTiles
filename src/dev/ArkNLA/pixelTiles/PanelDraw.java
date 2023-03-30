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
	
	private int pX, pY, line, stepX, stepY, gridSize;
	
	PanelDraw() {
		
		userGridX.setText("Rows: " + PixelTilesMain.userGridLineRows);
		userGridY.setText("Cols: " + PixelTilesMain.userGridLineCols);
				
		pX = this.getWidth();
		pY = this.getHeight();
		
		if (pX > pY) {
			gridSize = pY;
		} else {
			gridSize = pX;
		}

		add(userGridX);
		add(userGridY);
		
		this.setBorder(border);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int gridSizeCorrectedX = 0;
		int gridSizeCorrectedY = 0;
		int uglRows = PixelTilesMain.userGridLineRows;
		int uglCols = PixelTilesMain.userGridLineCols;
		
		// Get size of Panel and use smaller to create square grid, corrected for mod (%) remainder
		pX = this.getWidth();
		pY = this.getHeight();
		
		if (pX > pY) {
			
			// Height is smaller than width
			gridSize = pY;
			stepX = (int)gridSize/uglRows;
			stepY = (int)gridSize/uglCols;

			gridSizeCorrectedX = gridSize - gridSize%uglCols;
			gridSizeCorrectedY = gridSize - gridSize%uglRows;
			
		} else {
			
			// Width is smaller than height
			gridSize = pX;
			stepX = (int)gridSize/uglRows;
			stepY = (int)gridSize/uglCols;

			gridSizeCorrectedX = gridSize - gridSize%uglCols;
			gridSizeCorrectedY = gridSize - gridSize%uglRows;

		}
						
		// Draw rows
		line = stepX;
		for(int i = 0; i < uglRows; i++) {
			g.drawLine(0, line, gridSizeCorrectedX, line);
			line += stepX;
		}

		// Draw Cols
		line = stepY;
		for(int i = 0; i < uglCols; i++) {
			g.drawLine(line, 0, line, gridSizeCorrectedY);
			line += stepY;
		}
		
		// DEVELOPMENT: Show sizes
		userGridX.setText("Rows: " + stepX + "/" + gridSizeCorrectedX + "/" + pX);
		userGridY.setText("Cols: " + stepY + "/" + gridSizeCorrectedY + "/" + pY);

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
