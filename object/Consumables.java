package object;

import java.io.Serializable;
import java.util.List;

public class Consumables extends Object implements Serializable {
	private static final long serialVersionUID = -5822284005527688486L;
	// heals, replicas, skills
	private int healing;
	private int[] skills;
	private int[] replicas;
	private int duration;


	public Consumables(boolean inventoriable, String name, String description, List<Object> parts, String[] combinable,
			int weight, int value, int healing, int[] skills, int[] replicas, int duration) {
		super(inventoriable, name, description, parts, combinable, weight, value);
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

	public void setReplicas(int[] replicas) {
		this.replicas = replicas;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
