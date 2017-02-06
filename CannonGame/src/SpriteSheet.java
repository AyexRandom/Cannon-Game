import java.awt.image.BufferedImage;

//class to handle grabbing from the sprite sheet
public class SpriteSheet {

	// initializing a gerneral image
	private BufferedImage image;

	public SpriteSheet(BufferedImage image) {
		this.image = image;

	}

	// method for grabbing images depending on col,row,and pixel width and
	// height
	public BufferedImage grabImage(int col, int row, int width, int height) {
		// starting point
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}

}
