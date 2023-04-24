package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanelTools extends JPanel{

	static PanelPreview panePreview = new PanelPreview();
	PanelDrawTools paneDrawTools = new PanelDrawTools();
	PanelSave paneSave = new PanelSave();
	
	PanelTools() {
		
		setLayout(new BorderLayout());			
		panePreview.setPreferredSize(new Dimension(250,305));
		
		add(panePreview, BorderLayout.NORTH);
		
		// Possible feature with mirror splits, clear image, etc.
		
		add(paneDrawTools, BorderLayout.CENTER);
		
		// paneTools.add(paneDrawTools, BorderLayout.CENTER);
		
		add(paneSave, BorderLayout.SOUTH);
	}
}
