package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelSave extends JPanel implements ActionListener, KeyListener{

	private JTextField textFilename = new JTextField(10);
	private JLabel statusMessage = new JLabel("Enter filename to save.");
	private final JLabel labelFilename = new JLabel("Filename:");
	private JButton buttonLoadImage = new JButton("Load PNG Image");
	private JButton buttonSaveImage = new JButton("Save PNG Image");
	private BufferedImage userImage;
	
	private int imageSize = 250;
	
	private JLabel labelImageSize;
	private JTextField textImageSize = new JTextField(10);
	
	private JPanel paneSaveImage = new JPanel();
	
	PanelSave() {
		
		textFilename.addKeyListener(this);
		buttonLoadImage.addActionListener(this);
		buttonSaveImage.addActionListener(this);
		buttonSaveImage.setEnabled(false);
		
		labelImageSize = new JLabel("Image Size: " + imageSize + "x" + imageSize);
		textImageSize.addKeyListener(this);
		textImageSize.setText(String.valueOf(imageSize));
		
		setLayout(new BorderLayout());

		//add(buttonLoadImage, BorderLayout.NORTH);

		paneSaveImage.setSize(125,100);
		paneSaveImage.setLayout(new GridLayout(5,1));
		
		paneSaveImage.add(labelImageSize);
		paneSaveImage.add(textImageSize);
		paneSaveImage.add(labelFilename);
		paneSaveImage.add(textFilename);		
		paneSaveImage.add(buttonSaveImage);
		
		add(statusMessage, BorderLayout.NORTH);
		add(paneSaveImage, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == buttonLoadImage) {
			
			// Feature added later
		
		}
		
		if (source == buttonSaveImage) {
			
			saveImage();
		}
	}
	
	private void saveImage() {
		
		try {
			
			userImage = (BufferedImage) PixelTilesMain.userImage;

			userImage = (BufferedImage) getScaledImage(userImage, imageSize, imageSize);

			
			if (textFilename.getText().length() > 0) {
				
				// If images directory doesn't exist, create it
				File dir = new File("images/");
				if (!dir.exists()){
				    dir.mkdirs();
				}
				
				// If file name has already been used, append number to it.
				File img = new File("images/" + textFilename.getText() + ".png");
				if (img.exists()) {
					int i = 1;
					while (img.exists()){
						img = new File("images/" + textFilename.getText() + "(" + i + ").png");
						i++;
					}
					
					textFilename.setText(textFilename.getText() + "(" + (i-1) + ")");
				}
				
				try {
					ImageIO.write(userImage, "PNG", new File("images/" + textFilename.getText() + ".png"));
					statusMessage.setText("Image " + textFilename.getText() + ".png has been saved.");
					textFilename.setText("");
				} catch (Exception ex) {
					statusMessage.setText("File not saved. File.");
					ex.printStackTrace();
				}
				
			} else {
				
				statusMessage.setText("Please name your image.");
			}
			
		} catch (Exception e) {
			statusMessage.setText("File not saved. saveImage.");
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

		Object source = e.getSource();
		
		if (source == textFilename) {
			if (textFilename.getText().length() > 0) {
				buttonSaveImage.setEnabled(true);
			} else {
				buttonSaveImage.setEnabled(false);
			}
		}
		
		if (source == textImageSize) {
			try {
				imageSize = Integer.parseInt(textImageSize.getText());
				labelImageSize.setText("Image Size: " + imageSize + "x" + imageSize);
			}
			catch (Exception ex) {
				imageSize = 250;
				labelImageSize.setText("Image Size: " + imageSize + "x" + imageSize);
			}
			
		}

		revalidate();
		
	}
	
	/**
	 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
	 * Resizes an image using a Graphics2D object backed by a BufferedImage.
	 * @param srcImg - source image to scale
	 * @param w - desired width
	 * @param h - desired height
	 * @return - the new resized image
	 */
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();        
	    return resizedImg;
	}

}
