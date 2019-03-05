package weapon;

import java.io.Serializable;

public class PlasmaCell extends Ammunition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2961708773867963572L;

	public PlasmaCell(String name, float calibre, String description) {
		super("Plasma", calibre, "Energy", description);
		// TODO Auto-generated constructor stub
	}

}
