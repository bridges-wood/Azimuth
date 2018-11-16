
public class Apparel extends Object {
	private boolean isUnderClothes;
	private int area;
	private int[] resistances; //Corrosive, Kinetic, Energy, Thermal, Radiation.
	private int[] REPLICASmodifiers;
	private int damageThreshold;
	
	public Apparel(String name, String description, Object[] parts, 
			int weight, int value, boolean isUnderClothes, int area, 
			int[] resistances, int[] REPLICASmodifiers, int damageThreshold) {
		super(true, name, description, parts, weight, value);
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
