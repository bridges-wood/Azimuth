package construction;

import java.io.Serializable;
import java.util.List;

import apparel.Apparel;
import object.Object;
import weapon.Weapon;

public class Player extends Character implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5084782657694635980L;
	private Apparel[] armour;
	private Weapon weapon;
	private Room currentRoom;
	private Object offHand;

	public Player(String name, String description, int exp, int level, int hp, int credits, Apparel[] armour,
			Apparel underClothes, Weapon equipped, Object offHand, int inventorySize, List<Object> inventory,
			Room currentRoom) {
		super(name, armour, underClothes, equipped, inventory);
		/*
		 * All player skills are scaled from 0 to 200 usually with a fixed base of 50.
		 * This could possibly be changed by a players chosen difficulty setting during
		 * play-throughs. Max 17/11/18
		 */
		this.armour = armour;
		this.offHand = offHand;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	/**
	 * @return the weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * @param weapon
	 *            the weapon to set
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
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

	/**
	 * @return the offHand
	 */
	public Object getOffHand() {
		return offHand;
	}

	/**
	 * @param offHand
	 *            the offHand to set
	 */
	public void setOffHand(Object offHand) {
		this.offHand = offHand;
	}

}
