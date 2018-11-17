import java.util.List;

public class Character {// Level banding of enemies and different types as well as character creator.
	private String name;
	private int exp;
	private int level;
	private int hp;
	private int credits;
	private Apparel[] armour; //Head, Torso, Left Arm, Right Arm, Left Leg, Right Leg.
	private int[] resistances;
	private Apparel underClothes;
	private Weapon equipped;
	private int inventorySize;
	private List <Object> inventory;
	private int[] REPLICAS;
	private List <Skill> skill;
	private String affiliation;

	public Character( String name, int exp, int level, int hp, int credits, 
			Apparel[] armour, int[] resistances, Apparel underClothes, Weapon equipped, 
			int inventorySize, List<Object> inventory, int[] REPLICAS, 
			List<Skill> skills, String affiliation ) {
		this.name = name;
		this.exp = exp;
		this.hp = hp;
		this.credits = credits;
		this.armour = armour;
		this.resistances = resistances;
		this.underClothes = underClothes;
		this.equipped = equipped;
		this.inventorySize = inventorySize;
		this.inventory = inventory;
		this.REPLICAS = REPLICAS;
		this.skill = skills;
		this.affiliation = affiliation;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public Apparel[] getApparel() {
		return armour;
	}

	public void setApparel(Apparel[] armour) {
		this.armour = armour;
	}

	public int[] getResistances() {
		return resistances;
	}

	public void setResistances(int[] resistances) {
		this.resistances = resistances;
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

	public int getInventorySize() {
		return inventorySize;
	}

	public void setInventorySize(int inventorySize) {
		this.inventorySize = inventorySize;
	}

	public List<Object> getInventory() {
		return inventory;
	}

	public void setInventory(List<Object> inventory) {
		this.inventory = inventory;
	}

	public int[] getREPLICAS() {
		return REPLICAS;
	}

	public void setREPLICAS(int[] REPLICAS) {
		this.REPLICAS = REPLICAS;
	}

	public List<Skill> getSkills() {
		return skill;
	}

	public void setSkills(List<Skill> skills) {
		this.skill = skills;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

}