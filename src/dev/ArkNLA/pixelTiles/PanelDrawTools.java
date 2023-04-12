package dev.ArkNLA.pixelTiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelDrawTools extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	JButton butClearImage;
	
	PanelDrawTools() {
		butClearImage = new JButton("Clear Image");
		butClearImage.addActionListener(this);
		
		add(butClearImage);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		if (source == butClearImage) {
			// Clear image button, sets draw panel boolean to true
			PixelTilesMain.paneDraw.clearImage();
			PixelTilesMain.paneDraw.repaint();
			PixelTilesMain.panePreview.repaint();
		}
	}

	
}
