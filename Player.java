import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Player extends Character implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5084782657694635980L;
	private int maxWeaponWeight, maxHp, maxArmourWeight, totalInventoryWeight;
	private Apparel[] armour;
	private List<Skill> skills;
	private Weapon weapon;
	private Room currentRoom;
	private Object offHand;

	public Player(String name, String description, int exp, int level, int hp, int credits, Apparel[] armour,
			int[] resistances, Apparel underClothes, Weapon equipped, Object offHand, int inventorySize, List<Object> inventory,
			int[] REPLICAS, List<Skill> skills, String affiliation, Room currentRoom) {
		super(name, exp, level, hp, credits, armour, resistances, underClothes, equipped, inventorySize, inventory,
				REPLICAS, skills, affiliation);
		/*
		 * All player skills are scaled from 0 to 200 usually with a fixed base of 50.
		 * This could possibly be changed by a players chosen difficulty setting during
		 * play-throughs. Max 17/11/18
		 */
		int temp = (this.getREPLICAS()[5] * 10) + 50;
		Skill Persuasion = new Skill("Persuasion", "Affects the player's ability to persuade NPCs in dialogue.", temp);
		temp = (this.getREPLICAS()[4] * 10) + (this.getREPLICAS()[2] * 5) + 50;
		Skill Hacking = new Skill("Hacking",
				"Affects the player's ability to break into and use hostile computer systems.", temp);
		Skill Medicine = new Skill("Medicine",
				"Affects the player's ability to use medical equipment as well as perform triage "
						+ "and other medical tasks.",
				temp);
		temp = (this.getREPLICAS()[4] * 20);
		Skill AdvancedWeapons = new Skill("Advanced Weapons Training",
				"Affects the player's ability to use more advanced weapons", temp);
		Skill[] skillsArr = new Skill[] { Persuasion, Hacking, Medicine, AdvancedWeapons }; // Other skills could be
																							// added in the future, open
																							// to ideas. Max 17/11/18
		this.skills = Arrays.asList(skillsArr);
		this.totalInventoryWeight = 0;
		this.maxHp = 20 + REPLICAS[7] * 10;
		this.armour = armour;
		this.maxArmourWeight = REPLICAS[7] * 10;
		this.offHand = offHand;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public int getMaxWeaponWeight() {
		return maxWeaponWeight;
	}

	public void setMaxWeaponWeight(int maxWeaponWeight) {
		this.maxWeaponWeight = maxWeaponWeight;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMaxArmourWeight() {
		return maxArmourWeight;
	}

	public void setMaxArmourWeight(int maxArmourWeight) {
		this.maxArmourWeight = maxArmourWeight;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public int getTotalInventoryWeight() {
		return totalInventoryWeight;
	}

	public void setTotalInventoryWeight(int totalInventoryWeight) {
		this.totalInventoryWeight = totalInventoryWeight;
	}

	/**
	 * @return the weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * @param weapon the weapon to set
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
	 * @param armour the armour to set
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
	 * @param offHand the offHand to set
	 */
	public void setOffHand(Object offHand) {
		this.offHand = offHand;
	}

}
