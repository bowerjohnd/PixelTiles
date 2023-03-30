package dev.ArkNLA.pixelTiles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class PanelColorSelect extends JPanel implements MouseListener, ActionListener, KeyListener, AdjustmentListener{

	private static final long serialVersionUID = 1L;

	/*
		TODO:
				- Color selected shown on top of pane
				- User clicks given color, selected color changes
				- R, G, B text fields with adjustment slides
				- Brightness adjustment slides
				- Transparency adjustment slide
				- Copy to clipboard RGB / HEX
				- RGB / HEX labels with copy to clipboard buttons
	 */
	
	// THIS generic
	
	private int pY;
	private GridBagLayout gridBag = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	
	// THIS border layout NORTH
	
	private JPanel paneNorth = new JPanel();
	private JPanel paneColorSelected = new JPanel();
	private JLabel labelRed, labelGreen, labelBlue, labelTransp, labelHEX;
	private JTextField textRed, textGreen, textBlue, textTransp, textHEX;
	private JScrollBar scrollRed, scrollGreen, scrollBlue, scrollTransp, scrollDarkness;
	
	// THIS border layout CENTER
	
	private JScrollPane paneCenter = new JScrollPane();
	
	
	
	PanelColorSelect() {
		
		// THIS panel
		pY = this.getHeight();
		setBounds(0,0,200,pY);
		setLayout(new BorderLayout());
		
		// NORTH panel setup
		paneNorth.setLayout(gridBag);
		
		// Color selected view
		paneColorSelected.setBackground(Color.WHITE);
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.ipady = 100;
		paneNorth.add(paneColorSelected, gbc);
		
		gbc.gridwidth = 0; gbc.ipady = 0;
		
		// Red adjustments
		labelRed = new JLabel("R:");
		textRed = new JTextField(3);
		textRed.addKeyListener(this);
		scrollRed = new JScrollBar(JScrollBar.HORIZONTAL,250,1,0,255);
		scrollRed.addAdjustmentListener(this);
		
		gbc.gridy = 1; gbc.gridx = 0;
		paneNorth.add(labelRed, gbc);
		gbc.gridy = 1; gbc.gridx = 1;
		paneNorth.add(textRed, gbc);
		gbc.gridy = 1; gbc.gridx = 2;
		paneNorth.add(scrollRed, gbc);

		// Green adjustments
		labelGreen = new JLabel("G:");
		textGreen = new JTextField(3);
		textGreen.addKeyListener(this);
		scrollGreen = new JScrollBar(JScrollBar.HORIZONTAL,250,1,0,255);
		scrollGreen.addAdjustmentListener(this);
		
		gbc.gridy = 2; gbc.gridx = 0;
		paneNorth.add(labelGreen, gbc);
		gbc.gridy = 2; gbc.gridx = 1;
		paneNorth.add(textGreen, gbc);
		gbc.gridy = 2; gbc.gridx = 2;
		paneNorth.add(scrollGreen, gbc);

		// Blue adjustments
		labelBlue = new JLabel("B:");
		textBlue = new JTextField(3);
		textBlue.addKeyListener(this);
		scrollBlue = new JScrollBar(JScrollBar.HORIZONTAL,250,1,0,255);
		scrollBlue.addAdjustmentListener(this);
		
		gbc.gridy = 3; gbc.gridx = 0;
		paneNorth.add(labelBlue, gbc);
		gbc.gridy = 3; gbc.gridx = 1;
		paneNorth.add(textBlue, gbc);
		gbc.gridy = 3; gbc.gridx = 2;
		paneNorth.add(scrollBlue, gbc);
		
		// THIS panel add other panels
		
		add(paneNorth, BorderLayout.NORTH);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
