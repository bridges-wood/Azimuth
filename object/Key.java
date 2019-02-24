package object;
import java.io.Serializable;
import java.util.Collections;

public class Key extends Object implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6430370260836783830L;
	
	public Key(String name, String description, int value) {
		super(true, name, description, Collections.emptyList(), new String[0], 0, value);
	}

}
