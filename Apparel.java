import java.io.Serializable;
import java.util.List;

public class Apparel extends Object implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8229924260087295982L;
	private boolean isUnderClothes;
	private int area, damageThreshold;
	private int[] REPLICASmodifiers, resistances; //Corrosive, Kinetic, Energy, Thermal, Radiation.
	
	public Apparel(String name, String description, List<Object> parts, 
			int weight, int value, boolean isUnderClothes, int area, 
			int[] resistances, int[] REPLICASmodifiers, int damageThreshold) {
		super(true, name, description, parts, null, weight, value);
		this.isUnderClothes = isUnderClothes;
		this.area = area;
		this.resistances = resistances;
		this.REPLICASmodifiers = REPLICASmodifiers;
		this.damageThreshold = damageThreshold;
	}
	
	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int[] getResistances() {
		return resistances;
	}

	public void setResistances(int[] resistances) {
		this.resistances = resistances;
	}

	public int[] getREPLICASmodifiers() {
		return REPLICASmodifiers;
	}

	public void setREPLICASmodifiers(int[] rEPLICASmodifiers) {
		REPLICASmodifiers = rEPLICASmodifiers;
	}

	public int getDamageThreshold() {
		return damageThreshold;
	}

	public void setDamageThreshold(int damageThreshold) {
		this.damageThreshold = damageThreshold;
	}

	
	public boolean isUnderClothes() {
		return isUnderClothes;
	}

	
	public void setUnderClothes(boolean isUnderClothes) {
		this.isUnderClothes = isUnderClothes;
	}
}
