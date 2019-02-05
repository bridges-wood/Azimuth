import java.io.Serializable;
import java.util.Collections;

public class Key extends Object implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6430370260836783830L;
	private Object lock;
	
	public Key(String name, String description, int value, Object lock) {
		super(true, name, description, Collections.emptyList(), new String[0], 0, value);
		this.lock = lock;
	}

	public Object getLock() {
		return lock;
	}

	public void setLock(Object lock) {
		this.lock = lock;
	}

}
