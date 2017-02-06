import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.event.*;
import sun.audio.*;
import javax.swing.*;
import java.io.*;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	// setting initial scale of the screen
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	// score variable
	int score = 0;
	public final String TITLE = "Cannon Game";

	private boolean running = false;
	private Thread thread;

	// buffered images initialized
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	// private BufferedImage base = null;
	// private BufferedImage turret = null;
	// private Point targetPoint;

	// public Game() {
	// addMouseMotionListener(new MouseAdapter() {
	//
	// @Override
	// public void mouseMoved(MouseEvent e) {
	// targetPoint = e.getPoint();
	// repaint();
	// }
	//
	// });}

	private boolean is_shooting = false;

	// importing all the variables
	private Player p;
	private Controller c;
	private Images tex;
	private Menu menu;
	private Bullet bullet;

	// enemy and bullet entities
	public LinkedList<Enemy> e;
	public Bullet b;

	// states of the game
	public static enum STATE {
		MENU, GAME
	};

	public static STATE State = STATE.MENU;

	// getting required files and initializing initial images
	public void init() {

		// automatically focus on screen
		requestFocus();

		BufferedImageLoader loader = new BufferedImageLoader();

		System.out.println("**************");
		System.out.println("File names:");
		File file = new File(".");
		for (String fileNames : file.list())
			System.out.println(fileNames);
		System.out.println("**************");

		System.out.println("Trying to load image...");

		// grabbing files
		try {
			spriteSheet = loader.loadImage("res/sprite_Sheet.png");
			background = loader.loadImage("res/background.png");
			// base = loader.loadImage("res/Base.png");
			// turret = loader.loadImage("res/Turret.png");

		} catch (IOException e) {
		}
		System.out.println("Image loaded!");

		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());

		// grab the images from texture class and initialize
		tex = new Images(this);
		p = new Player(200, 200, tex);
		c = new Controller(this, tex);

		menu = new Menu();
		bullet = new Bullet(10, 10, tex, this);
		e = c.gete();
	}

	// method to start thread
	private synchronized void start() {
		// if true than stop, can't have the thread be recalled
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	// method to stop thread
	private synchronized void stop() {
		if (!running)
			return;

		running = false;

		// if it fails
		try {
			// joins the threads together and waits for stop
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	// game loop
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	// anything that moves goes through this
	private void tick() {
		if (State == STATE.GAME) {
			p.tick();
			c.tick();

		}

	}

	// balls left
	int times = 20;

	// menu button to return to menu
	public Rectangle menuButton = new Rectangle(Game.WIDTH / 2 + 400, 400, 50, 25);

	private void render() {
		// handles all the buffering
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.RED);

		///////////////////////////////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		// if state is game than load game
		if (State == STATE.GAME) {
			Graphics2D g2d = (Graphics2D) g;

			g.drawImage(background, 0, 0, null);
			g2d.draw(menuButton);
			g.drawString("Menu", menuButton.x + 10, menuButton.y + 18);

			g.drawString("Cannon Balls Left: " + (times), (int) (Game.WIDTH + 200), 450);
			g.drawString("Score: " + bullet.getScore(), (int) (Game.WIDTH + 200), 475);

			p.render(g);
			c.render(g);

			// cannon with rotation but can not shoot diagonally
			// g.create();
			// if (base != null) {
			// double x = (getWidth() - base.getWidth()) / 2d;
			// double y = (450) ;
			// // Test line from center of tank to mouse poisition
			// if (targetPoint != null) {
			// g2d.draw(new Line2D.Double((x + 12), (y + 12), targetPoint.x,
			// targetPoint.y));
			// }
			// AffineTransform at = AffineTransform.getTranslateInstance(x, y);
			// g2d.setTransform(at);
			// g2d.drawImage(base, 0, 0, this);
			// at.translate(8, 8);
			// if (targetPoint != null) {
			// double deltaX = (x + 8) - targetPoint.x;
			// double deltaY = (y + 8) - targetPoint.y;
			//
			// double rotation = Math.atan2(deltaY, deltaX) +
			// Math.toRadians(180d);
			// at.rotate(rotation, 4, 4);
			// }
			// g2d.setTransform(at);
			// g2d.drawImage(turret, 0, 0, this);
			// }
			//
			// g2d.dispose();

			// if menu is loaded than render menu
		} else if (State == STATE.MENU) {
			menu.render(g);
		}
		//////////////////////////////////////////////
		// get rid of old
		g.dispose();
		bs.show();

	}

	// keypressed method
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (State == STATE.GAME) {
			// right key
			if (key == KeyEvent.VK_RIGHT) {
				p.setvelX(5);
				// left key
			} else if (key == KeyEvent.VK_LEFT) {
				p.setvelX(-5);
				// down key
			} else if (key == KeyEvent.VK_DOWN) {
				p.setvelY(5);
				// up key
			} else if (key == KeyEvent.VK_UP) {
				p.setvelY(-5);
				// space key
			} else if (key == KeyEvent.VK_SPACE && is_shooting == false) {
				// if pressed shooting is true so add a bullet to where the
				// player coordinates are at
				is_shooting = true;
				c.addBullet(new Bullet(p.getX(), p.getY(), tex, this));
				times--;
			}
		}
	}

	// key released set back to zeros
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			p.setvelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setvelX(0);

		} else if (key == KeyEvent.VK_DOWN) {
			p.setvelY(0);

		} else if (key == KeyEvent.VK_UP) {
			p.setvelY(0);

		} else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;
		}

	}
	

	public static void main(String args[]) {
		// initialize the game
		Game game = new Game();
		// set sizes
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setBackground(Color.GRAY);

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.setBackground(Color.GRAY);
		// puts everything together
		frame.pack();
		// allows exit button to work
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// can not resize
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		// can see it
		frame.setVisible(true);

		// start game
		game.start();
	}

	// getter and setters
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

}
