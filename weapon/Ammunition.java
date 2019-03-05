package weapon;

import java.io.Serializable;
import java.util.Collections;

import object.Object;

public class Ammunition extends Object implements Serializable {// TODO Kinetic(Bullets), Plasma Cells, Power Cells,
																// Missiles, Ferrous projectiles.
	/**
	 * 
	 */
	private static final long serialVersionUID = -3616094952060982278L;
	private float calibre;

	public Ammunition(String name, float calibre, String damageType, String description) {
		super(true, name, description, Collections.emptyList(), null);
		this.calibre = calibre;
	}

	public float getCalibre() {
		return calibre;
	}

	public void setCalibre(float calibre) {
		this.calibre = calibre;
	}
}
