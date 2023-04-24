package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelDrawTools extends JPanel implements ActionListener{
	
	/*
	 * 		4/24/2023
	 * 		Known Bugs:		- 
	 * 		
	 * 		TODO: 			- Add more tools, perhaps make entire pane scrollable
	 * 								
	 */

	private static final long serialVersionUID = 1L;

	JButton butClearImage;
	JScrollPane scrollPane;
	JTextArea areaForTips = new JTextArea(5,10);
	
	ArrayList<String> tips = new ArrayList<String>();
	
	PanelDrawTools() {
		
		butClearImage = new JButton("Clear Image");
		butClearImage.addActionListener(this);

		tips.add("Right-Click on a color in the drawing area to select that color.");
		tips.add("Set custom paint grid to 1 to wash entire image in one color.");
		tips.add("Minecraft blocks are 16x16 pixels drawn/saved.");
		
		areaForTips.setMargin(new Insets(4,4,4,4));
		areaForTips.setWrapStyleWord(true);
		areaForTips.setLineWrap(true);
		areaForTips.setEditable(false);
		areaForTips.setFont(new Font("Serif", Font.BOLD, 16));
		areaForTips.setText("TIPS:\n-----\n");
		
		scrollPane = new JScrollPane(areaForTips);
		
		for (int i = 0; i < tips.size(); i++) {
			String temp = areaForTips.getText();
			temp += tips.get(i) + "\n\n";
			areaForTips.setText(temp);
			
		}
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		add(butClearImage);
		add(scrollPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		if (source == butClearImage) {
			// Clear image button, sets draw panel boolean to true
			PixelTilesMain.paneDraw.clearImage();
			PixelTilesMain.paneDraw.repaint();
			PanelTools.panePreview.repaint();
		}
	}

	
}
