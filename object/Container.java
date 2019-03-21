package object;

import java.io.Serializable;
import java.util.List;

public class Container extends Object implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2179747136654751547L;
	private boolean locked;
	private Key[] workingKeys;

	public Container(String name, String description, List<Object> parts, boolean locked, Key[] workingKeys) {
		super(false, name, description, parts, new String[0]);
		this.locked = locked;
		this.workingKeys = workingKeys;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Key[] getWorkingKeys() {
		return workingKeys;
	}

	public void setWorkingKeys(Key[] workingKeys) {
		this.workingKeys = workingKeys;
	}

}
