import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

//class for mouse listener to press buttons
public class MouseInput implements MouseListener {

	// auto generated methods not used at this time
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// setting coordinates of mouse
		int my = e.getY();
		int mx = e.getX();

		// public Rectangle playButton = new Rectangle(Game.WIDTH/2
		// +120,200,100,50);
		// public Rectangle helpButton = new Rectangle(Game.WIDTH/2
		// +120,300,100,50);
		// public Rectangle quitButton = new Rectangle(Game.WIDTH/2
		// +120,400,100,50);
		// public Rectangle menuButton = new Rectangle(Game.WIDTH/2
		// +400,400,50,25);

		// if the spot pressed is between these coordinates than its a button
		// and it does something
		// menu button
		if (mx >= Game.WIDTH / 2 + 400 && mx <= Game.WIDTH / 2 + 450) {
			if (my >= 400 && my <= 425) {
				// pressed play button
				Game.State = Game.STATE.MENU;
			}

		}

		// play button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
			if (my >= 200 && my <= 250) {
				// pressed play button
				Game.State = Game.STATE.GAME;
			}

		}

		// quit button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
			if (my >= 400 && my <= 450) {
				// pressed quit button
				System.exit(1);
			}

		}

		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
			if (my >= 300 && my <= 350) {
				// pressed quit button
				infoBox("Move your cannon using the left and right arrow keys. To fire your cannon press space bar.  You have 20 cannon balls try and score as high as possible. Good Luck!",
						"Help");
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// pop up message that i use for help
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

}
