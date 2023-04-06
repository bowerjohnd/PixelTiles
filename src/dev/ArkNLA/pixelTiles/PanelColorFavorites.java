package dev.ArkNLA.pixelTiles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class PanelColorFavorites extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> arrayColorString = new ArrayList<String>();
	private PanelPaintColorFavorites panePaint;
	private JLabel labelStatus = new JLabel("Status");
	private ArrayList<JButton> butUse = new ArrayList<JButton>();
	private ArrayList<JButton> butDelete = new ArrayList<JButton>();
	
	PanelColorFavorites() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(labelStatus);
		
		loadColorFavoritesFromFile();
		
		if (!arrayColorString.isEmpty()) {
			populateJPanels();
		}
		
		setVisible(true);
		
	}
	
	public void addFavorite(String fav) {
		arrayColorString.add(fav);
		saveColorFavoritesToFile();
		populateJPanels();
		
		revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		for(int i = 0; i < butUse.size(); i++) {
			if (source == butUse.get(i)) {
					
				String[] split = arrayColorString.get(i).split(", ");
					
				int r = 0;
				int g = 0;
				int b = 0;
				int o = 0;
				
				try {
					r = Integer.parseInt(split[0]);
					g = Integer.parseInt(split[1]);
					b = Integer.parseInt(split[2]);
					o = Integer.parseInt(split[3]);
				} catch (Exception ex){
					labelStatus.setText("ACTION: Error converting rgbo to int.");
				}
					
				PixelTilesMain.userColor = new Color(r,g,b,o);
				PixelTilesMain.paneColorSelect.setColorToFavorite();

			}
		}

		for(int i = 0; i < butDelete.size(); i++) {
			if (source == butDelete.get(i)) {
			
				arrayColorString.remove(i);
				butUse.remove(i);
				butDelete.remove(i);
				saveColorFavoritesToFile();
				
				populateJPanels();
				
				revalidate();
			}
		}
	}
	
	private void populateJPanels() {
		
		removeAll();
		
		for(int i = 0; i < arrayColorString.size(); i++) {
			String[] split = arrayColorString.get(i).split(", ");
			
			int r = 0;
			int g = 0;
			int b = 0;
			int o = 0;
			
			try {
				r = Integer.parseInt(split[0]);
				g = Integer.parseInt(split[1]);
				b = Integer.parseInt(split[2]);
				o = Integer.parseInt(split[3]);
			} catch (Exception ex){
				labelStatus.setText("POPULATE: Error converting rgbo to int.");
			}
			
			JPanel jp = new JPanel();
			//jp.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			panePaint = new PanelPaintColorFavorites(r,g,b,o);
			panePaint.setPreferredSize(new Dimension(50, 50));
			
			butUse.add(new JButton("Use"));
			butUse.get(i).addActionListener(this);
			
			butDelete.add(new JButton("Delete"));
			butDelete.get(i).addActionListener(this);
			
			jp.add(panePaint);
			jp.add(butUse.get(i));
			jp.add(butDelete.get(i));
			
			add(jp);
		
		}

		repaint();
	}
	
	private void loadColorFavoritesFromFile() {

		try {
			FileInputStream fis = new FileInputStream("PixelTilesFavColors.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			arrayColorString = (ArrayList<String>) ois.readObject();
			ois.close();
			labelStatus.setText("Loaded Success.");
		} catch (FileNotFoundException fnf) {
			
			labelStatus.setText("Save your favorite colors here.");

		} catch (Exception ex) {

			labelStatus.setText("LOAD FILE: Error loading favorites from file.");

		}
	}
	
	private void saveColorFavoritesToFile() {
		
		try {
			FileOutputStream fos = new FileOutputStream("PixelTilesFavColors.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(arrayColorString);
			oos.close();
			labelStatus.setText("Save Success.");
		} catch (Exception ex) {
			labelStatus.setText("SAVE FILE: Error saving favorites to file.");
		}
	}


}
