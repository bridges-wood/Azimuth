package weapon;
import java.io.Serializable;

public class Bullet extends Ammunition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4052838366034558075L;
	public Bullet(String name, float calibre) {
		super(name, calibre, "Kinetic", null);
		// TODO Auto-generated constructor stub
	}

}
