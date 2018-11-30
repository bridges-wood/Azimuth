import java.io.Serializable;

public class RightLeg extends Apparel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6505186992629036296L;

	public RightLeg(String name, String description, int weight, int value, int[] resistances, int[] REPLICASmodifiers, int damageThreshold) {
		super(name, description, null, weight, value, false, 5, resistances, REPLICASmodifiers, damageThreshold);
	}

}
