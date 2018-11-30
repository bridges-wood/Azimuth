
public class Consumables extends Object {
	//heals, replicas, skills
	private int healing;
	private int[] skills;
	private int[] replicas;
	private int duration;
	public Consumables(boolean inventoriable, String name, String description,
			Object[] parts, int weight, int value, int healing, int[] skills, int[] replicas, int duration) {
		super(inventoriable, name, description, parts, weight, value);
	}
	public int getHealing() {
		return healing;
	}
	public void setHealing(int healing) {
		this.healing = healing;
	}
	public int[] getSkills() {
		return skills;
	}
	public void setSkills(int[] skills) {
		this.skills = skills;
	}
	public int[] getReplicas() {
		return replicas;
	}
	public void setReplicas(int[] repilcas) {
		this.replicas = repilcas;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
