package construction;

import java.io.Serializable;
import java.util.List;
import object.Object;
import weapon.Weapon;

public class Character implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2118679890920812909L;
	private String name;
	private Weapon equipped;
	private List<Object> inventory;

	public Character(String name, Weapon equipped, List<Object> inventory) {
		this.name = name;
		this.equipped = equipped;
		this.inventory = inventory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}