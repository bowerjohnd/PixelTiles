package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelDrawTools extends JPanel implements ActionListener{
	
	/*
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
	
	JPanel paneOther = new JPanel();
	JButton butClearImage = new JButton("Clear");

	JPanel paneMirror = new JPanel();
	JButton butMirrorVS = new JButton(new ImageIcon("mvs-button.png"));
	JButton butMirrorHS = new JButton(new ImageIcon("mhs-button.png"));
	JButton butMirror4S = new JButton(new ImageIcon("m4s-button.png"));

	JPanel paneDuplicate = new JPanel();
	JButton butDuplicateVS = new JButton(new ImageIcon("dvs-button.png"));
	JButton butDuplicateHS = new JButton(new ImageIcon("dhs-button.png"));
	JButton butDuplicate4S = new JButton(new ImageIcon("d4s-button.png"));
	
	ArrayList<String> tips = new ArrayList<String>();
	
	PanelDrawTools() {
		
		butClearImage.addActionListener(this);
		paneOther.add(butClearImage);
		
		butMirrorVS.addActionListener(this);
		butMirrorHS.addActionListener(this);
		butMirror4S.addActionListener(this);
		paneMirror.add(butMirrorVS);
		paneMirror.add(butMirrorHS);
		paneMirror.add(butMirror4S);

		butDuplicateVS.addActionListener(this);
		butDuplicateHS.addActionListener(this);
		butDuplicate4S.addActionListener(this);
		paneDuplicate.add(butDuplicateVS);
		paneDuplicate.add(butDuplicateHS);
		paneDuplicate.add(butDuplicate4S);

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
		
		add(paneOther);
		add(paneMirror);
		add(paneDuplicate);
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
		
		if (source == butMirrorVS) {
			disableConflictingDrawTools("mvs");
			PixelTilesMain.paneDraw.setDrawTools("mvs");
			PixelTilesMain.paneDraw.repaint();
		}
		if (source == butMirrorHS) {
			disableConflictingDrawTools("mhs");
			PixelTilesMain.paneDraw.setDrawTools("mhs");
			PixelTilesMain.paneDraw.repaint();
		}
		if (source == butMirror4S) {
			disableConflictingDrawTools("m4s");
			PixelTilesMain.paneDraw.setDrawTools("m4s");
			PixelTilesMain.paneDraw.repaint();
		}

		if (source == butDuplicateVS) {
			disableConflictingDrawTools("dvs");
			PixelTilesMain.paneDraw.setDrawTools("dvs");
			PixelTilesMain.paneDraw.repaint();
		}
		if (source == butDuplicateHS) {
			disableConflictingDrawTools("dhs");
			PixelTilesMain.paneDraw.setDrawTools("dhs");
			PixelTilesMain.paneDraw.repaint();
		}
		if (source == butDuplicate4S) {
			disableConflictingDrawTools("d4s");
			PixelTilesMain.paneDraw.setDrawTools("d4s");
			PixelTilesMain.paneDraw.repaint();			
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
