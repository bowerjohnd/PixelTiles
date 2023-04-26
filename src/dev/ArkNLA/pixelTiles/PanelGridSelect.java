package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class PanelGridSelect extends JPanel implements ActionListener, KeyListener{
	
	/*
	 * 		4/24/2023
	 * 		Known Bugs:		- 
	 * 		
	 * 		TODO: 			- 
	 * 								
	 */
	
	private static final long serialVersionUID = 1L;
	
	JLabel labelGridCustom, labelCustomSize;
	JTextField textFieldCustom;
	
	private int grid = 16;
	
	private final String[] options1 = {"3", "9", "27", "54"};
	private final String[] options2 = {"5", "10", "20", "40"};
	private final String[] options3 = {"8", "16", "32", "64"};
	
	JComboBox<String> combo1 = new JComboBox<>(options1);
	JComboBox<String> combo2 = new JComboBox<>(options2);
	JComboBox<String> combo3 = new JComboBox<>(options3);
	
	PanelGridSelect() {

		PixelTilesMain.userGridSize = grid;
		
		labelGridCustom = new JLabel("Custom:");
		labelGridCustom.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldCustom = new JTextField(3);
		textFieldCustom.setText(String.valueOf(grid));
		labelCustomSize = new JLabel(grid + "x" + grid);
		textFieldCustom.addKeyListener(this);
		
		combo1.addActionListener(this);
		combo2.addActionListener(this);
		combo3.addActionListener(this);
		
		

		setLayout(new GridLayout(1,6,10,2));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		add(new JLabel(""), 0, 0);
		add(new JLabel("Paint Grid:"), 0, 1);
		add(new JLabel(""), 0, 2);
		add(combo1, 0, 3);
		add(combo2, 0, 4);
		add(combo3, 0, 5);
		add(new JLabel(""), 0, 6);
		add(labelGridCustom, 0, 7);
		add(textFieldCustom, 0, 8);
		add(labelCustomSize, 0, 9);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source == combo1) {
			grid = Integer.valueOf((String)combo1.getSelectedItem());
			textFieldCustom.setText((String)combo1.getSelectedItem());
			labelCustomSize.setText(grid + "x" + grid);
		}
		
		if (source == combo2) {
			grid = Integer.valueOf((String)combo2.getSelectedItem());
			textFieldCustom.setText((String)combo2.getSelectedItem());
			labelCustomSize.setText(grid + "x" + grid);
		}
		
		if (source == combo3) {
			grid = Integer.valueOf((String)combo3.getSelectedItem());
			textFieldCustom.setText((String)combo3.getSelectedItem());
			labelCustomSize.setText(grid + "x" + grid);
		}

		PixelTilesMain.userGridSize = grid;
    	PixelTilesMain.paneDrawTile.repaint();

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
			    	
		    	PixelTilesMain.paneDrawTile.repaint();
		    }

			    labelCustomSize.setText(grid + "x" + grid);
			    			    
		} catch(Exception ex) {
			if (!(grid > 0)) {
				grid = 9;
			}
		}
		    
	}  

	public int getGrid() {
		return grid;
	}

}
