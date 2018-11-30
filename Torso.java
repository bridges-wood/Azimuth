import java.io.Serializable;

public class Torso extends Apparel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8809067493860959369L;

	public Torso(String name, String description, int weight, int value, int[] resistances, int[] REPLICASmodifiers, int damageThreshold) {
		super(name, description, null, weight, value, false, 1, resistances, REPLICASmodifiers, damageThreshold);
	}

}
