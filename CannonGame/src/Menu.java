import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

//class for the menu screen
public class Menu {

	// the 3 buttons that will be used
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 200, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 300, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 400, 100, 50);

	// render that buttons and grpahics with it
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// fonts
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		Font fnt2 = new Font("arial", Font.BOLD, 30);

		g.setFont(fnt0);
		g.setColor(Color.RED);
		g.drawString("Cannon Game", Game.WIDTH / 2, 100);
		g.setFont(fnt1);
		g.drawString("Created by: Devin Lee & Nathan Cartwright", (int) (Game.WIDTH / 2.5), 150);

		// set where the buttons go
		g.setFont(fnt2);
		g.drawString("Play", playButton.x + 19, playButton.y + 35);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
		g2d.draw(quitButton);

	}
}
