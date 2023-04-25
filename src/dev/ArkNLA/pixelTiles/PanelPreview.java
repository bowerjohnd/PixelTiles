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
			public void paint (Graphics g) {
								
				Image image = PixelTilesMain.userImage;

				if (image != null) {
					
					image = PixelTilesMain.userImage.getScaledInstance(scale, scale, Image.SCALE_DEFAULT);
					
					// Paint squares behind image preview to show transparency
					
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, this.getWidth(), this.getHeight());
					
					g.setColor(Color.LIGHT_GRAY);
					
					int transpWidth = 0;
					int transpHeight = 0;

					while (transpHeight < this.getHeight()) {
						
						while (transpWidth < this.getWidth()) {

							g.fillRect(transpWidth, transpHeight, 10, 10);
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
					
					g.drawImage(image, startW, startH, null);
				
				} else {
				
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, 125, 125);
					g.setColor(Color.RED);
					g.fillRect(125, 0, 125, 125);
					g.setColor(Color.BLUE);
					g.fillRect(0, 125, 125, 125);
					g.setColor(Color.GREEN);
					g.fillRect(125, 125, 125, 125);
					
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
