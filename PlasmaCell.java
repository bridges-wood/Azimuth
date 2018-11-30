import java.io.Serializable;

public class PlasmaCell extends Ammunition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2961708773867963572L;

	public PlasmaCell(String name, Object[] parts, int value, int weight, float calibre, String damageType,
			String description) {
		super("Plasma", parts, value, weight, calibre, "Energy", description);
		// TODO Auto-generated constructor stub
	}

}
