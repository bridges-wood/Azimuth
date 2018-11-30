import java.io.Serializable;

public class Helmet extends Apparel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7132407000825074793L;

	public Helmet(String name, String description, int weight, int value, int[] resistances, int[] REPLICASmodifiers, int damageThreshold) {
		super(name, description, null, weight, value, false, 0, resistances, REPLICASmodifiers, damageThreshold);
	}

}
