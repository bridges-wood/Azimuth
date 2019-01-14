import java.io.Serializable;

public class Consumables extends Object implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4352137502541921937L;
	private int healthModifier;
	private int[] skillsModifiers;
	private int[] replicasModifiers;
	private int duration;

	public Consumables(boolean inventoriable, String name, String description, Object[] parts, int weight, int value,
			int healthModifier, int[] skillsModifiers, int[] replicasModifiers, int duration) {
		super(inventoriable, name, description, parts, weight, value);
		this.healthModifier = healthModifier;
		this.skillsModifiers = skillsModifiers;
		this.replicasModifiers = replicasModifiers;
		this.duration = duration;
	}


	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int[] getReplicasModifiers() {
		return replicasModifiers;
	}

	public void setReplicasModifiers(int[] replicasModifiers) {
		this.replicasModifiers = replicasModifiers;
	}

	public int[] getSkillsModifiers() {
		return skillsModifiers;
	}

	public void setSkillsModifiers(int[] skillsModifiers) {
		this.skillsModifiers = skillsModifiers;
	}

	public int getHealthModifier() {
		return healthModifier;
	}

	public void setHealthModifier(int healthModifier) {
		this.healthModifier = healthModifier;
	}

}
