
public class Weapon extends Object { //Plasma, Gauss/Railgun, Laser, Melee, Kinetic. 
	private String type;
	private int damage, rateOfFire, condition, durability, magazineCapacity;
	private float accuracy, critChance, critDamage;
	private Modification[] modifications;
	private Ammunition ammunition;
	
	public Weapon(boolean inventoriable, String name, int weight, int value, 
			String description, Object[] parts, String type, int damage, 
			int rateOfFire, int condition, int durability, float accuracy, 
			float critChance, float critDamage, Modification[] modifications, 
			Ammunition ammunition, int magazineCapacity) {
		super(inventoriable, name, description, parts, weight, value);
		this.type = type;
		this.damage = damage;
		this.rateOfFire = rateOfFire;
		this.condition = condition;
		this.durability = durability;
		this.accuracy = accuracy;
		this.critChance = critChance;
		this.critDamage = critDamage;
		this.modifications = modifications;
		this.ammunition = ammunition;
		this.magazineCapacity = magazineCapacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getRateOfFire() {
		return rateOfFire;
	}

	public void setRateOfFire(int rateOfFire) {
		this.rateOfFire = rateOfFire;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public float getCritChance() {
		return critChance;
	}

	public void setCritChance(float critChance) {
		this.critChance = critChance;
	}

	public float getCritDamage() {
		return critDamage;
	}

	public void setCritDamage(float critDamage) {
		this.critDamage = critDamage;
	}

	public Modification[] getModifications() {
		return modifications;
	}

	public void setModifications(Modification[] modifications) {
		this.modifications = modifications;
	}

	public Ammunition getAmmunition() {
		return ammunition;
	}

	public void setAmmunition(Ammunition ammunition) {
		this.ammunition = ammunition;
	}

	public int getMagazineCapacity() {
		return magazineCapacity;
	}

	public void setMagazineCapacity(int magazineCapacity) {
		this.magazineCapacity = magazineCapacity;
	}

}
