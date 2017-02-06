import java.awt.Rectangle;

//class to create a new object around images in the game that will be used for collision detection
public class GameObject {

	// coordinates
	public double x, y;

	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// creating a rectangle bounds around a given coordinate
	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int) x, (int) y, width, height);
	}

}
