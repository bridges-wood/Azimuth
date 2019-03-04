package weapon;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import object.Object;

public class Ammunition extends Object implements Serializable {//TODO Kinetic(Bullets), Plasma Cells, Power Cells, Missiles, Ferrous projectiles.
	/**
	 * 
	 */
	private static final long serialVersionUID = -3616094952060982278L;
	private float calibre;
	private String damageType; //Corrosive, Kinetic, Energy, Thermal, Radiation.
	
	public Ammunition(String name, List<Object> parts, 
			int value, int weight, float calibre, String damageType, String description) {
		super(true, name, description, Collections.emptyList(), null);
		this.calibre = calibre;
		this.damageType = damageType;
	}
	
	public float getCalibre() {
		return calibre;
	}
	
	public void setCalibre(float calibre) {
		this.calibre = calibre;
	}
	
	public String getDamageType() {
		return damageType;
	}
	
	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}
}
