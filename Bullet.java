import java.io.Serializable;
import java.util.List;

public class Bullet extends Ammunition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4052838366034558075L;

	public Bullet(String name, List<Object> parts, int value, int weight, float calibre, String damageType,
			String description) {
		super("Bullet", parts, value, weight, calibre, "Kinetic", null);
		// TODO Auto-generated constructor stub
	}

}
