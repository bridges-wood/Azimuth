package weapon;

import java.io.Serializable;
import java.util.List;

import object.Modification;
import object.Object;

public class Weapon extends Object implements Serializable { // Plasma, Gauss/Railgun, Laser, Melee, Kinetic.
	/**
	 * 
	 */
	private static final long serialVersionUID = 3254321993836423666L;
	private String type;
	private int damage, rateOfFire, condition, durability;
	private float accuracy, critChance, critDamage;
	private List<Modification> modifications;
	private Ammunition ammunition;

	public Weapon(boolean inventoriable, String name, int weight, int value, String description, List<Object> parts,
			String[] combinable, String type, int damage, int rateOfFire, int condition, int durability, float accuracy,
			float critChance, float critDamage, List<Modification> modifications, Ammunition ammunition) {
		super(inventoriable, name, description, parts, combinable, weight, value);
		this.type = type;
		this.damage = damage;
		this.rateOfFire = rateOfFire;
		this.condition = condition; // If condition is -1, use is unlimited.
		this.durability = durability; // If durability is -1, use is unlimited.
		this.accuracy = accuracy;
		this.critChance = critChance;
		this.critDamage = critDamage; // Modifier
		this.modifications = modifications;
		this.ammunition = ammunition;
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

	public List<Modification> getModifications() {
		return modifications;
	}

	public void setModifications(List<Modification> modifications) {
		this.modifications = modifications;
	}

	public Ammunition getAmmunition() {
		return ammunition;
	}

	public void setAmmunition(Ammunition ammunition) {
		this.ammunition = ammunition;
	}

}
