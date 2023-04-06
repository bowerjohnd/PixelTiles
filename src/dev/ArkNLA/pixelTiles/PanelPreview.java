package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelPreview extends JPanel implements ActionListener{
	
	/*
	 * 		4/4/2023
	 * 		Known Bugs:		none.
	 * 		
	 * 		TODO: 			- Add resize buttons below preview image.
	 * 						- Add transparency indicator.
	 * 								
	 */
	
	int scaleX = 250, scaleY = 250;
	int size1 = 25;
	int size2 = 50;
	int size3 = 100;
	int size4 = 150;
	int size5 = 250;	
	
	JButton butSize1 = new JButton(size1 + "px");
	JButton butSize2 = new JButton(size2 + "px");
	JButton butSize3 = new JButton(size3 + "px");
	JButton butSize4 = new JButton(size4 + "px");
	JButton butSize5 = new JButton(size5 + "px");
	
	JPanel paneImage = new JPanel();
	JPanel paneButtons = new JPanel();
	
	public void paint (Graphics g) {
		
		Image image = PixelTilesMain.userImage;

		if (image != null) {
			
			image = PixelTilesMain.userImage.getScaledInstance(scaleX, scaleY, Image.SCALE_DEFAULT);
			
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
	
	PanelPreview() {

		/*
		setLayout(new BorderLayout());
		
		paneImage.setPreferredSize(new Dimension(250, 250));
		
		add(paneImage, BorderLayout.CENTER);
		
		butSize1.addActionListener(this);
		butSize2.addActionListener(this);
		butSize3.addActionListener(this);
		butSize4.addActionListener(this);
		butSize5.addActionListener(this);
		
		
		paneButtons.add(butSize1);
		paneButtons.add(butSize2);
		paneButtons.add(butSize3);
		paneButtons.add(butSize4);
		paneButtons.add(butSize5);

		add(paneButtons, BorderLayout.SOUTH);
		*/
		
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == butSize1) {
			scaleX = size1;
			scaleY = size1;
		}
		if (source == butSize2) {
			scaleX = size2;
			scaleY = size2;
		}
		if (source == butSize3) {
			scaleX = size3;
			scaleY = size3;
		}
		if (source == butSize4) {
			scaleX = size4;
			scaleY = size4;
		}
		if (source == butSize5) {
			scaleX = size5;
			scaleY = size5;
		}
	repaint();
	}
}
