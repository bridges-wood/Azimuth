import java.io.Serializable;

public class RightArm extends Apparel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4579083595247099276L;

	public RightArm(String name, String description, int weight, int value, int[] resistances, int[] REPLICASmodifiers, int damageThreshold) {
		super(name, description, null, weight, value, false, 3, resistances, REPLICASmodifiers, damageThreshold);
	}

}
