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
	 * 		Known Bugs:		none.
	 * 		
	 * 		TODO: 			- Add resize buttons below preview image.
	 * 						- Add transparency indicator.
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
				
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0,0,250,250);
								
				Image image = PixelTilesMain.userImage;

				if (image != null) {
					
					image = PixelTilesMain.userImage.getScaledInstance(scale, scale, Image.SCALE_DEFAULT);
					
					// Paint tiny grid behind image preview to show opacity
					
					for (int i = 0; i < 250; i++) {
						
						if (i%2 == 0) {
							g.setColor(Color.WHITE);
							g.drawLine(i, 0, i, 250);
							g.drawLine(0, i, 250, i);				
						} else {
							g.setColor(Color.DARK_GRAY);
							g.drawLine(i, 0, i, 250);
							g.drawLine(0, i, 250, i);
						}

					}
					
					g.drawImage(image, 0, 0, null);
				
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
