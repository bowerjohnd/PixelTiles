package dev.ArkNLA.pixelTiles;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class PanelSizeSelect extends JPanel implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	JLabel labelGrid1, labelGrid2, labelGrid3, labelGridCustom, labelRows, labelCols;
	JTextField textFieldCustomRows, textFieldCustomCols;
	JRadioButton radioGrid1, radioGrid2, radioGrid3, radioGridCustom;
	ButtonGroup radioGroup = new ButtonGroup();
	
	private int rows, cols;
	
	int size1value = 10;
	int size2value = 25;
	int size3value = 50;
	
	PanelSizeSelect() {
		
		labelGrid1 = new JLabel(size1value + "x" + size1value);
		labelGrid2 = new JLabel(size2value + "x" + size2value);
		labelGrid3 = new JLabel(size3value + "x" + size3value);
		
		labelGridCustom = new JLabel("Custom:");
		textFieldCustomRows = new JTextField(3);
		textFieldCustomCols = new JTextField(3);
		textFieldCustomRows.addKeyListener(this);
		textFieldCustomCols.addKeyListener(this);
		textFieldCustomRows.setEnabled(false);
		textFieldCustomCols.setEnabled(false);	
		labelRows = new JLabel("Rows:");
		labelCols = new JLabel("Cols:");
		labelRows.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCols.setHorizontalAlignment(SwingConstants.RIGHT);
		
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
		rows = size1value;
		cols = size1value;
		PixelTilesMain.userGridLineRows = rows;
		PixelTilesMain.userGridLineCols = cols;
		textFieldCustomRows.setText(Integer.toString(size1value));
		textFieldCustomCols.setText(Integer.toString(size1value));
		
		
		//setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(1,12,2,2));
		
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
		add(labelRows, 0, 8);
		add(textFieldCustomRows, 0, 9);
		add(labelCols, 0, 10);
		add(textFieldCustomCols, 0, 11);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == radioGrid1) {
			rows = size1value;
			cols = size1value;
			textFieldCustomRows.setEnabled(false);
			textFieldCustomCols.setEnabled(false);	
			textFieldCustomRows.setText(Integer.toString(size1value));
			textFieldCustomCols.setText(Integer.toString(size1value));	
		}

		if (source == radioGrid2) {
			rows = size2value;
			cols = size2value;
			textFieldCustomRows.setEnabled(false);
			textFieldCustomCols.setEnabled(false);	
			textFieldCustomRows.setText(Integer.toString(size2value));
			textFieldCustomCols.setText(Integer.toString(size2value));	
		}

		if (source == radioGrid3) {
			rows = size3value;
			cols = size3value;
			textFieldCustomRows.setEnabled(false);
			textFieldCustomCols.setEnabled(false);	
			textFieldCustomRows.setText(Integer.toString(size3value));
			textFieldCustomCols.setText(Integer.toString(size3value));	
		}

		if (source == radioGridCustom) {
			textFieldCustomRows.setEnabled(true);
			textFieldCustomCols.setEnabled(true);	
		}

		PixelTilesMain.userGridLineRows = rows;
		PixelTilesMain.userGridLineCols = cols;
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
				int w = Integer.parseInt(textFieldCustomRows.getText());	
			    int h = Integer.parseInt(textFieldCustomCols.getText());
			    
			    if (w > 0) {
			    	
			    	if (w < 100) {
			    		rows = w;
			    	} else {
			    		rows = 100;
						textFieldCustomRows.setText(Integer.toString(rows));
			    	}
			    	
			    	PixelTilesMain.userGridLineRows = rows;
			    	PixelTilesMain.paneDraw.repaint();
			    }
			    
			    if (h > 0) {
			    	
			    	if (h < 100) {
			    		cols = h;
			    	} else {
			    		cols = 100;
						textFieldCustomCols.setText(Integer.toString(cols));	
			    	}

			    	PixelTilesMain.userGridLineCols = cols;
			    	PixelTilesMain.paneDraw.repaint();
			    }
			    			    
			} catch(Exception ex) {
				if (!(rows > 0)) {
					rows = 1;
				}
				if (!(cols > 0)) {
					cols = 1;
				}
			}
		    
		}
	}  

	public int getGridRows() {
		return rows;
	}
	public int getGridCols() {
		return cols;
	}
}
