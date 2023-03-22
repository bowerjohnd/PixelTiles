package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class PanelSizeSelect extends JPanel implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	JLabel labelSize1, labelSize2, labelSize3, labelSize4, labelSizeCustom;
	JTextField textFieldCustomWidth, textFieldCustomHeight;
	JRadioButton radioSize1, radioSize2, radioSize3, radioSize4, radioSizeCustom;
	ButtonGroup radioGroup = new ButtonGroup();
	
	private int sizeX, sizeY;
	
	int size1value = 8;
	int size2value = 24;
	int size3value = 50;
	int size4value = 250;
	
	PanelSizeSelect() {
		
		//setBackground(Color.LIGHT_GRAY);
		
		labelSize1 = new JLabel(size1value + "x" + size1value);
		labelSize2 = new JLabel(size2value + "x" + size2value);
		labelSize3 = new JLabel(size3value + "x" + size3value);
		labelSize4 = new JLabel(size4value + "x" + size4value);
		
		labelSizeCustom = new JLabel("Custom:");
		textFieldCustomWidth = new JTextField(3);
		textFieldCustomHeight = new JTextField(3);
		textFieldCustomWidth.setEnabled(false);
		textFieldCustomHeight.setEnabled(false);	

		
		radioSize1 = new JRadioButton();
		radioSize2 = new JRadioButton();
		radioSize3 = new JRadioButton();
		radioSize4 = new JRadioButton();
		radioSizeCustom = new JRadioButton();

		radioSize1.addActionListener(this);
		radioSize2.addActionListener(this);
		radioSize3.addActionListener(this);
		radioSize4.addActionListener(this);
		radioSizeCustom.addActionListener(this);
		
		radioSize1.setHorizontalAlignment(SwingConstants.RIGHT);
		radioSize2.setHorizontalAlignment(SwingConstants.RIGHT);
		radioSize3.setHorizontalAlignment(SwingConstants.RIGHT);
		radioSize4.setHorizontalAlignment(SwingConstants.RIGHT);
		radioSizeCustom.setHorizontalAlignment(SwingConstants.RIGHT);
		
		radioSize3.setSelected(true);
		sizeX = size3value;
		sizeY = size3value;
		textFieldCustomWidth.setText(Integer.toString(size3value));
		textFieldCustomHeight.setText(Integer.toString(size3value));
		
		setLayout(new GridLayout(1,12));
		
		radioGroup.add(radioSize1);
		radioGroup.add(radioSize2);
		radioGroup.add(radioSize3);
		radioGroup.add(radioSize4);
		radioGroup.add(radioSizeCustom);
		
		add(radioSize1);
		add(labelSize1);
		add(radioSize2);
		add(labelSize2);
		add(radioSize3);
		add(labelSize3);
		add(radioSize4);
		add(labelSize4);
		add(radioSizeCustom);
		add(labelSizeCustom);
		add(textFieldCustomWidth);
		add(textFieldCustomHeight);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == radioSize1) {
			sizeX = size1value;
			sizeY = size1value;
			textFieldCustomWidth.setEnabled(false);
			textFieldCustomHeight.setEnabled(false);	
			textFieldCustomWidth.setText(Integer.toString(size1value));
			textFieldCustomHeight.setText(Integer.toString(size1value));	
		}

		if (source == radioSize2) {
			sizeX = size2value;
			sizeY = size2value;
			textFieldCustomWidth.setEnabled(false);
			textFieldCustomHeight.setEnabled(false);	
			textFieldCustomWidth.setText(Integer.toString(size2value));
			textFieldCustomHeight.setText(Integer.toString(size2value));	
		}

		if (source == radioSize3) {
			sizeX = size3value;
			sizeY = size3value;
			textFieldCustomWidth.setEnabled(false);
			textFieldCustomHeight.setEnabled(false);	
			textFieldCustomWidth.setText(Integer.toString(size3value));
			textFieldCustomHeight.setText(Integer.toString(size3value));	
		}

		if (source == radioSize4) {
			sizeX = size4value;
			sizeY = size4value;
			textFieldCustomWidth.setEnabled(false);
			textFieldCustomHeight.setEnabled(false);	
			textFieldCustomWidth.setText(Integer.toString(size4value));
			textFieldCustomHeight.setText(Integer.toString(size4value));	
		}

		if (source == radioSizeCustom) {
			textFieldCustomWidth.setEnabled(true);
			textFieldCustomHeight.setEnabled(true);	
		}

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
		
		if (radioSizeCustom.isSelected()) {
			try {
				int w = Integer.parseInt(textFieldCustomWidth.getText());	
			    int h = Integer.parseInt(textFieldCustomHeight.getText());
			    
			    if (w > 1) {
			    	sizeX = w;
			    }
			    
			    if (h > 1) {
			    	sizeY = h;
			    }
			    
			} catch(Exception ex) {
				if (!(sizeX > 1)) {
					sizeX = 1;
				}
				if (!(sizeY > 1)) {
					sizeY = 1;
				}
			}
		    
		}
	}  

	public int getSizeX() {
		return sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
}
