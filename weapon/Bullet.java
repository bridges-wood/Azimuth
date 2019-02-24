package weapon;
import java.io.Serializable;
import java.util.List;

import object.Object;

public class Bullet extends Ammunition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4052838366034558075L;

	public Bullet(List<Object> parts, int value, int weight, float calibre) {
		super("Bullet", parts, value, weight, calibre, "Kinetic", null);
		// TODO Auto-generated constructor stub
	}

}
