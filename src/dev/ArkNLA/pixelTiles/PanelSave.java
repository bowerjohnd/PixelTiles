package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelSave extends JPanel implements ActionListener, KeyListener{

	/*
	 * 		4/24/2023
	 * 		Known Bugs:		
	 * 		
	 * 		TODO: 			- Use PixelTIlesMain.userImageColorArray to draw
	 * 							a fresh image to be saved.
	 * 								
	 */
	
	private JLabel statusMessage = new JLabel("Save Image:");
	private JButton buttonSaveAs = new JButton("Save As");
	private BufferedImage userImage;
	
	private int imageSize = PixelTilesMain.userGridSize;	
	private JLabel labelImageSize;
	private JTextField textImageSize = new JTextField(3);
	
	private File workingDirectory;
	String stringSelectedFile = "";
	
	private JPanel paneSaveImage = new JPanel();
	
	PanelSave() {
		
		buttonSaveAs.addActionListener(this);
		
		labelImageSize = new JLabel("Image Size: ");
		labelImageSize.setHorizontalAlignment(SwingConstants.LEFT);
		textImageSize.addKeyListener(this);
		textImageSize.setHorizontalAlignment(SwingConstants.RIGHT);
		textImageSize.setText(String.valueOf(imageSize));
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		paneSaveImage.setSize(125,100);
		paneSaveImage.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridy = 0;
		c.gridx = 0;
		paneSaveImage.add(labelImageSize, c);
		c.gridy = 1;
		c.gridx = 0;
		paneSaveImage.add(textImageSize, c);
		c.gridy = 2;
		c.gridx = 0;
		paneSaveImage.add(buttonSaveAs, c);
		
		add(statusMessage, BorderLayout.NORTH);
		add(paneSaveImage, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == buttonSaveAs) {
			
			showSaveAsDialog();
		}
	}
	
	private void showSaveAsDialog() {

		// If images directory doesn't exist, create it
		if (workingDirectory == null) {
			workingDirectory = new File("images/");
			if (!workingDirectory.exists()){
			    workingDirectory.mkdirs();
			}
		}
		
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(workingDirectory);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
 
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Image", "png"));
 
        fileChooser.setAcceptAllFileFilterUsed(true);
 
        int result = fileChooser.showSaveDialog(this);
 
        if (result == JFileChooser.APPROVE_OPTION) {
        	workingDirectory = fileChooser.getCurrentDirectory();
            File selectedFile = fileChooser.getSelectedFile();
            stringSelectedFile = selectedFile.getAbsolutePath();
            
            saveImage();
        }
	}
	
	private void saveImage() {
		
		try {
			
			userImage = (BufferedImage) PixelTilesMain.userImage;

			userImage = (BufferedImage) getScaledImage(userImage, imageSize, imageSize);
			
			if (stringSelectedFile.length() > 0) {
				
				
				// If file name has already been used, append number to it.
				File img = new File(stringSelectedFile + ".png");
				if (img.exists()) {
					int i = 1;
					while (img.exists()){
						img = new File(stringSelectedFile + "(" + i + ").png");
						i++;
					}
					
					stringSelectedFile = stringSelectedFile + "(" + (i-1) + ")";
				}
				
				try {
					ImageIO.write(userImage, "PNG", new File(stringSelectedFile + ".png"));
					statusMessage.setText("Image has been saved.");
					stringSelectedFile = "";
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
		
		if (source == textImageSize) {
			try {
				imageSize = Integer.parseInt(textImageSize.getText());
				labelImageSize.setText("Image Size: " + imageSize + "x" + imageSize);
			}
			catch (Exception ex) {
				textImageSize.setText(String.valueOf(imageSize));
				labelImageSize.setText("Image Size: " + imageSize + "x" +imageSize);
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
