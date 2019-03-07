package weapon;

import java.io.Serializable;

public class FerrousProjectile extends Ammunition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1823858235501593630L;

	public FerrousProjectile(String name, float calibre) {
		super("Ferrous Projectiles", calibre, "Kinetic", null);
		// TODO Auto-generated constructor stub
	}

}
