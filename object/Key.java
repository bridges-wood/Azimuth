package object;

import java.util.Collections;

import construction.Utilities;

public class Key extends Object {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6430370260836783830L;
	
	public Key(String name, String description) {
		super(true, name, description, Collections.emptyList(), new String[0]);
	}
	
	public static Key modifyKey(int attribute, Key key) {
		switch (attribute) {
		case 1:
			System.out.println("New name: ");
			key.setName(Utilities.StrInput());
		case 2:
			System.out.println("New description: ");
			key.setDescription(Utilities.StrInput());
		}
		return key;
	}

}
