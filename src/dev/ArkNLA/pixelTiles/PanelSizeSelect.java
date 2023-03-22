package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class PanelSizeSelect extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	JLabel labelSize1, labelSize2, labelSize3, labelSize4, labelSizeCustom;
	JTextField textFieldCustomWidth, textFieldCustomHeight;
	JRadioButton radioSize1, radioSize2, radioSize3, radioSize4, radioSizeCustom;
	
	PanelSizeSelect() {
		
		setBackground(Color.LIGHT_GRAY);
		
		labelSize1 = new JLabel("8x8");
		labelSize2 = new JLabel("24x24");
		labelSize3 = new JLabel("50x50");
		labelSize4 = new JLabel("250x250");
		
		labelSizeCustom = new JLabel("Custom:");
		textFieldCustomWidth = new JTextField(3);
		textFieldCustomHeight = new JTextField(3);
		
		setLayout(new GridLayout(12,1));
		
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

}
