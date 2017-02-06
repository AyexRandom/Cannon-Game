import java.util.LinkedList;

//class to handle collisions
public class collision {

	// method for collision takes in bullet and a enemy and checks if thier
	// bounds ever intersect
	public static boolean Collision(Bullet b, LinkedList<Enemy> e) {
		for (int i = 0; i < e.size(); i++) {
			if (b.getBounds().intersects(e.get(i).getBounds())) {
				return true;
			}
		}
		return false;
	}

}
