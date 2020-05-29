package object;

import java.util.List;
import java.util.Map;

public class Usable extends Object {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2993920639711393879L;
	private String genericUse, objectState;
	private Map<Object, String> specificUses;

	public Usable(boolean inventoriable, String name, String description, List<Object> parts, String[] combinable,
			String genericUse, String objectState, Map<Object, String> specificUses) {
		super(inventoriable, name, description, parts, combinable);
		this.genericUse = genericUse;
		this.specificUses = specificUses;
		this.objectState = objectState;
	}

	public String getGenericUse() {
		return genericUse;
	}

	public void setGenericUse(String genericUse) {
		this.genericUse = genericUse;
	}

	public Map<Object, String> getSpecificUses() {
		return specificUses;
	}

	public void setSpecificUses(Map<Object, String> specificUses) {
		this.specificUses = specificUses;
	}

	public String getObjectState() {
		return objectState;
	}

	public void setObjectState(String objectState) {
		this.objectState = objectState;
	}

}
