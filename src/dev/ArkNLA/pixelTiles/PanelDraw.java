package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

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
	 * 								- bug possibly in way image is resized...
	 * 								- bug when selecting size, it changes image size
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
    BufferedImage resizedImg = null;
	
	/*
	 * 		General use
	 */
    	
    private Color colorInUse = Color.BLACK;
	private int pX, pY, line, step, gridSize;
	private int gridSizeCorrected = 0;

	int userGrid = 1;

	PanelDraw() {
		
		setLayout(new BorderLayout());
		
		mouseLoc = new Point(0, 0);
		addMouseMotionListener(this);
		addMouseListener(this);
		
	}
	
	@Override
	public void paint(Graphics pane) {
		
		// Stops anomalies outside draw area
		pane.setColor(Color.LIGHT_GRAY);
		pane.fillRect(0, 0, this.getWidth(), this.getHeight());
		

		// Get panel size before painting in case of window resizing
		
		panelSize();
		
		/*
		 * 	Off screen image to prevent flickering
		 */

    	if (imageUserDrawn == null) {
    		imageUserDrawn = createImage(gridSizeCorrected, gridSizeCorrected);
    	} else {
    		imageUserDrawn = getScaledImage(imageUserDrawn, gridSizeCorrected, gridSizeCorrected);
    	}

    	Graphics g = imageUserDrawn.getGraphics();
    	/*
		 * 	Draw filled rectangle within draw grid
		 */
		
		// find grid square mouse cursor is in
		
		if (mousePressed || mouseClicked) {

			int dx = 0;
			int dy = 0;
			
			// find x grid
			for (int i = 0; i < gridSizeCorrected+step; i += step) {
				
				if (mouseLoc.x < i) {
					dx = i - step;
					break;
				} else {
					dx = gridSizeCorrected - step;
				}
			}
			
			// find y grid
			for (int i = 0; i < gridSizeCorrected+step; i += step) {
				
				if (mouseLoc.y < i) {
					dy = i - step;
					break;
				} else {
					dy = gridSizeCorrected - step;
				}
			}
						
			g.setColor(PixelTilesMain.userColor);
			g.fillRect(dx, dy, step, step);							
			
			//System.out.println("dx: " + dx + " / dy: " + dy + " / step: " + step);

			mouseClicked = false;
		}
		
		/*
		 * 	Draw off screen image to pane
		 */
		

		pane.drawImage(imageUserDrawn, 0, 0, gridSizeCorrected, gridSizeCorrected, this);

		
		
		/*
		 * 	set global user image to pane image before drawing grid lines
		 */
		
		PixelTilesMain.userImage = imageUserDrawn;
		
		/*
		 *  Draw grid lines over user image
		 */
						
		// Draw rows
		line = step;
		for(int i = 0; i < userGrid; i++) {
			pane.drawLine(0, line, gridSizeCorrected, line);
			line += step;
		}

		// Draw cols
		line = step;
		for(int i = 0; i < userGrid; i++) {
			pane.drawLine(line, 0, line, gridSizeCorrected);
			line += step;
		}
		
		// DEVELOPMENT: Show sizes
		PixelTilesMain.labelDevMouseGridInfo.setText("Rows: " + step + "/" + gridSizeCorrected + "/ mX:" + mouseLoc.x
									+ " ---- Cols: " + step + "/" + gridSizeCorrected + "/ mY:" + mouseLoc.y);

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
		
		if (x > gridSizeCorrected) x = gridSizeCorrected;
		if (y > gridSizeCorrected) y = gridSizeCorrected;
		
		mouseLoc.x = x;
		mouseLoc.y = y;
		
		repaint();
		PixelTilesMain.panePreview.repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		
		if (x > gridSizeCorrected) x = gridSizeCorrected;
		if (y > gridSizeCorrected) y = gridSizeCorrected; 
		
		mouseLoc.x = x;
		mouseLoc.y = y;
		repaint();

	}
	
	private void panelSize() {
		
		// Get panel size
		
		pX = this.getWidth();
		pY = this.getHeight();
		
		// Use smaller to create square grid, corrected for mod (%) remainder

		if (pX > pY) {
			gridSize = pY;
		} else {
			gridSize = pX;
		}
		
		/*
		 * Adjust grid to fit steps evenly
		 * 
		 */

		userGrid = PixelTilesMain.userGridSize;
		
		step = (int)gridSize/userGrid;

		gridSizeCorrected = gridSize - gridSize%userGrid;
			
	}
	
	/**
	 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
	 * Resizes an image using a Graphics2D object backed by a BufferedImage.
	 * @param srcImg - source image to scale
	 * @param w - desired width
	 * @param h - desired height
	 * @return - the new resized image
	 */
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();        
	    return resizedImg;
	}

}
