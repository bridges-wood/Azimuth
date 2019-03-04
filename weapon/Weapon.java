package weapon;

import java.io.Serializable;
import java.util.List;

import object.Object;

public class Weapon extends Object implements Serializable { // Plasma, Gauss/Railgun, Laser, Melee, Kinetic.
	/**
	 * 
	 */
	private static final long serialVersionUID = 3254321993836423666L;

	public Weapon(boolean inventoriable, String name, String description, List<Object> parts) {
		super(inventoriable, name, description, parts, new String[0]);
	}
}
