import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//necessary class to handle key press and release
public class KeyInput extends KeyAdapter {

	// using the original game
	Game game;

	public KeyInput(Game game) {
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}

}
