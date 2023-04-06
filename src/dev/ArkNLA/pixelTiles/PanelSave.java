package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
	private final JLabel labelFN = new JLabel("Filename:");
	private JButton buttonLoadImage = new JButton("Load PNG Image");
	private JButton buttonSaveImage = new JButton("Save PNG Image");
	private BufferedImage userImage;
	private int w, h;
	
	private JPanel paneSaveImage = new JPanel();
	
	PanelSave() {
		
		textFilename.addKeyListener(this);
		buttonLoadImage.addActionListener(this);
		buttonSaveImage.addActionListener(this);
		buttonSaveImage.setEnabled(false);
		
		setLayout(new BorderLayout());

		//add(buttonLoadImage, BorderLayout.NORTH);

		paneSaveImage.setSize(125,100);
		paneSaveImage.setLayout(new GridLayout(3,1));
		
		paneSaveImage.add(labelFN);
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
			w = PixelTilesMain.userImage.getWidth(this);
			h = PixelTilesMain.userImage.getHeight(this);
			
			
			
			userImage = (BufferedImage) PixelTilesMain.userImage;
			
			if (textFilename.getText().length() > 0) {
				
				File dir = new File("images/");
				if (!dir.exists()){
				    dir.mkdirs();
				}
				
				try {
					ImageIO.write(userImage, "PNG", new File("images/" + textFilename.getText() + ".PNG"));
					statusMessage.setText("Image " + textFilename.getText() + ".PNG has been saved.");
					textFilename.setText("");
				} catch (Exception ex) {
					statusMessage.setText("File not saved.");
					ex.printStackTrace();
				}
				
			} else {
				
				statusMessage.setText("Please name your image.");
			}
			
		} catch (Exception e) {
			statusMessage.setText("File not saved.");
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

		if (textFilename.getText().length() > 0) {
			buttonSaveImage.setEnabled(true);
		} else {
			buttonSaveImage.setEnabled(false);
		}

		revalidate();
		
	}

}
