package dev.ArkNLA.pixelTiles;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class PanelSizeSelect extends JPanel implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	JLabel labelGrid1, labelGrid2, labelGrid3, labelGridCustom, labelCustomSize;
	JTextField textFieldCustom;
	JRadioButton radioGrid1, radioGrid2, radioGrid3, radioGridCustom;
	ButtonGroup radioGroup = new ButtonGroup();
	
	private int grid;
	
	int size1value = 10;
	int size2value = 25;
	int size3value = 50;
	
	/*
	 * 		4/4/2023
	 * 		Known Bugs:		none.
	 * 		
	 * 		TODO: 			none.
	 * 								
	 */
	
	PanelSizeSelect() {
		
		labelGrid1 = new JLabel(size1value + "x" + size1value);
		labelGrid2 = new JLabel(size2value + "x" + size2value);
		labelGrid3 = new JLabel(size3value + "x" + size3value);
		
		labelGridCustom = new JLabel("Custom:");
		textFieldCustom = new JTextField(3);
		labelCustomSize = new JLabel("");
		textFieldCustom.addKeyListener(this);
		textFieldCustom.setEnabled(false);
		
		radioGrid1 = new JRadioButton();
		radioGrid2 = new JRadioButton();
		radioGrid3 = new JRadioButton();
		radioGridCustom = new JRadioButton();

		radioGrid1.addActionListener(this);
		radioGrid2.addActionListener(this);
		radioGrid3.addActionListener(this);
		radioGridCustom.addActionListener(this);
		
		radioGrid1.setHorizontalAlignment(SwingConstants.RIGHT);
		radioGrid2.setHorizontalAlignment(SwingConstants.RIGHT);
		radioGrid3.setHorizontalAlignment(SwingConstants.RIGHT);
		radioGridCustom.setHorizontalAlignment(SwingConstants.RIGHT);
		
		radioGrid1.setSelected(true);
		grid = size1value;
		PixelTilesMain.userGridSize = grid;
		textFieldCustom.setText(Integer.toString(size1value));		
		labelCustomSize.setText(size1value + "x" +size1value);
		
		//setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(1,10,2,2));
		
		radioGroup.add(radioGrid1);
		radioGroup.add(radioGrid2);
		radioGroup.add(radioGrid3);
		radioGroup.add(radioGridCustom);
		
		add(radioGrid1, 0, 0);
		add(labelGrid1, 0, 1);
		add(radioGrid2, 0, 2);
		add(labelGrid2, 0, 3);
		add(radioGrid3, 0, 4);
		add(labelGrid3, 0, 5);
		add(radioGridCustom, 0, 6);
		add(labelGridCustom, 0, 7);
		add(textFieldCustom, 0, 8);
		add(labelCustomSize, 0, 9);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == radioGrid1) {
			grid = size1value;
			textFieldCustom.setEnabled(false);
			textFieldCustom.setText(Integer.toString(size1value));
			labelCustomSize.setText(grid + "x" + grid);
		}

		if (source == radioGrid2) {
			grid = size2value;
			textFieldCustom.setEnabled(false);
			textFieldCustom.setText(Integer.toString(size2value));
			labelCustomSize.setText(grid + "x" + grid);
		}

		if (source == radioGrid3) {
			grid = size3value;
			textFieldCustom.setEnabled(false);
			textFieldCustom.setText(Integer.toString(size3value));
			labelCustomSize.setText(grid + "x" + grid);
		}

		if (source == radioGridCustom) {
			textFieldCustom.setEnabled(true);
		}

		PixelTilesMain.userGridSize = grid;
    	PixelTilesMain.paneDraw.repaint();

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
		
		if (radioGridCustom.isSelected()) {
			try {
				int size = Integer.parseInt(textFieldCustom.getText());	
			    
			    if (size > 0) {
			    	
			    	if (size < 100) {
			    		grid = size;
			    	} else {
			    		grid = 100;
						textFieldCustom.setText(Integer.toString(grid));
			    	}
			    	
			    	PixelTilesMain.userGridSize = grid;
			    	
			    	PixelTilesMain.paneDraw.repaint();
			    }

			    labelCustomSize.setText(grid + "x" + grid);

			    			    
			} catch(Exception ex) {
				if (!(grid > 0)) {
					grid = 1;
				}
			}
		    
		}
	}  

	public int getGrid() {
		return grid;
	}
}
