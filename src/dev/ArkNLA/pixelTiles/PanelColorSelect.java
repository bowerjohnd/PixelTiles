package dev.ArkNLA.pixelTiles;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelColorSelect extends JPanel implements ActionListener, KeyListener, ChangeListener, MouseListener{

	private static final long serialVersionUID = 1L;

	/*
		TODO:
				- slideAll: get solved code from CPS261 drawing project
				- Brightness adjustment buttons
	 */
	
	// THIS generic
	
	private int pY;
	private int intRed = 0;			//
	private int intGreen = 0;		// Default color: black
	private int intBlue = 0;		//
	private int intOpacity = 255;	//
	private int intHue = 0;
	private int intSaturation = 0;
	private int intBrightness = 0;
	private Color colorSelected = new Color(intRed, intGreen, intBlue, intOpacity);
	
	// THIS border layout NORTH
	
	private JPanel paneNorth = new JPanel();
	private JPanel paneColorSelected = new JPanel();
	private JPanel paneColorSelectedPreview;
	private JPanel paneColorSelectedText = new JPanel();
	private JPanel paneRGB = new JPanel();
	private JPanel panePresetColors = new JPanel();
	private JLabel labelRed, labelGreen, labelBlue, labelHue, labelSaturation, labelBrightness, labelOpacity, labelRGB, labelHEX;
	private JTextField textRed, textGreen, textBlue, textHue, textSaturation, textBrightness, textOpacity, textRGB, textHEX;
	private JSlider slideRed, slideGreen, slideBlue, slideHue, slideSaturation, slideBrightness, slideOpacity;
	private JButton buttonRGBcopy, buttonHEXcopy, buttonSaveFavorite,
					butRed, butGreen, butBlue, butYellow, butOrange, butBlack,
					butGray, butWhite;
	
	// THIS border layout CENTER
	
	private JScrollPane paneCenter = new JScrollPane();
	private PanelColorFavorites paneFavorites = new PanelColorFavorites();

	
	
	PanelColorSelect() {
		
		// THIS panel
		pY = this.getHeight();
		setBounds(0,0,100,pY);
		setLayout(new BorderLayout());


		/*
		 * 		NORTH PANEL begins
		 */
		
		paneNorth.setLayout(new BorderLayout());
		paneNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		paneColorSelected.setLayout(new BorderLayout());
		paneColorSelectedText.setLayout(new GridBagLayout());
		paneRGB.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		/*
		 * 		Color selected pane NORTH
		 */
		
		// Swing doesn't support opacity on backgrounds
		//		- Anomalies occur with only setBackground on pane
		// 		- Get background color and paint a rectangle over it with opacity
		//		* Source: https://tips4java.wordpress.com/2009/05/31/backgrounds-with-transparency/
		
		paneColorSelectedPreview = new JPanel()
		{
		    protected void paintComponent(Graphics g)
		    {
		    	g.setColor(Color.WHITE);
		    	g.fillRect(0, 0, getWidth(), getHeight());
		        g.setColor( getBackground() );
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		
		paneColorSelectedPreview.setPreferredSize(new Dimension(100, 100));
		paneColorSelectedPreview.setLayout(new BorderLayout());
		
		paneColorSelected.add(paneColorSelectedPreview, BorderLayout.NORTH);
		
		labelRGB = new JLabel("RGBA:");
		labelHEX = new JLabel("HEX:");
		textRGB = new JTextField(10);
		textRGB.setEditable(false);
		textHEX = new JTextField(10);
		textHEX.setEditable(false);
		buttonRGBcopy = new JButton("Copy");
		buttonRGBcopy.addActionListener(this);
		buttonHEXcopy = new JButton("Copy");
		buttonHEXcopy.addActionListener(this);
		
		c.gridy = 0;
		c.gridx = 0;
		paneColorSelectedText.add(labelRGB, c);
		c.gridy = 0;
		c.gridx = 1;
		paneColorSelectedText.add(textRGB, c);
		c.gridy = 0;
		c.gridx = 2;
		paneColorSelectedText.add(buttonRGBcopy, c);
		c.gridy = 1;
		c.gridx = 0;
		paneColorSelectedText.add(labelHEX, c);
		c.gridy = 1;
		c.gridx = 1;
		paneColorSelectedText.add(textHEX, c);
		c.gridy = 1;
		c.gridx = 2;
		paneColorSelectedText.add(buttonHEXcopy, c);
		
		paneColorSelected.add(paneColorSelectedText, BorderLayout.SOUTH);
		
		/*
		 * 		Preset color buttons north pane CENTER
		 */
		
		panePresetColors.setLayout(new GridLayout());
		
		butRed = new JButton();
		butGreen = new JButton(); 
		butBlue = new JButton();
		butYellow = new JButton(); 
		butOrange = new JButton(); 
		butBlack = new JButton();
		butGray = new JButton();
		butWhite = new JButton();

		butRed.setPreferredSize(new Dimension(20, 20));
		butGreen.setPreferredSize(new Dimension(20, 20)); 
		butBlue.setPreferredSize(new Dimension(20, 20));
		butYellow.setPreferredSize(new Dimension(20, 20));
		butOrange.setPreferredSize(new Dimension(20, 20));
		butBlack.setPreferredSize(new Dimension(20, 20));
		butGray.setPreferredSize(new Dimension(20, 20));
		butWhite.setPreferredSize(new Dimension(20, 20));

		butRed.addActionListener(this);
		butGreen.addActionListener(this); 
		butBlue.addActionListener(this);
		butYellow.addActionListener(this);
		butOrange.addActionListener(this);
		butBlack.addActionListener(this);
		butGray.addActionListener(this);
		butWhite.addActionListener(this);

		butRed.setBackground(Color.RED);
		butGreen.setBackground(Color.GREEN); 
		butBlue.setBackground(Color.BLUE);
		butYellow.setBackground(Color.YELLOW);
		butOrange.setBackground(Color.ORANGE);
		butBlack.setBackground(Color.BLACK);
		butGray.setBackground(Color.GRAY);
		butWhite.setBackground(Color.WHITE);
		
		panePresetColors.add(butRed);
		panePresetColors.add(butGreen);
		panePresetColors.add(butBlue);
		panePresetColors.add(butYellow);
		panePresetColors.add(butOrange);
		panePresetColors.add(butBlack);
		panePresetColors.add(butGray);
		panePresetColors.add(butWhite);

		/*
		 * 		Color adjustments north pane SOUTH
		 */
		
		// Red adjustments
		labelRed = new JLabel("Red:");
		labelRed.setHorizontalAlignment(SwingConstants.LEFT);
		textRed = new JTextField(3);
		textRed.addKeyListener(this);
		slideRed = new JSlider(0, 255, 1);
		slideRed.addChangeListener(this);
		
		c.gridy = 0;
		c.gridx = 0;
		paneRGB.add(labelRed, c);
		c.gridy = 0;
		c.gridx = 1;
		paneRGB.add(textRed, c);
		c.gridy = 0;
		c.gridx = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		paneRGB.add(slideRed, c);
		
		// Green adjustments
		labelGreen = new JLabel("Green:");
		labelGreen.setHorizontalAlignment(SwingConstants.LEFT);
		textGreen = new JTextField(3);
		textGreen.addKeyListener(this);
		slideGreen = new JSlider(0, 255, 1);
		slideGreen.addChangeListener(this);
		
		c.gridy = 1;
		c.gridx = 0;
		paneRGB.add(labelGreen, c);
		c.gridy = 1;
		c.gridx = 1;
		paneRGB.add(textGreen, c);
		c.gridy = 1;
		c.gridx = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		paneRGB.add(slideGreen, c);
		
		// Blue adjustments
		labelBlue = new JLabel("Blue:");
		labelBlue.setHorizontalAlignment(SwingConstants.LEFT);
		textBlue = new JTextField(3);
		textBlue.addKeyListener(this);
		slideBlue = new JSlider(0, 255, 1);
		slideBlue.addChangeListener(this);
		
		c.gridy = 2;
		c.gridx = 0;
		paneRGB.add(labelBlue, c);
		c.gridy = 2;
		c.gridx = 1;
		paneRGB.add(textBlue, c);
		c.gridy = 2;
		c.gridx = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		paneRGB.add(slideBlue, c);
		
		// Hue adjustment
		labelHue = new JLabel("Hue:");
		labelHue.setHorizontalAlignment(SwingConstants.LEFT);
		textHue = new JTextField(3);
		textHue.addKeyListener(this);
		slideHue = new JSlider(0, 255, 0);
		slideHue.addChangeListener(this);
		
		c.gridy = 3;
		c.gridx = 0;
		paneRGB.add(labelHue, c);
		c.gridy = 3;
		c.gridx = 1;
		paneRGB.add(textHue, c);
		c.gridy = 3;
		c.gridx = 2;
		c.fill = GridBagConstraints.HORIZONTAL;	
		paneRGB.add(slideHue, c);

		// Saturation adjustment
		labelSaturation = new JLabel("Sat.:");
		labelSaturation.setHorizontalAlignment(SwingConstants.LEFT);
		textSaturation = new JTextField(3);
		textSaturation.addKeyListener(this);
		slideSaturation = new JSlider(0, 255, 0);
		slideSaturation.addChangeListener(this);
		
		c.gridy = 4;
		c.gridx = 0;
		paneRGB.add(labelSaturation, c);
		c.gridy = 4;
		c.gridx = 1;
		paneRGB.add(textSaturation, c);
		c.gridy = 4;
		c.gridx = 2;
		c.fill = GridBagConstraints.HORIZONTAL;	
		paneRGB.add(slideSaturation, c);

		// Brightness adjustment
		labelBrightness = new JLabel("Bri.:");
		labelBrightness.setHorizontalAlignment(SwingConstants.LEFT);
		textBrightness = new JTextField(3);
		textBrightness.addKeyListener(this);
		slideBrightness = new JSlider(0, 255, 0);
		slideBrightness.addChangeListener(this);
		
		c.gridy = 5;
		c.gridx = 0;
		paneRGB.add(labelBrightness, c);
		c.gridy = 5;
		c.gridx = 1;
		paneRGB.add(textBrightness, c);
		c.gridy = 5;
		c.gridx = 2;
		c.fill = GridBagConstraints.HORIZONTAL;	
		paneRGB.add(slideBrightness, c);

		
		// Transparency adjustment		
		labelOpacity = new JLabel("O:");
		textOpacity = new JTextField(3);
		textOpacity.addKeyListener(this);
		slideOpacity = new JSlider(0, 255, 1);
		slideOpacity.addChangeListener(this);
		
		// Save to favorites button
		buttonSaveFavorite = new JButton("Save");
		buttonSaveFavorite.addActionListener(this);
		
		c.gridy = 6;
		c.gridx = 0;
		paneRGB.add(labelOpacity, c);
		c.gridy = 6;
		c.gridx = 1;
		paneRGB.add(textOpacity, c);
		c.gridy = 6;
		c.gridx = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		paneRGB.add(slideOpacity, c);
		c.gridy = 7;
		c.gridx = 2;
		paneRGB.add(buttonSaveFavorite, c);
		
		// Add panels to paneNORTH 
		
		paneNorth.add(paneColorSelected, BorderLayout.NORTH);
		paneNorth.add(panePresetColors, BorderLayout.CENTER);
		paneNorth.add(paneRGB, BorderLayout.SOUTH);
		
		// THIS add north panel
		
		add(paneNorth, BorderLayout.NORTH);
		
		/*
		 * 		NORTH PANEL ends
		 * 
		 * 		CENTER PANEL begins
		 */
		paneCenter.getViewport().add(paneFavorites);
		
		add(paneCenter,BorderLayout.CENTER);
		
		/*
		 * 		CENTER PANEL ends
		 * 
		 * 		WRAP UP
		 */
		
		slideRed.setValue(intRed);
		slideGreen.setValue(intGreen);
		slideBlue.setValue(intBlue);
		slideOpacity.setValue(intOpacity);
		slideHue.setValue(intHue);
		slideSaturation.setValue(intSaturation);
		slideBrightness.setValue(intBrightness);
		
		textRed.setText(String.valueOf(intRed));
		textGreen.setText(String.valueOf(intGreen));
		textBlue.setText(String.valueOf(intBlue));
		textOpacity.setText(String.valueOf(intOpacity));
		textHue.setText(String.valueOf(intHue));
		textSaturation.setText(String.valueOf(intSaturation));
		textBrightness.setText(String.valueOf(intBrightness));
		
		setSlidersAndTexts();
		setDrawColor();
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
		
		Object source = e.getSource();
		
		/*
		 * 		RGB and O manual input
		 * 			- adjust textRGB, textHEX, sliderRed
		 */
		
		if (source == textRed) {
			try {
				int red = Integer.parseInt(textRed.getText());	
			    
				if (red >= 0 && red <= 255) {
					intRed = red;
				}
			    			    
			} catch(Exception ex) {
				if (!(intRed >= 0 && intRed <= 255)) {
					intRed = 0;
					textRed.setText("0");
				}
			}
		}

		if (source == textGreen) {
			try {
				int green = Integer.parseInt(textGreen.getText());	
			    
				if (green >= 0 && green <= 255) {
					intGreen = green;
				}
			    			    
			} catch(Exception ex) {
				if (!(intGreen >= 0 && intGreen <= 255)) {
					intGreen = 0;
					textGreen.setText("0");
				}
			}
		}

		if (source == textBlue) {
			try {
				int blue = Integer.parseInt(textBlue.getText());	
			    
				if (blue >= 0 && blue <= 255) {
					intBlue = blue;
				}
			    			    
			} catch(Exception ex) {
				if (!(intBlue >= 0 && intBlue <= 255)) {
					intBlue = 0;
					textBlue.setText("0");
				}
			}
		}

		if (source == textOpacity) {
			try {
				int opacity = Integer.parseInt(textOpacity.getText());	
			    
				if (opacity >= 0 && opacity <= 255) {
					intOpacity = opacity;
				}
			    			    
			} catch(Exception ex) {
				if (!(intOpacity >= 0 && intOpacity <= 255)) {
					intOpacity = 255;
					textOpacity.setText("255");
				}
			}
		}

		setSlidersAndTexts();
		setDrawColor();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		/*
		 * 		NORTH PANEL begin
		 */
		
		// Copy color selected to system clipboard

		if (source == buttonRGBcopy) {
			StringSelection selection = new StringSelection(textRGB.getText());
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
		}
		
		if (source == buttonHEXcopy) {
			StringSelection selection = new StringSelection(textHEX.getText());
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
		}
		
		// User selected preset color
		
		if (source == butRed) {
			intRed = Color.red.getRed();
			intGreen = Color.red.getGreen();
			intBlue = Color.red.getBlue();
			
			setSlidersAndTexts();
			setDrawColor();
		}
		if (source == butGreen) {
			intRed = Color.green.getRed();
			intGreen = Color.green.getGreen();
			intBlue = Color.green.getBlue();
			
			setSlidersAndTexts();
			setDrawColor();
		}
		if (source == butBlue) {
			intRed = Color.blue.getRed();
			intGreen = Color.blue.getGreen();
			intBlue = Color.blue.getBlue();
			
			setSlidersAndTexts();
			setDrawColor();
		}
		if (source == butYellow) {
			intRed = Color.yellow.getRed();
			intGreen = Color.yellow.getGreen();
			intBlue = Color.yellow.getBlue();
			
			setSlidersAndTexts();
			setDrawColor();
		}
		if (source == butOrange) {
			intRed = Color.orange.getRed();
			intGreen = Color.orange.getGreen();
			intBlue = Color.orange.getBlue();
			
			setSlidersAndTexts();
			setDrawColor();
		}
		if (source == butBlack) {
			intRed = Color.black.getRed();
			intGreen = Color.black.getGreen();
			intBlue = Color.black.getBlue();
			
			setSlidersAndTexts();
			setDrawColor();
		}
		if (source == butGray) {
			intRed = Color.gray.getRed();
			intGreen = Color.gray.getGreen();
			intBlue = Color.gray.getBlue();
			
			setSlidersAndTexts();
			setDrawColor();
		}
		if (source == butWhite) {
			intRed = Color.white.getRed();
			intGreen = Color.white.getGreen();
			intBlue = Color.white.getBlue();
			
			setSlidersAndTexts();
			setDrawColor();
		}
		
		if (source == buttonSaveFavorite) {
			paneFavorites.addFavorite(intRed + ", " + intGreen + ", " + intBlue + ", " + intOpacity);
			paneFavorites.repaint();
		}
		
		/*
		 * 		NORTH PANEL end
		 * 
		 * 		
		 */
		
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		Object source = e.getSource();
		
		// Color adjustment sliders
		
		if (source == slideRed) {
			
			int v = slideRed.getValue();
			
			intRed = v;			
			textRed.setText(String.valueOf(v));

		}
		
		if (source == slideGreen) {
		
			int v = slideGreen.getValue();
			
			intGreen = v;			
			textGreen.setText(String.valueOf(v));

		}

		if (source == slideBlue) {
			
			int v = slideBlue.getValue();
			
			intBlue = v;			
			textBlue.setText(String.valueOf(v));
			
		}

		if (source == slideOpacity) {
			
			int v = slideOpacity.getValue();
			
			intOpacity = v;			
			textOpacity.setText(String.valueOf(v));
			
		}

		if (source == slideHue) {
			
			int v = slideHue.getValue();
			
			intHue = v;				
			textHue.setText(String.valueOf(v));
			
		}
		
		if (source == slideSaturation) {
			
			int v = slideSaturation.getValue();
			
			intSaturation = v;			
			textSaturation.setText(String.valueOf(v));
			
		}
		
		if (source == slideBrightness) {
			
			int v = slideBrightness.getValue();
			
			intBrightness = v;			
			textBrightness.setText(String.valueOf(v));
			
		}
		
	    int rgb = Color.HSBtoRGB(intHue, intSaturation, intBrightness);
	    int inRed = (rgb >> 16) & 0xFF;
	    int inGreen = (rgb >> 8) & 0xFF;
	    int inBlue = rgb & 0xFF;

	    System.out.println("|" + inRed + "|" + inGreen + "|" + inBlue + "|");
	    
		setSlidersAndTexts();
		setDrawColor();
	}
	
	private void setDrawColor() {
		paneColorSelectedPreview.setBackground(new Color(intRed, intGreen, intBlue, intOpacity));
		colorSelected = new Color(intRed, intGreen, intBlue, intOpacity);
		PixelTilesMain.userColor = colorSelected;
	}
	
	private void setSlidersAndTexts() {
		
		float[] temp = new float[3];
		Color.RGBtoHSB(intRed, intGreen, intBlue, temp);
		intHue = (int)(temp[0]*255);
		intSaturation = (int)(temp[1]*255);
		intBrightness = (int)(temp[2]*255);
		
		slideRed.setValue(intRed);
		slideGreen.setValue(intGreen);
		slideBlue.setValue(intBlue);
		
		slideHue.setValue(intHue);
		slideSaturation.setValue(intSaturation);
		slideBrightness.setValue(intBrightness);
		
		textRed.setText(String.valueOf(intRed));
		textGreen.setText(String.valueOf(intGreen));
		textBlue.setText(String.valueOf(intBlue));
		
		textHue.setText(String.valueOf(intHue));
		textSaturation.setText(String.valueOf(intSaturation));
		textBrightness.setText(String.valueOf(intBrightness));
		
		textRGB.setText(intRed + ", " + intGreen + ", " + intBlue + ", " + intOpacity);
		textHEX.setText("#" + Integer.toHexString(intRed) + Integer.toHexString(intGreen) 
							+ Integer.toHexString(intBlue) + Integer.toHexString(intOpacity));
		}
	
	public void setColorToFavorite() {
		intRed = PixelTilesMain.userColor.getRed();
		intGreen = PixelTilesMain.userColor.getGreen();
		intBlue = PixelTilesMain.userColor.getBlue();
		intOpacity = PixelTilesMain.userColor.getAlpha();
		
		setSlidersAndTexts();
		setDrawColor();		
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
	
	public void setColorFromOutside(int r, int g, int b, int a) {
		intRed = r;
		intGreen = g;
		intBlue = b;
		intOpacity = a;
		
		setDrawColor();
		setSlidersAndTexts();
	}
}