import java.io.Serializable;
import java.util.Collections;

public class Modification extends Object implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8286114933151004877L;
	//TODO: Conversion kits, scopes, receivers, barrels, stocks/grips, magazines, muzzle.
	private String typeMod;
	private int damageMod, rateOfFireMod, conditionMod, durabilityMod, magazineCapacityMod;
	private float accuracyMod, critChanceMod, critDamageMod;
	private Ammunition ammunitionMod;
	private Skill skilledUse;

	public Modification(String name, String description, int weight, int value,  String typeMod, int damageMod, 
			int rateOfFireMod, int conditionMod, int durabilityMod, float accuracyMod,
			float critChanceMod, float critDamageMod, Ammunition ammunitionMod, 
			int magazineCapacityMod, Skill SkiledUse) {
		super(true, name, description, Collections.emptyList(), new String[0] , weight, value);
		this.typeMod = typeMod;
		this.damageMod = damageMod;
		this.rateOfFireMod = rateOfFireMod;
		this.conditionMod = conditionMod;
		this.durabilityMod = durabilityMod;
		this.accuracyMod = accuracyMod;
		this.critChanceMod = critChanceMod;
		this.critDamageMod = critDamageMod;
		this.ammunitionMod = ammunitionMod;
		this.magazineCapacityMod = magazineCapacityMod;
		this.skilledUse = skilledUse;
	}

	public String getTypeMod() {
		return typeMod;
	}

	public void setTypeMod(String typeMod) {
		this.typeMod = typeMod;
	}

	public int getDamageMod() {
		return damageMod;
	}

	public void setDamageMod(int damageMod) {
		this.damageMod = damageMod;
	}

	public int getRateOfFireMod() {
		return rateOfFireMod;
	}

	public void setRateOfFireMod(int rateOfFireMod) {
		this.rateOfFireMod = rateOfFireMod;
	}

	public int getConditionMod() {
		return conditionMod;
	}

	public void setConditionMod(int conditionMod) {
		this.conditionMod = conditionMod;
	}

	public int getDurabilityMod() {
		return durabilityMod;
	}

	public void setDurabilityMod(int durabilityMod) {
		this.durabilityMod = durabilityMod;
	}

	public float getAccuracyMod() {
		return accuracyMod;
	}

	public void setAccuracyMod(float accuracyMod) {
		this.accuracyMod = accuracyMod;
	}

	public float getCritChanceMod() {
		return critChanceMod;
	}

	public void setCritChanceMod(float critChanceMod) {
		this.critChanceMod = critChanceMod;
	}

	public float getCritDamageMod() {
		return critDamageMod;
	}

	public void setCritDamageMod(float critDamageMod) {
		this.critDamageMod = critDamageMod;
	}

	public Ammunition getAmmunitionMod() {
		return ammunitionMod;
	}

	public void setAmmunitionMod(Ammunition ammunitionMod) {
		this.ammunitionMod = ammunitionMod;
	}

	public int getMagazineCapacityMod() {
		return magazineCapacityMod;
	}

	public void setMagazineCapacityMod(int magazineCapacityMod) {
		this.magazineCapacityMod = magazineCapacityMod;
	}
	

	public Skill getSkilledUse() {
		return skilledUse;
	}
	

	public void setSkilledUse(Skill skilledUse) {
		this.skilledUse = skilledUse;
	}
}
