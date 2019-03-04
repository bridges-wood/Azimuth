package construction;

import java.io.Serializable;
import java.util.List;

import apparel.Apparel;
import object.Object;
import weapon.Weapon;

public class Character implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2118679890920812909L;
	// Level banding of enemies and different types as well as character creator.
	private String name;
	private Apparel[] armour; // Head, Torso, Left Arm, Right Arm, Left Leg, Right Leg.
	private Apparel underClothes;
	private Weapon equipped;
	private List<Object> inventory;

	public Character(String name, Apparel[] armour, Apparel underClothes, Weapon equipped, List<Object> inventory) {
		this.name = name;
		this.armour = armour;
		this.underClothes = underClothes;
		this.equipped = equipped;
		this.inventory = inventory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Apparel getUnderClothes() {
		return underClothes;
	}

	public void setUnderClothes(Apparel underClothes) {
		this.underClothes = underClothes;
	}

	public Weapon getEquipped() {
		return equipped;
	}

	public void setEquipped(Weapon equipped) {
		this.equipped = equipped;
	}

	public List<Object> getInventory() {
		return inventory;
	}

	public void setInventory(List<Object> inventory) {
		this.inventory = inventory;
	}

	public void addToInventory(Object toAdd) {
		this.inventory.add(toAdd);
	}

	/**
	 * @return the armour
	 */
	public Apparel[] getArmour() {
		return armour;
	}

	/**
	 * @param armour
	 *            the armour to set
	 */
	public void setArmour(Apparel[] armour) {
		this.armour = armour;
	}

}