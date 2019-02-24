package weapon;
import java.io.Serializable;
import java.util.List;

import object.Object;

public class FerrousProjectile extends Ammunition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1823858235501593630L;

	public FerrousProjectile(String name, List<Object> parts, int value, int weight, float calibre, String damageType,
			String description) {
		super("Ferrous Projectiles", parts, value, weight, calibre, "Kinetic", null);
		// TODO Auto-generated constructor stub
	}

}
