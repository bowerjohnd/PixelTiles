package dev.ArkNLA.pixelTiles;

public class ColorArray{

	String[][] userImageColorArray;
	int pixelCountWidth;
	int pixelCountHeight;
	int step;
	
	ColorArray() {
		this.pixelCountWidth = 10;
		this.pixelCountHeight = 10;
		this.step = 50;
		
		userImageColorArray = new String[pixelCountWidth][pixelCountHeight];
		
		randomFillArray();

	}
	
	ColorArray(int imageX, int imageY, int imageStep) {
		
		this.pixelCountWidth = imageX;
		this.pixelCountHeight = imageY;
		this.step = imageStep;

		userImageColorArray = new String[pixelCountWidth][pixelCountHeight];

		randomFillArray();

	}
	
	void randomFillArray() {

		int r = 255;
		int g = 255;
		int b = 255;
		int a = 0;

		for (int i = 0; i < pixelCountWidth; i++) {
			for (int j = 0; j < pixelCountHeight; j++) {
				r = (int)(Math.random()*255);
				g = (int)(Math.random()*255);
				b = (int)(Math.random()*255);
				a = (int)(Math.random()*255);
				
				userImageColorArray[i][j] = r + "," + b + "," + g + "," + a;
			}
		}
		
	}

	public String[][] getColorArray() {
		return userImageColorArray;
	}
	
	public String getColorInArray(int x, int y) {
		return userImageColorArray[x][y];
	}

	public void setColorInArray(int x, int y, int r, int g, int b, int a) {
		this.userImageColorArray[x][y] = r + "," + g + "," + b + "," + a;
	}

	public int getPixelCountWidth() {
		return pixelCountWidth;
	}

	public void setPixelCountWidth(int pixelCountWidth) {
		this.pixelCountWidth = pixelCountWidth;
	}

	public int getPixelCountHeight() {
		return pixelCountHeight;
	}

	public void setPixelCountHeight(int pixelCountHeight) {
		this.pixelCountHeight = pixelCountHeight;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	

}
