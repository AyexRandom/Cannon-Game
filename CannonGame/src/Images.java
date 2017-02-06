import java.awt.image.BufferedImage;

//class for handling all the images
public class Images {

	// initialize images
	public BufferedImage player, missile, enemy;

	// initialize the sprite sheet
	private SpriteSheet ss;

	// pull from game
	public Images(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());

		getTextures();
	}

	// getter for grabbing the image from its specific spot in the sprite sheet
	private void getTextures() {
		player = ss.grabImage(1, 1, 32, 32);
		missile = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(3, 1, 32, 32);

	}

}
