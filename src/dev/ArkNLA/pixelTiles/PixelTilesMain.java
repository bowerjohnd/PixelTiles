package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PixelTilesMain implements ActionListener {
	
	/*
	 * 		4/4/2023
	 * 		Known Bugs:		- see PanelDraw
	 * 		
	 * 		TODO: 			- fix bugs
	 * 						- see PanelDraw
	 * 						- see PanelPreview
	 * 						- add clearing of image
	 * 						* Start on saving favorite colors
	 * 						* Start on saving image to file
	 * 						- Considering expanding frame to tabs to paint saved images to grid/image
	 * 
	 */
	
	// Screen properties
	
	private final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	// Frame Location and size
	
	private int frameX = screenWidth/5;
	private int frameY = screenHeight/5;
	private int frameW = screenWidth/5*3;
	private int frameH = screenHeight/5*3;
	
	// GUI frame and panels
	
	JFrame frame = new JFrame();
	
	JPanel paneTitleBar = new JPanel();							// Border layout NORTH
	PanelColorSelect paneColorSelect = new PanelColorSelect();	// Border layout WEST
	static PanelDraw paneDraw = new PanelDraw();				// Border layout CENTER
	JPanel paneTools = new JPanel();							// Border layout EAST
	static PanelPreview panePreview = new PanelPreview();							// paneTools layout NORTH
	PanelDrawTools paneDrawTools = new PanelDrawTools();				// paneTools layout CENTER
	PanelSave paneSave = new PanelSave();								// paneTools layout SOUTH
	PanelSizeSelect paneSizeSelect = new PanelSizeSelect();		// Border layout SOUTH
	
	// GUI components
	
	JLabel labelTitle = new JLabel("Pixel Tiles by ArkNLA.dev");
	JButton buttonExit = new JButton("Exit");
	
	// User selections
	
	public static int userGridLineRows = 1;
	public static int userGridLineCols = 1;
	public static Color userColor;
	public static Image userImage;
	
	PixelTilesMain() {

		// User selections default
		
		userGridLineRows = paneSizeSelect.getGridRows();
		userGridLineCols = paneSizeSelect.getGridCols();
		userColor = Color.black;

		// Title bar pane setup
		
		paneTitleBar.setLayout(new BorderLayout());
		
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		buttonExit.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonExit.addActionListener(this);
		
		paneTitleBar.setBackground(Color.LIGHT_GRAY);
		paneTitleBar.add(labelTitle, BorderLayout.CENTER);
		paneTitleBar.add(buttonExit, BorderLayout.EAST);
		
		frame.add(paneTitleBar, BorderLayout.NORTH);
		
		// Color select pane setup
		
		frame.add(paneColorSelect, BorderLayout.WEST);
		
		// Draw pane setup
		
		frame.add(paneDraw, BorderLayout.CENTER);
		
		// Tools pane setup
		
		paneTools.setLayout(new BorderLayout());
		
		// Tools pane NORTH
		
		panePreview.setPreferredSize(new Dimension(250,250));
		paneDrawTools.add(panePreview, BorderLayout.NORTH);
		
		
		paneTools.add(paneDrawTools, BorderLayout.CENTER);
		paneTools.add(paneSave, BorderLayout.SOUTH);
		
		frame.add(paneTools, BorderLayout.EAST);
		
		// Size select pane setup
		
		frame.add(paneSizeSelect, BorderLayout.SOUTH);
		
		// Frame properties

		// Full screen option, not used
		//frame.setBounds(0,0,screenWidth, screenHeight);
		//frame.setUndecorated(true);		
		//frame.setResizable(false);
		//buttonExit.setVisible(true);
		  
		 
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(frameX, frameY, frameW, frameH);
		frame.setTitle("Pixel Tiles by ArkNLA.dev");
		frame.setMinimumSize(new Dimension(screenWidth/5*2, screenHeight/5*2));
		buttonExit.setVisible(false);
		frame.setResizable(false);
		frame.setVisible(true);

	}
	
	public static void main(String[] args) {
		
		PixelTilesMain start = new PixelTilesMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		if (source == buttonExit) exitProgram();
		
	}
	
	public void exitProgram() {
		
		// Wrap up exit
		
		System.exit(1);
	}
}
