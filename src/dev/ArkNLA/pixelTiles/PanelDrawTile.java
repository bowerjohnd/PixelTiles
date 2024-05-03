package dev.ArkNLA.pixelTiles;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelDrawTile extends JPanel implements MouseListener, MouseMotionListener, ComponentListener{

	/*
	 * 		4/25/2023
	 * 		Known Bugs:		 	- Duplicate4S: doesn't work with mouse pressed
	 * 		
	 * 		TODO: 				- Get transparency working.
	 * 							- Move non-drawing in paint to methods where possible
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
	 * 		Draw Tools
	 */
	
	boolean toolActive = false;
	
	boolean toolMirrorVS = false;
	boolean toolMirrorHS = false;
	boolean toolMirror4S = false;
	boolean toolDuplicateVS = false;
	boolean toolDuplicateHS = false;
	boolean toolDuplicate4S = false;

	/*
	 * 		General use
	 */
	
	Graphics2D g2, g2pane;
	private int pX, pY, line, step, gridSize;
	private int gridSizeCorrected = 0;
	private Boolean boolClearImage = false;
	private int[] userColor = new int[4];
	private int gridX = 0, gridY = 0;
	private Point gridContainingMouse = new Point();
	
	int userGrid = 1;

	PanelDrawTile() {
		
		setLayout(new BorderLayout());
		
		mouseLoc = new Point(0, 0);
		addMouseMotionListener(this);
		addMouseListener(this);
		addComponentListener(this);
		
		setOpaque(false);
		
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics pane) {
		
		setUserColor();
		
		if (boolClearImage == true) {
			imageUserDrawn = null;
			boolClearImage = false;
		}

		// Stops anomalies outside draw area
		//pane.setColor(Color.WHITE);
		//pane.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw alternating white squares on background to indicate transparency
		
		pane.setColor(Color.LIGHT_GRAY);

		int transpWidth = 0;
		int transpHeight = 0;

		while (transpHeight < gridSizeCorrected) {

			while (transpWidth < gridSizeCorrected) {

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
			imageUserDrawn = new BufferedImage(gridSizeCorrected, gridSizeCorrected, BufferedImage.TYPE_INT_ARGB);

        	g2 = (Graphics2D) imageUserDrawn.createGraphics();
        	g2.setColor(new Color(0,0,0,0));
        	g2.fillRect(0, 0, gridSizeCorrected, gridSizeCorrected);
    	} else {
    		imageUserDrawn = (BufferedImage) getScaledImage(imageUserDrawn, gridSizeCorrected, gridSizeCorrected);
        	g2 = (Graphics2D) imageUserDrawn.getGraphics();
    	}

    	
    	/*
		 * 	Draw filled rectangle within draw grid
		 */
		
    	
		if (mousePressed || mouseClicked) {

			
			findGridContainingMouse();
	    	Point p = gridContainingMouse;
			int dx = p.x;
			int dy = p.y;
			

			
			g2.setColor(PixelTilesMain.userColor);
			g2.fillRect(dx, dy, step, step);

			pane.setColor(PixelTilesMain.userColor);
			pane.fillRect(dx, dy, step, step);
			PixelTilesMain.userImageColorArray.setColorInArray(gridX, gridY, userColor[0], userColor[1], userColor[2], userColor[3]);
			

			/*
			 * 		Draw tools
			 */
			
			//	Duplicate Vertical Split		***/*|***/*

			if (toolDuplicateVS) {
				if (mouseLoc.x < (gridSizeCorrected/2)) {
					mouseLoc.x = (gridSizeCorrected/2) + mouseLoc.x;
				} else {
					mouseLoc.x = mouseLoc.x-(gridSizeCorrected/2);
				}
				findGridContainingMouse();
		    	p = gridContainingMouse;
				g2.fillRect(p.x, dy, step, step);

				pane.fillRect(p.x, dy, step, step);
				PixelTilesMain.userImageColorArray.setColorInArray(p.x, dy, userColor[0], userColor[1], userColor[2], userColor[3]);
			
			}

			//	Duplicate Horizontal Split		***/******
			//									----------
			//                                  ***/******

			if (toolDuplicateHS) {
				if (mouseLoc.y < (gridSizeCorrected/2)) {
					mouseLoc.y = (gridSizeCorrected/2) + mouseLoc.y;
				} else {
					mouseLoc.y = mouseLoc.y-(gridSizeCorrected/2);
				}
				findGridContainingMouse();
		    	p = gridContainingMouse;
				g2.fillRect(dx, p.y, step, step);

				pane.fillRect(dx, p.y, step, step);
				PixelTilesMain.userImageColorArray.setColorInArray(dx, p.y, userColor[0], userColor[1], userColor[2], userColor[3]);

			}
			
			//	Duplicate 4x Split				***/*|***/*
			//                                  *****|*****
			//									-----------
			//	               					***/*|***/*
			//                                  *****|*****

			if (toolDuplicate4S) {
				if (mouseLoc.x < (gridSizeCorrected/2)) {
					mouseLoc.x = (gridSizeCorrected/2) + mouseLoc.x;
				} else {
					mouseLoc.x = mouseLoc.x-(gridSizeCorrected/2);
				}
				findGridContainingMouse();
		    	p = gridContainingMouse;
				g2.fillRect(p.x, dy, step, step);

				pane.fillRect(p.x, dy, step, step);
				PixelTilesMain.userImageColorArray.setColorInArray(p.x, dy, userColor[0], userColor[1], userColor[2], userColor[3]);

				if (mouseLoc.y < (gridSizeCorrected/2)) {
					mouseLoc.y = (gridSizeCorrected/2) + mouseLoc.y;
				} else {
					mouseLoc.y = mouseLoc.y-(gridSizeCorrected/2);
				}
				findGridContainingMouse();
		    	p = gridContainingMouse;
				g2.fillRect(dx, p.y, step, step);
				
				pane.fillRect(dx, p.y, step, step);
				PixelTilesMain.userImageColorArray.setColorInArray(dx, p.y, userColor[0], userColor[1], userColor[2], userColor[3]);
				
			}
			
			//	Mirror Vertical Split			***/*|*\***

			if (toolMirrorVS == true) {
				mouseLoc.x = gridSizeCorrected-mouseLoc.x;
				findGridContainingMouse();
		    	p = gridContainingMouse;
				g2.fillRect(p.x, dy, step, step);

				pane.fillRect(p.x, dy, step, step);
				PixelTilesMain.userImageColorArray.setColorInArray(p.x, dy, userColor[0], userColor[1], userColor[2], userColor[3]);

			}

			//	Mirror Horizontal Split			***/******
			//									----------
			//                                  ***\******

			if (toolMirrorHS == true) {
				mouseLoc.y = gridSizeCorrected-mouseLoc.y;
				findGridContainingMouse();
		    	p = gridContainingMouse;
				g2.fillRect(dx, p.y, step, step);

				pane.fillRect(dx, p.y, step, step);
				PixelTilesMain.userImageColorArray.setColorInArray(dx, p.y, userColor[0], userColor[1], userColor[2], userColor[3]);

			}

			//	Mirror 4x Split					***/*|*\***
			//                                  *****|*****
			//									-----------
			//	               					*****|*****
			//                                  ***\*|*/***

			if (toolMirror4S == true) {
				mouseLoc.x = gridSizeCorrected-mouseLoc.x;
				mouseLoc.y = gridSizeCorrected-mouseLoc.y;

				findGridContainingMouse();
		    	p = gridContainingMouse;

		    	g2.fillRect(p.x, dy, step, step);
				g2.fillRect(dx, p.y, step, step);
				
				pane.fillRect(p.x, dy, step, step);
				pane.fillRect(dx, p.y, step, step);
				PixelTilesMain.userImageColorArray.setColorInArray(p.x, dy, userColor[0], userColor[1], userColor[2], userColor[3]);
				PixelTilesMain.userImageColorArray.setColorInArray(dx, p.y, userColor[0], userColor[1], userColor[2], userColor[3]);

				mouseLoc.x = gridSizeCorrected-mouseLoc.x;
				mouseLoc.y = gridSizeCorrected-mouseLoc.y;
				g2.fillRect(p.x, p.y, step, step);
				
				pane.fillRect(p.x, p.y, step, step);
				PixelTilesMain.userImageColorArray.setColorInArray(p.x, p.y, userColor[0], userColor[1], userColor[2], userColor[3]);
			}

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
		
		//pane.drawImage(imageUserDrawn, 0, 0, gridSizeCorrected, gridSizeCorrected, this);

		
		/*
		 * 			Paint array onto jpanel
		 */
		
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				if (PixelTilesMain.userImageColorArray.getColorInArray(i, j) != null) {
					String[] temp = PixelTilesMain.userImageColorArray.getColorInArray(i, j).split(",");
					
					int r = Integer.parseInt(temp[0]);
					int g = Integer.parseInt(temp[1]);
					int b = Integer.parseInt(temp[2]);
					int a = Integer.parseInt(temp[3]);
					
					pane.setColor(new Color(r,g,b,a));
					pane.fillRect(i*step, j*step, step, step);
				}

			}
		}
		
		
		/*
		 * 	set global user image to pane image before drawing grid lines
		 */
		
		PixelTilesMain.userImage = imageUserDrawn;
		
		/*
		 *  Draw grid lines over user image
		 */
		
		pane.setColor(Color.BLACK);
					
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
		
		// Draw thicker split lines when toolActive is true
		
		g2pane = (Graphics2D) pane;
		
		if (toolActive) {			
			g2pane.setStroke(new BasicStroke(5.0F));
			
			if (toolDuplicateVS || toolMirrorVS) {
				g2pane.drawLine(gridSizeCorrected/2, 0, gridSizeCorrected/2, gridSizeCorrected);
			}
			if (toolDuplicateHS || toolMirrorHS) {
				g2pane.drawLine(0, gridSizeCorrected/2, gridSizeCorrected, gridSizeCorrected/2);
			}
			if (toolDuplicate4S || toolMirror4S) {
				g2pane.drawLine(0, gridSizeCorrected/2, gridSizeCorrected, gridSizeCorrected/2);
				g2pane.drawLine(gridSizeCorrected/2, 0, gridSizeCorrected/2, gridSizeCorrected);
			}
		}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseClicked = true;
			repaint();
			PanelTools.panePreview.repaint();
		}
		
		if (e.getButton() == MouseEvent.BUTTON3) {
//			int x = e.getX();
//			int y = e.getY();
//			int c = imageUserDrawn.getRGB(x, y);
//			int a = (c>>24) & 0xff;
//			int r = (c>>16) & 0xff;
//			int g = (c>>8) & 0xff;
//			int b = c & 0xff;
			int r = 0, g = 0, b = 0, a = 0;
			findGridContainingMouse();
			
			try {
				String[] temp = PixelTilesMain.userImageColorArray.getColorInArray(gridX,gridY).split(",");
				r = Integer.parseInt(temp[0]);
				g = Integer.parseInt(temp[1]);
				b = Integer.parseInt(temp[2]);
				a = Integer.parseInt(temp[3]);
			} catch (NullPointerException ne) {
				r = 0;
				g = 0;
				b = 0;
				a = 0;				
			} catch (Exception ex) {
				r = 0;
				g = 0;
				b = 0;
				a = 255;
			}
			
			PixelTilesMain.userColor = new Color(r, g, b, a);
			PixelTilesMain.paneColorSelectTileFactory.setColor(r, g, b, a);	
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			mousePressed = true;
			repaint();
			PanelTools.panePreview.repaint();
		}
		
		if (e.getButton() == MouseEvent.BUTTON3) {
			
//			int x = e.getX();
//			int y = e.getY();
//			int c = imageUserDrawn.getRGB(x, y);
//			int a = (c>>24) & 0xff;
//			int r = (c>>16) & 0xff;
//			int g = (c>>8) & 0xff;
//			int b = c & 0xff;
			int r = 0, g = 0, b = 0, a = 0;
			findGridContainingMouse();

			try {
				String[] temp = PixelTilesMain.userImageColorArray.getColorInArray(gridX,gridY).split(",");
				r = Integer.parseInt(temp[0]);
				g = Integer.parseInt(temp[1]);
				b = Integer.parseInt(temp[2]);
				a = Integer.parseInt(temp[3]);
			} catch (NullPointerException ne) {
				r = 0;
				g = 0;
				b = 0;
				a = 0;				
			} catch (Exception ex) {
				r = 0;
				g = 0;
				b = 0;
				a = 255;
			}

			PixelTilesMain.userColor = new Color(r, g, b, a);
			PixelTilesMain.paneColorSelectTileFactory.setColor(r, g, b, a);	
		}
		
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
		//repaint();

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
		
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				
				PixelTilesMain.userImageColorArray.setColorInArray(i, j, 255, 255, 255, 0);

			}
		}
		
		boolClearImage = true;
	}
	
	private void findGridContainingMouse() {
		
		int dx = 0;
		int dy = 0;
		int countX = 0;
		int countY = 0;
		
		// find x grid
		for (int i = 0; i < gridSizeCorrected+step; i += step) {
			
			countX++;
			
			if (mouseLoc.x < i) {
				dx = i - step;
				break;
			} else {
				dx = gridSizeCorrected - step;
			}
		}
		
		// find y grid
		for (int i = 0; i < gridSizeCorrected+step; i += step) {
			
			countY++;
			
			if (mouseLoc.y < i) {
				dy = i - step;
				break;
			} else {
				dy = gridSizeCorrected - step;
			}
		}
		
		if (countX-2 < 0) {
			this.gridX = 0;
		} else {		
			this.gridX = countX-2;
		}
		
		if (countY-2 < 0) {
			this.gridY = 0;
		} else {
			this.gridY = countY-2;
		}
		
		gridContainingMouse = new Point(dx, dy);
	}
	
	public void setDrawTools(String tool) {
		
		switch(tool) {
			case "mvs" : 
				if (toolMirrorVS) {
					toolMirrorVS = false;
					toolActive = false;
				} else {
					toolMirrorVS = true;
					toolActive = true;
				}
				break;
			case "mhs" : 
				if (toolMirrorHS) {
					toolMirrorHS = false;
					toolActive = false;
				} else {
					toolMirrorHS = true;
					toolActive = true;
				}
				break;
			case "m4s" : 
				if (toolMirror4S) {
					toolMirror4S = false;
					toolActive = false;
				} else {
					toolMirror4S = true;
					toolActive = true;
				}
				break;
			case "dvs" : 
				if (toolDuplicateVS) {
					toolDuplicateVS = false;
					toolActive = false;
				} else {
					toolDuplicateVS = true;
					toolActive = true;
				}
				break;
			case "dhs" : 
				if (toolDuplicateHS) {
					toolDuplicateHS = false;
					toolActive = false;
				} else {
					toolDuplicateHS = true;
					toolActive = true;
				}
				break;
			case "d4s" : 
				if (toolDuplicate4S) {
					toolDuplicate4S = false;
					toolActive = false;
				} else {
					toolDuplicate4S = true;
					toolActive = true;
				}
				break;
		}
	}
	
	private void setUserColor() {
		userColor[0] = PixelTilesMain.userColor.getRed();
		userColor[1] = PixelTilesMain.userColor.getGreen();
		userColor[2] = PixelTilesMain.userColor.getBlue();
		userColor[3] = PixelTilesMain.userColor.getAlpha();
	}
	
	public void disableAllDrawTools() {
		toolActive = false;
		toolMirrorVS = false;
		toolMirrorHS = false;
		toolMirror4S = false;
		toolDuplicateVS = false;
		toolDuplicateHS = false;
		toolDuplicate4S = false;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		repaint();
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		repaint();
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
