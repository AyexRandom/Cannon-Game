import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject {

	private Images tex;

	// enemy constructor to make more enemies
	public Enemy(double x, double y, Images tex) {
		super(x, y);
		this.tex = tex;
	}

	// anything that moves goes through this
	public void tick() {
		// enemy moves to the right at 1 unit per second and re appears at the
		// other side once it reaches screen length
		getMovement(1, 0);

		if ((this.x > 640 - 20) || (this.y < 0) || (this.y > 600)) {
			this.x = 0;

		}

	}

	// render the enemy
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int) x, (int) y, null);

	}

	// setting a bounds around enemy
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	// setters and getters
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void getMovement(int x, int y) {
		this.x += x;
		this.y += y;

	}

}
