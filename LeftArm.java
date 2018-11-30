import java.io.Serializable;

public class LeftArm extends Apparel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1954901267226737241L;

	public LeftArm(String name, String description, int weight, int value, int[] resistances, int[] REPLICASmodifiers, int damageThreshold) {
		super(name, description, null, weight, value, false, 2, resistances, REPLICASmodifiers, damageThreshold);
	}

}
