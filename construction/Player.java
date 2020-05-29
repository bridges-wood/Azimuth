package construction;

import java.util.List;

import object.Object;
import weapon.Weapon;

public class Player extends Character {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5084782657694635980L;
	private Weapon weapon;
	private Room currentRoom;
	private Object offHand;

	public Player(String name, String description, int exp, int level, int hp, int credits, Weapon equipped,
			Object offHand, int inventorySize, List<Object> inventory, Room currentRoom) {
		super(name, equipped, inventory);
		/*
		 * All player skills are scaled from 0 to 200 usually with a fixed base of 50.
		 * This could possibly be changed by a players chosen difficulty setting during
		 * play-throughs. Max 17/11/18
		 */
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
