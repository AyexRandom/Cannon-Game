import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

	// new velocity points to user for smooth movement
	private double velX = 0;
	private double velY = 0;

	private Images tex;

	// constructor for more players
	public Player(double x, double y, Images tex) {
		super(x, y);

		this.tex = tex;

	}

	// anything that moves goes through this
	public void tick() {
		// depending on keys pressed move player
		x += velX;
		y += velY;

		// set bounds of player and where he can move
		// left
		if (x <= 0)
			x = 0;
		// right
		if (x >= 640 - 20)
			x = 640 - 20;
		// up
		if (y <= 390)
			y = 390;
		// down
		if (y >= 480 - 32)
			y = 480 - 32;
	}

	// render the player into the game
	public void render(Graphics g) {
		g.drawImage(tex.player, (int) x, (int) y, null);
	}

	// set a bounds on the player
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	// setters and getters
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setvelX(double velX) {
		this.velX = velX;
	}

	public void setvelY(double velY) {
		this.velY = velY;
	}

}
