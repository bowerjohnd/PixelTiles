package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelDraw extends JPanel implements MouseListener, MouseMotionListener{

	/*
	 * 		4/4/2023
	 * 		Known Bugs:		
	 * 		
	 * 		TODO: 			- Get transparency working...
	 * 								
	 */
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 		Tracking mouse in panel
	 */
	
	private Boolean mousePressed = false;
	private Boolean mouseClicked = false;
	private Point mouseLoc;

	/*
	 * 		Image drawn by user
	 */
		
	private BufferedImage imageUserDrawn = null;
	
	/*
	 * 		General use
	 */
	
	Graphics2D g2;
	private int pX, pY, line, step, gridSize;
	private int gridSizeCorrected = 0;
	private Boolean boolClearImage = false;
	
	int userGrid = 1;

	PanelDraw() {
		
		setLayout(new BorderLayout());
		
		mouseLoc = new Point(0, 0);
		addMouseMotionListener(this);
		addMouseListener(this);
		
		setOpaque(false);
		
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics pane) {
		
		if (boolClearImage == true) {
			imageUserDrawn = null;
			boolClearImage = false;
		}

		// Stops anomalies outside draw area
		pane.setColor(Color.WHITE);
		pane.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw alternating white squares on background to indicate transparency
		pane.setColor(Color.LIGHT_GRAY);
		
		int transpWidth = 0;
		int transpHeight = 0;

		while (transpHeight < this.getHeight()) {
			
			while (transpWidth < this.getWidth()) {

				pane.fillRect(transpWidth, transpHeight, 10, 10);
				transpWidth += 20;
			}
			
			if (transpHeight%20 == 0) {
				transpWidth = 10;
			} else {
				transpWidth = 0;
			}
			
			transpHeight += 10;
			
		}
		
		// Get panel size before painting in case of window resizing
		
		panelSize();
		
		/*
		 * 	Off screen image to prevent flickering
		 */

    	if (imageUserDrawn == null) {
    		try {
				imageUserDrawn = ImageIO.read(new File("transparent-master.png"));
			} catch (IOException e) {
				imageUserDrawn = new BufferedImage(gridSizeCorrected, gridSizeCorrected, BufferedImage.TYPE_INT_ARGB);
			}
        	g2 = (Graphics2D) imageUserDrawn.createGraphics();
        	g2.setColor(Color.white);
        	g2.fillRect(0, 0, gridSizeCorrected, gridSizeCorrected);
    	} else {
    		imageUserDrawn = (BufferedImage) getScaledImage(imageUserDrawn, gridSizeCorrected, gridSizeCorrected);
        	g2 = (Graphics2D) imageUserDrawn.getGraphics();
    	}

    	
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
			
			g2.setColor(PixelTilesMain.userColor);
			g2.fillRect(dx, dy, step, step);							
			
			//System.out.println("dx: " + dx + " / dy: " + dy + " / step: " + step);

			mouseClicked = false;
		}

		/*
		 * 	Draw off screen image to pane
		 */
		
		/*
		// Testing transparency on new transparency background, working.
		pane.setColor(new Color(75,75,75,50));
		pane.fillRect(0, 0, 200, 200);
		pane.setColor(new Color(125,125,0,100));
		pane.fillRect(200, 0, 200, 200);
		pane.setColor(new Color(125,0,125,150));
		pane.fillRect(0, 200, 200, 200);
		pane.setColor(new Color(0,125,125,200));
		pane.fillRect(200, 200, 200, 200);
		pane.setColor(new Color(0,0,0,225));
		pane.fillRect(0, 400, 200, 200);
		pane.setColor(new Color(175,200,100,255));
		pane.fillRect(400, 400, 200, 200);
		pane.setColor(Color.LIGHT_GRAY);
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
		PanelTools.panePreview.repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		mousePressed = true;
		repaint();
		PanelTools.panePreview.repaint();
		
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
		PanelTools.panePreview.repaint();
		
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

	public void clearImage() {
		boolClearImage = true;
	}
}
