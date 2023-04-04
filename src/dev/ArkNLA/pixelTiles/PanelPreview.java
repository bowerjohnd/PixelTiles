package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelPreview extends JPanel{
	
	public void paint (Graphics g) {
		
		Image image = PixelTilesMain.userImage;

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 125, 125);
		g.setColor(Color.RED);
		g.fillRect(125, 0, 125, 125);
		g.setColor(Color.BLUE);
		g.fillRect(0, 125, 125, 125);
		g.setColor(Color.GREEN);
		g.fillRect(125, 125, 125, 125);
		
		if (image != null) {
			
			image = PixelTilesMain.userImage.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
			g.drawImage(image, 0, 0, null);
		}
		
	}
	
	PanelPreview() {
		setSize(250,250);
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
