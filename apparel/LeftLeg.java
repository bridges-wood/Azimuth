package apparel;
import java.io.Serializable;

public class LeftLeg extends Apparel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1204901544077532253L;

	public LeftLeg(String name, String description, int weight, int value, int[] resistances, int[] REPLICASmodifiers, int damageThreshold) {
		super(name, description, null, weight, value, false, 4, resistances, REPLICASmodifiers, damageThreshold);
	}

}
