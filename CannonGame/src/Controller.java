import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

//class for creating multiple entities if desired
public class Controller {

	// variables for bullet and enemy
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	private LinkedList<Enemy> e = new LinkedList<Enemy>();

	// random number
	Random r = new Random();

	// temp variables
	Bullet TempBullet;
	Enemy TempEnemy;

	// bringing in game and textures
	Game game;
	Images tex;

	// constructor for the controller
	public Controller(Game game, Images tex) {
		this.game = game;
		this.tex = tex;

		// add a enemy to the screen
		addEnemy(new Enemy(r.nextInt(Game.WIDTH * game.SCALE), 0, tex));

	}

	// anything that moves goes here
	public void tick() {
		// loop bullet so that multiple can be made
		for (int i = 0; i < b.size(); i++) {
			TempBullet = b.get(i);
			// destroy bullet if it goes out of bounds
			if (TempBullet.getY() < 0)
				removeBullet(TempBullet);

			TempBullet.tick();
		}

		// can have multiple enemies but only use one at this time
		for (int i = 0; i < e.size(); i++) {
			TempEnemy = e.get(i);

			TempEnemy.tick();
		}

	}

	// render graphics
	public void render(Graphics g) {

		// spawn multiple bullets
		for (int i = 0; i < b.size(); i++) {
			TempBullet = b.get(i);

			TempBullet.render(g);
		}
		// multiple enemies
		for (int i = 0; i < e.size(); i++) {
			TempEnemy = e.get(i);

			TempEnemy.render(g);
		}
	}

	// setters and getters
	public void addBullet(Bullet block) {
		b.add(block);
	}

	public void removeBullet(Bullet block) {
		b.remove(block);
	}

	public void addEnemy(Enemy block) {
		e.add(block);
	}

	public void removeEnemy(Enemy block) {
		e.remove(block);
	}

	public LinkedList<Enemy> gete() {
		return e;
	}

	public LinkedList<Bullet> getb() {
		return b;
	}

}
