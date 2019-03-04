package weapon;

import java.io.Serializable;
import java.util.Collections;
import object.Object;

public class Magazine extends Object implements Serializable {
	private int rounds;
	private Ammunition ammunition;

	public Magazine(String name, String description, int rounds, Ammunition ammunition) {
		super(true, name, description, Collections.emptyList(), new String[0]);
		this.rounds = rounds;
		this.ammunition = ammunition;
	}

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public Ammunition getAmmunition() {
		return ammunition;
	}

	public void setAmmunition(Ammunition ammunition) {
		this.ammunition = ammunition;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3676436842600679918L;

}
