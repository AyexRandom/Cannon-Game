import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Bullet extends GameObject {

	// initialize
	private Images tex;
	private Game game;
	private Menu menu;

	static double score = 0;

	// bullet constructor
	public Bullet(double x, double y, Images tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;

	}

	public void tick() {
		// speed of bullet towards the - plain
		y -= 10;

		boolean intersect = false;

		// if the bullet (this) and game.e (enemy) collides score ++
		if (collision.Collision(this, game.e)) {
			intersect = true;
		}

		if (intersect == true && y < -10) {
			score++;
		}

	}

	public void render(Graphics g) {

		// if the balls run out game is over use pop up messages to force game
		// to reset
		if (game.getTimes() < 0) {
			infoBox("GAME OVER " + "Score: " + score, "Congrats");
			infoBox("Press Space or Okay to try again", "Play Again");
			// reset values
			game.setTimes(20);
			score = 0;

		}

		g.drawImage(tex.missile, (int) x, (int) y, null);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	// setters and getters
	public double getY() {
		return y;
	}

	public double getScore() {
		return score;

	}

	// popup message method
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.YES_NO_CANCEL_OPTION);
	}
}
