package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PanelDraw extends JPanel implements MouseListener, MouseMotionListener{

	/*
	 * 		4/4/2023
	 * 		Known Bugs:		- Anomalies on pane
	 * 		
	 * 		TODO: 			- Fix bugs
	 * 						- Allow resize: resize pane to correctedX/Y, resize image/draw area to fit pane
	 * 								
	 */
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 		Tracking mouse in panel
	 */
	
	Boolean mousePressed = false;
	Boolean mouseClicked = false;
	Point mouseLoc;

	/*
	 * 		Image drawn by user
	 */
		
	Image imageUserDrawn = null;
	
	/*
	 * 		Grid Lines - drawn over image - not saved on image
	 */
	
	JLabel userGridX = new JLabel("");
	JLabel userGridY = new JLabel("");
	
	Border border = new LineBorder(Color.black, 2);
	
	private int pX, pY, line, stepX, stepY, gridSize;
	private int gridSizeCorrectedX = 0;
	private int gridSizeCorrectedY = 0;

	int uglRows = 1;
	int uglCols = 1;

	PanelDraw() {
		
		mouseLoc = new Point(0, 0);
		addMouseMotionListener(this);
		addMouseListener(this);


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
	public void paint(Graphics pane) {
		
		pane.setColor(Color.LIGHT_GRAY);
		pane.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		/*
		 * Adjust grid and grid steps when user resizes screen
		 * 
		 */
		
		// Get size of Panel and use smaller to create square grid, corrected for mod (%) remainder
		pX = this.getWidth();
		pY = this.getHeight();

		uglRows = PixelTilesMain.userGridLineRows;
		uglCols = PixelTilesMain.userGridLineCols;

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
		
		/*
		 * 	Off screen image to prevent flickering
		 */
		
        Dimension dimen = new Dimension(gridSizeCorrectedX, gridSizeCorrectedY);

    	if (imageUserDrawn == null) {
    		imageUserDrawn = createImage(dimen.width, dimen.height);
    	}
    	
    	Graphics g = imageUserDrawn.getGraphics();
		

    	/*
		 * 	Draw filled rectangle within draw grid
		 */
		
		// find grid line starting point the mouse cursor is in
		
		if (mousePressed || mouseClicked) {

			int dx = 0;
			int dy = 0;
			
			for (int i = 0; i < gridSizeCorrectedX+stepX; i += stepX) {
				
				if (mouseLoc.x < i) {
					dx = i - stepX;
					break;
				} else {
					dx = gridSizeCorrectedX - stepX;
				}
			}
			
			for (int i = 0; i < gridSizeCorrectedY+stepY; i += stepY) {
				
				if (mouseLoc.y < i) {
					dy = i - stepY;
					break;
				} else {
					dy = gridSizeCorrectedY - stepY;
				}
			}
						
			g.setColor(PixelTilesMain.userColor);
			g.fillRect(dx, dy, stepX, stepY);
				
			mouseClicked = false;
		}
		
		/*
		 * 	Draw off screen image to pane
		 */
		
		pane.drawImage(imageUserDrawn, 0, 0, this);
		
		/*
		 * 	set global user image to off screen image
		 */
		
		PixelTilesMain.userImage = imageUserDrawn;
		
		/*
		 *  Draw grid lines over image
		 */
						
		// Draw rows
		line = stepX;
		for(int i = 0; i < uglRows; i++) {
			pane.drawLine(0, line, gridSizeCorrectedX, line);
			line += stepX;
		}

		// Draw cols
		line = stepY;
		for(int i = 0; i < uglCols; i++) {
			pane.drawLine(line, 0, line, gridSizeCorrectedY);
			line += stepY;
		}
		
		// DEVELOPMENT: Show sizes
		userGridX.setText("Rows: " + stepX + "/" + gridSizeCorrectedX + "/ mX:" + mouseLoc.x);
		userGridY.setText("Cols: " + stepY + "/" + gridSizeCorrectedY + "/ mY:" + mouseLoc.y);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		mouseClicked = true;
		repaint();
		PixelTilesMain.panePreview.repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		mousePressed = true;
		repaint();
		PixelTilesMain.panePreview.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		mousePressed = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		mousePressed = true;
		
		int x = e.getX();
		int y = e.getY();
		
		if (x > gridSizeCorrectedX)	x = gridSizeCorrectedX;
		if (y > gridSizeCorrectedY) y = gridSizeCorrectedY;
		
		mouseLoc.x = x;
		mouseLoc.y = y;
		
		repaint();
		PixelTilesMain.panePreview.repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		
		if (x > gridSizeCorrectedX)	x = gridSizeCorrectedX;
		if (y > gridSizeCorrectedY) y = gridSizeCorrectedY; 
		
		mouseLoc.x = x;
		mouseLoc.y = y;
		repaint();

	}

}
