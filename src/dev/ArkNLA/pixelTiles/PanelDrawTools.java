package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelDrawTools extends JPanel implements ActionListener{
	
	/*
	 * 
	 * 		5/9/2024		*reverted to original working version after color array difficulties*
	 * 		4/25/2023
	 * 		Known Bugs:		- 
	 * 		
	 * 		TODO: 			- Add more tools, perhaps make entire pane scrollable
	 * 							- 
	 * 							- Crop?
	 * 							- 
	 * 								
	 */

	private static final long serialVersionUID = 1L;

	JScrollPane scrollPane;
	JTextArea areaForTips = new JTextArea(5,10);

	// Draw Tools
	String toolInUse = "";
	
	JPanel paneToolButtons = new JPanel();
	JButton butClearImage = new JButton("Clear");

	JButton butMirrorVS = new JButton(new ImageIcon("resources/mvs-button.png"));
	JButton butMirrorHS = new JButton(new ImageIcon("resources/mhs-button.png"));
	JButton butMirror4S = new JButton(new ImageIcon("resources/m4s-button.png"));

	JButton butDuplicateVS = new JButton(new ImageIcon("resources/dvs-button.png"));
	JButton butDuplicateHS = new JButton(new ImageIcon("resources/dhs-button.png"));
	JButton butDuplicate4S = new JButton(new ImageIcon("resources/d4s-button.png"));
	
	ArrayList<String> tips = new ArrayList<String>();
	
	PanelDrawTools() {
		
		paneToolButtons.setLayout(new GridLayout(3,3));
		
		paneToolButtons.add(new JLabel(""));
		
		butClearImage.addActionListener(this);
		paneToolButtons.add(butClearImage);
		
		paneToolButtons.add(new JLabel(""));

		butMirrorVS.addActionListener(this);
		butMirrorHS.addActionListener(this);
		butMirror4S.addActionListener(this);
		paneToolButtons.add(butMirrorVS);
		paneToolButtons.add(butMirrorHS);
		paneToolButtons.add(butMirror4S);

		butDuplicateVS.addActionListener(this);
		butDuplicateHS.addActionListener(this);
		butDuplicate4S.addActionListener(this);
		paneToolButtons.add(butDuplicateVS);
		paneToolButtons.add(butDuplicateHS);
		paneToolButtons.add(butDuplicate4S);

		tips.add("Right-Click on a color in the drawing area to select that color.");
		tips.add("Set custom paint grid to 1 to wash entire image in one color.");
		tips.add("Minecraft blocks are 16x16 pixels drawn/saved.");
		tips.add("Save image to highest paint grid used to avoid detail loss.");
		
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
		
		add(paneToolButtons);
		add(scrollPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		if (source == butClearImage) {
			// Clear image button, sets draw panel boolean to true
			PixelTilesMain.paneDrawTile.clearImage();
			PixelTilesMain.paneDrawTile.repaint();
			PanelTools.panePreview.repaint();
		}
		
		if (source == butMirrorVS) {
			disableConflictingDrawTools("mvs");
			PixelTilesMain.paneDrawTile.setDrawTools("mvs");
			PixelTilesMain.paneDrawTile.repaint();
		}
		if (source == butMirrorHS) {
			disableConflictingDrawTools("mhs");
			PixelTilesMain.paneDrawTile.setDrawTools("mhs");
			PixelTilesMain.paneDrawTile.repaint();
		}
		if (source == butMirror4S) {
			disableConflictingDrawTools("m4s");
			PixelTilesMain.paneDrawTile.setDrawTools("m4s");
			PixelTilesMain.paneDrawTile.repaint();
		}

		if (source == butDuplicateVS) {
			disableConflictingDrawTools("dvs");
			PixelTilesMain.paneDrawTile.setDrawTools("dvs");
			PixelTilesMain.paneDrawTile.repaint();
		}
		if (source == butDuplicateHS) {
			disableConflictingDrawTools("dhs");
			PixelTilesMain.paneDrawTile.setDrawTools("dhs");
			PixelTilesMain.paneDrawTile.repaint();
		}
		if (source == butDuplicate4S) {
			disableConflictingDrawTools("d4s");
			PixelTilesMain.paneDrawTile.setDrawTools("d4s");
			PixelTilesMain.paneDrawTile.repaint();			
		}
	}
	
	private void disableConflictingDrawTools(String toolUsed) {
		switch(toolUsed) {
		case "mvs" : 
			if (toolInUse == "mvs") {
				allToolButtonsEnabled();
			} else {
				toolInUse = "mvs";
				butMirrorVS.setBackground(Color.GREEN);
				butMirrorHS.setEnabled(false);
				butMirror4S.setEnabled(false);
				butDuplicateVS.setEnabled(false);
				butDuplicateHS.setEnabled(false);
				butDuplicate4S.setEnabled(false);
			}
			break;
		case "mhs" : 
			if (toolInUse == "mhs") {
				allToolButtonsEnabled();
			} else {
				toolInUse = "mhs";
				butMirrorVS.setEnabled(false);
				butMirrorHS.setBackground(Color.GREEN);
				butMirror4S.setEnabled(false);
				butDuplicateVS.setEnabled(false);
				butDuplicateHS.setEnabled(false);
				butDuplicate4S.setEnabled(false);
			}
			break;
		case "m4s" : 
			if (toolInUse == "m4s") {
				allToolButtonsEnabled();
			} else {
				toolInUse = "m4s";
				butMirrorVS.setEnabled(false);
				butMirrorHS.setEnabled(false);
				butMirror4S.setBackground(Color.GREEN);
				butDuplicateVS.setEnabled(false);
				butDuplicateHS.setEnabled(false);
				butDuplicate4S.setEnabled(false);
			}
			break;
		case "dvs" : 
			if (toolInUse == "dvs") {
				allToolButtonsEnabled();
			} else {
				toolInUse = "dvs";
				butMirrorVS.setEnabled(false);
				butMirrorHS.setEnabled(false);
				butMirror4S.setEnabled(false);
				butDuplicateVS.setBackground(Color.GREEN);
				butDuplicateHS.setEnabled(false);
				butDuplicate4S.setEnabled(false);
			}
			break;
		case "dhs" : 
			if (toolInUse == "dhs") {
				allToolButtonsEnabled();
			} else {
				toolInUse = "dhs";
				butMirrorVS.setEnabled(false);
				butMirrorHS.setEnabled(false);
				butMirror4S.setEnabled(false);
				butDuplicateVS.setEnabled(false);
				butDuplicateHS.setBackground(Color.GREEN);
				butDuplicate4S.setEnabled(false);
			}
			break;
		case "d4s" : 
			if (toolInUse == "d4s") {
				allToolButtonsEnabled();
			} else {
				toolInUse = "d4s";
				butMirrorVS.setEnabled(false);
				butMirrorHS.setEnabled(false);
				butMirror4S.setEnabled(false);
				butDuplicateVS.setEnabled(false);
				butDuplicateHS.setEnabled(false);
				butDuplicate4S.setBackground(Color.GREEN);
			}
			break;
		}
	}
	
	private void allToolButtonsEnabled() {
		butMirrorVS.setEnabled(true);
		butMirrorHS.setEnabled(true);
		butMirror4S.setEnabled(true);
		butDuplicateVS.setEnabled(true);
		butDuplicateHS.setEnabled(true);
		butDuplicate4S.setEnabled(true);

		butMirrorVS.setBackground(Color.LIGHT_GRAY);
		butMirrorHS.setBackground(Color.LIGHT_GRAY);
		butMirror4S.setBackground(Color.LIGHT_GRAY);
		butDuplicateVS.setBackground(Color.LIGHT_GRAY);
		butDuplicateHS.setBackground(Color.LIGHT_GRAY);
		butDuplicate4S.setBackground(Color.LIGHT_GRAY);

	}
}
