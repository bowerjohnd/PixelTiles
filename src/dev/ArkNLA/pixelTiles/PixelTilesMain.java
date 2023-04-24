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
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class PixelTilesMain implements ActionListener {
	
	/*
	 * 		4/4/2023
	 * 		Known Bugs:		- see PanelDraw
	 * 		
	 * 		TODO: 			- get transparency working
	 * 						- clearing of image to transparency (currently white wash)
	 * 						- Tabs: favorite color schemes, combine tiles to one image, image edits
	 * 						- Change PanelSizeSelect to drop down of multiples (3,6,9.. 5,10,15... 10,20,30...)
	 * 						- Possibly add JMenuBar?
	 * 						- Add more draw tools
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
	
	/*
	 * 		GUI components
	 */
	
	JFrame frame = new JFrame();	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	// Tab panels
	
	JPanel tabPaneTileFactory = new JPanel();
	JPanel tabPaneFavoriteColors = new JPanel();
	
	// Tile Factory components
	
	JPanel paneTitleBar = new JPanel();									// TileFactory Border layout NORTH
	static PanelColorSelect paneColorSelect = new PanelColorSelect();	// TileFactory Border layout WEST
	static PanelDraw paneDraw = new PanelDraw();						// TileFactory Border layout CENTER
	PanelTools paneTools = new PanelTools();							// TileFactory Border layout EAST
	PanelGridSelect paneSizeSelect = new PanelGridSelect();				// TileFactory Border layout SOUTH
	
	// GUI components
	
	JLabel labelTitle = new JLabel("Pixel Tiles by John Bower");
	JButton buttonExit = new JButton("Exit");
	
	// User selections
	
	public static int userGridSize = 1;
	public static Color userColor;
	public static Image userImage;
	
	PixelTilesMain() {
		
		frame.setLayout(new BorderLayout());
		tabbedPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		// User selections default
		
		userGridSize = paneSizeSelect.getGrid();
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
		*		Tile Factory Tab
		*/
		tabPaneTileFactory.setLayout(new BorderLayout());
		
		tabPaneTileFactory.add(paneColorSelect, BorderLayout.WEST);
		tabPaneTileFactory.add(paneDraw, BorderLayout.CENTER);
		tabPaneTileFactory.add(paneTools, BorderLayout.EAST);
		tabPaneTileFactory.add(paneSizeSelect, BorderLayout.SOUTH);
		
		/*
		 *		Favorite Colors Tab
		 */
		
		// Add tabs to tabbedPane
		
		tabbedPane.addTab("Tile Factory", tabPaneTileFactory);
		tabbedPane.addTab("Favorite Colors", tabPaneFavoriteColors);
		
		// Frame properties

		// Full screen option, not used
		//frame.setBounds(0,0,screenWidth, screenHeight);
		//frame.setUndecorated(true);		
		//frame.setResizable(false);
		//buttonExit.setVisible(true);
		buttonExit.setVisible(false);
		  
		frame.add(tabbedPane, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(frameX, frameY, frameW, frameH);
		frame.setTitle("Pixel Tiles by John Bower");
		frame.setMinimumSize(new Dimension(screenWidth/5*2, screenHeight/5*2));
		frame.setResizable(true);			
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
