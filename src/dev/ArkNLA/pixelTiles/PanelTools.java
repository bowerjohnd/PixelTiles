package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PanelTools extends JPanel{

	static PanelPreview panePreview = new PanelPreview();
	JScrollPane scrollDrawTools;
	PanelDrawTools paneDrawTools = new PanelDrawTools();
	PanelSave paneSave = new PanelSave();
	
	PanelTools() {
		
		setLayout(new BorderLayout());			
		panePreview.setPreferredSize(new Dimension(250,305));
		
		add(panePreview, BorderLayout.NORTH);
		
		// Possible feature with mirror splits, clear image, etc.
		scrollDrawTools = new JScrollPane(paneDrawTools, 
				   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollDrawTools, BorderLayout.CENTER);
		
		// paneTools.add(paneDrawTools, BorderLayout.CENTER);
		
		add(paneSave, BorderLayout.SOUTH);
	}
}
