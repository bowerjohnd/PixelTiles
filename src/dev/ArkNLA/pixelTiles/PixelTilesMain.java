package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
	
	JPanel paneTitleBar = new JPanel();									// Border layout NORTH
	static PanelColorSelect paneColorSelect = new PanelColorSelect();	// Border layout WEST
	static PanelDraw paneDraw = new PanelDraw();						// Border layout CENTER
	JPanel paneTools = new JPanel();									// Border layout EAST
	static PanelPreview panePreview = new PanelPreview();						// paneTools layout NORTH
	PanelDrawTools paneDrawTools = new PanelDrawTools();						// paneTools layout CENTER
	PanelSave paneSave = new PanelSave();										// paneTools layout SOUTH
	PanelSizeSelect paneSizeSelect = new PanelSizeSelect();				// Border layout SOUTH
	
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

		/*
		*	 Title bar pane setup	FRAME NORTH
		*/
		
		paneTitleBar.setLayout(new BorderLayout());
		
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		buttonExit.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonExit.addActionListener(this);
		
		paneTitleBar.setBackground(Color.LIGHT_GRAY);
		paneTitleBar.add(labelTitle, BorderLayout.CENTER);
		paneTitleBar.add(buttonExit, BorderLayout.EAST);
		
		frame.add(paneTitleBar, BorderLayout.NORTH);
		
		/*
		*	 Color select pane setup	FRAME WEST
		*/
		
		frame.add(paneColorSelect, BorderLayout.WEST);
		
		/*
		*	 Draw pane setup	FRAME CENTER
		*/
		
		frame.add(paneDraw, BorderLayout.CENTER);
		
		/*
		*	 Tools pane setup	FRAME EAST
		*/
		
		paneTools.setLayout(new BorderLayout());
			
		panePreview.setPreferredSize(new Dimension(250,250));
		paneTools.add(panePreview, BorderLayout.NORTH);
		
		// Possible feature with mirror splits, etc.
		// paneTools.add(paneDrawTools, BorderLayout.CENTER);
		
		paneSave.setPreferredSize(new Dimension(250,250));
		paneTools.add(paneSave, BorderLayout.SOUTH);
		
		frame.add(paneTools, BorderLayout.EAST);
		
		/*
		*	 Size select pane setup		FRAME SOUTH
		*/
		
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
		frame.setResizable(false);			// false until I can work out draw panel resizing
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
