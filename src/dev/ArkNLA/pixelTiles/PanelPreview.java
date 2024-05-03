package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelPreview extends JPanel implements ChangeListener{
	
	/*
	 * 		4/4/2023
	 * 		Known Bugs:		-
	 * 		
	 * 		TODO: 			-
	 * 						
	 * 								
	 */
	
	int scale = 250;
	
	JSlider slideResize;
	
	JPanel paneImage;
	JPanel paneResize = new JPanel();
	
	PanelPreview() {
	
		paneImage = new JPanel()
		{
			public void paint (Graphics paneg) {
								
				Image image = PixelTilesMain.userImage;

				if (image != null) {
					
					image = PixelTilesMain.userImage.getScaledInstance(scale, scale, Image.SCALE_DEFAULT);
					
					// Paint squares behind image preview to show transparency
					
					paneg.setColor(Color.WHITE);
					paneg.fillRect(0, 0, this.getWidth(), this.getHeight());
					
					paneg.setColor(Color.LIGHT_GRAY);
					
					int transpWidth = 0;
					int transpHeight = 0;

					while (transpHeight < this.getHeight()) {
						
						while (transpWidth < this.getWidth()) {

							paneg.fillRect(transpWidth, transpHeight, 10, 10);
							transpWidth += 20;
						}
						
						if (transpHeight%20 == 0) {
							transpWidth = 10;
						} else {
							transpWidth = 0;
						}
						
						transpHeight += 10;
						
					}
					
					int iw = image.getWidth(this);
					int ih = image.getHeight(this);
					int pw = this.getWidth();
					int ph = this.getHeight();
					
					int startW = (pw - iw)/2;
					int startH = (ph - ih)/2;
					
					//paneg.drawImage(image, startW, startH, null);
					
					int step = 1;
							
					for (int i=0; i<100; i++) {
						for (int j=0; j<100; j++) {
							if (PixelTilesMain.userImageColorArray.getColorInArray(i, j) != null) {
								String[] temp = PixelTilesMain.userImageColorArray.getColorInArray(i, j).split(",");
								
								int r = Integer.parseInt(temp[0]);
								int g = Integer.parseInt(temp[1]);
								int b = Integer.parseInt(temp[2]);
								int a = Integer.parseInt(temp[3]);
								
								paneg.setColor(new Color(r,g,b,a));
								paneg.fillRect(i*step, j*step, step, step);
							}

						}
					}
					
				} else {
				
					paneg.setColor(Color.BLACK);
					paneg.fillRect(0, 0, 125, 125);
					paneg.setColor(Color.RED);
					paneg.fillRect(125, 0, 125, 125);
					paneg.setColor(Color.BLUE);
					paneg.fillRect(0, 125, 125, 125);
					paneg.setColor(Color.GREEN);
					paneg.fillRect(125, 125, 125, 125);
					
				}
				
			}
		};	
		
		setLayout(new BorderLayout());
		
		paneImage.setPreferredSize(new Dimension(250, 250));
		paneImage.setBackground(Color.LIGHT_GRAY);
		
		slideResize = new JSlider(50, 250, 250);
		slideResize.setMinorTickSpacing(25);
		slideResize.setMajorTickSpacing(50);
		slideResize.setPaintTicks(true);
		slideResize.setPaintLabels(true);
		slideResize.setSnapToTicks(true);
		slideResize.addChangeListener(this);
		
		paneResize.add(slideResize);
		
		add(paneImage, BorderLayout.CENTER);
		add(paneResize, BorderLayout.SOUTH);
		
		
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		scale = slideResize.getValue();
		
		repaint();
		
	}
}
