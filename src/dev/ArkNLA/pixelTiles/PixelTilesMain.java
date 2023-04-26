package dev.ArkNLA.pixelTiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class PixelTilesMain implements ActionListener {
	
	/*
	 * 		4/25/2023
	 * 		Known Bugs:		- Problems with duplication
	 * 		
	 * 		TODO: 			- get transparency working
	 * 						- clearing of image to transparency (currently white wash)
	 * 						- Tabs: favorite color schemes, combine tiles to one image, image edits
	 * 						- Possibly add JMenuBar?
	 * 						- Add more draw tools
	 * 
	 * 						* make favorites into use buttons, provide the deletion to favorites tab
	 * 						* favorites tab will allow default/custom grouping favorites: no-group, tree, dirt, defRed, etc.
	 * 					
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
	JPanel tabPaneColorFactory = new JPanel();
	JPanel tabPaneImageFactory = new JPanel();
	JPanel tabPaneCollageFactory = new JPanel();
	
	// Tile Factory components
	
	JPanel paneTitleBar = new JPanel();									// TileFactory Border layout NORTH
	static PanelColorSelect paneColorSelect = new PanelColorSelect();	// TileFactory Border layout WEST
	static PanelDrawTile paneDrawTile = new PanelDrawTile();			// TileFactory Border layout CENTER
	PanelTools paneTools = new PanelTools();							// TileFactory Border layout EAST
	PanelGridSelect paneSizeSelect = new PanelGridSelect();				// TileFactory Border layout SOUTH
	
	// GUI components
	
	JLabel labelTitle = new JLabel("Pixel Tiles by John Bower");
	
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
		
		paneTitleBar.setBackground(Color.LIGHT_GRAY);
		paneTitleBar.add(labelTitle, BorderLayout.CENTER);
				
		frame.add(paneTitleBar, BorderLayout.NORTH);
		
		/*
		*		Tile Factory Tab
		*/
		tabPaneTileFactory.setLayout(new BorderLayout());
		
		tabPaneTileFactory.add(new PanelColorSelect(), BorderLayout.WEST);
		tabPaneTileFactory.add(paneDrawTile, BorderLayout.CENTER);
		tabPaneTileFactory.add(paneTools, BorderLayout.EAST);
		tabPaneTileFactory.add(paneSizeSelect, BorderLayout.SOUTH);
		
		/*
		 *		 Color Factory Tab
		 */
		tabPaneColorFactory.setLayout(new BorderLayout());
		
		tabPaneColorFactory.add(new PanelColorSelect(), BorderLayout.WEST);
		tabPaneColorFactory.add(new PanelFavoriteCatagories(), BorderLayout.CENTER);
		
		/*
		 *		 Image Factory Tab
		 */
		tabPaneImageFactory.setLayout(new BorderLayout());
		
		tabPaneImageFactory.add(new PanelImageCatagories(), BorderLayout.CENTER);
		
		/*
		 *		 Collage Factory Tab
		 */
		tabPaneCollageFactory.setLayout(new BorderLayout());

		tabPaneCollageFactory.add(new PanelDrawCollage(), BorderLayout.CENTER);
		
		/*
		 *		 Add tab panels to tabbedPane
		 */

		tabbedPane.addTab("Tile Factory", tabPaneTileFactory);
		tabbedPane.addTab("Color Factory", tabPaneColorFactory);
		tabbedPane.addTab("Image Factory", tabPaneImageFactory);
		tabbedPane.addTab("Collage Factory", tabPaneCollageFactory);

		// Frame properties

		setLookAndFeel();
		  
		frame.add(tabbedPane, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(frameX, frameY, frameW, frameH);
		frame.setTitle("Pixel Tiles by John Bower");
		frame.setMinimumSize(new Dimension(screenWidth/5*2, screenHeight/5*2));
		frame.setResizable(true);			
		frame.setVisible(true);
		
		ImageIcon icon = new ImageIcon(".//resources//icon.png");
	    frame.setIconImage(icon.getImage());
	}
	
	public static void main(String[] args) {
		
		PixelTilesMain start = new PixelTilesMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		
	}

	public void setLookAndFeel() {
		/*
		OK default javax.swing.plaf.metal.MetalLookAndFeel
		OK javax.swing.plaf.nimbus.NimbusLookAndFeel
		NO com.sun.java.swing.plaf.motif.MotifLookAndFeel
		OK com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		OK com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
		 */
	   try {

	       UIManager.setLookAndFeel(
	    		   "javax.swing.plaf.metal.MetalLookAndFeel"
	    		   );
	   }
	   catch (Exception e) {
	       System.out.println("Look and Feel not set");
	   }
	}
}
