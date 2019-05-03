package object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import construction.Map;
import construction.Utilities;

public class Object implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 251080304814776649L;
	// TODO Terminals (controls and personal), rest-places, consumables, keys,
	// crafting areas.
	private boolean inventoriable;
	private String name, description;
	private List<Object> parts;
	private String[] combinable;

	public Object(boolean inventoriable, String name, String description, List<Object> parts, String[] combinable) {
		this.inventoriable = inventoriable;
		this.name = name;
		this.description = description;
		this.parts = parts;
		this.combinable = combinable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInventoriable() {
		return inventoriable;
	}

	public void setInventoriable(boolean inventoriable) {
		this.inventoriable = inventoriable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Object> getParts() {
		return parts;
	}

	public void setParts(List<Object> parts) {
		this.parts = parts;
	}

	public String[] getCombinable() {
		return combinable;
	}

	public void setCombinable(String[] combinable) {
		this.combinable = combinable;
	}

	public static Object modifyObject(int selection, Object object) {
		switch (selection) {
		case 1:
			System.out.println("New locked: ");
			object.setInventoriable(Boolean.parseBoolean(Utilities.StrInput()));
			break;
		case 2:
			System.out.println("New combinable: ");
			for (Object i : Map.objects) {
				System.out.println(i.getName());
			}
			List<Object> combinable = new ArrayList<Object>();
			while (true) {
				System.out.println("Object name: ");
				String choice = Utilities.StrInput();
				if (choice.equals("quit")) {
					break;
				} else {
					for (Object i : Map.objects) {
						if (i.getName().toLowerCase().equals(choice.toLowerCase())) {
							combinable.add(i);
						}
					}
				}
			}
			break;
		case 3:
			System.out.println("New name: ");
			object.setName(Utilities.StrInput());
			break;
		case 4:
			System.out.println("New description: ");
			object.setDescription(Utilities.StrInput());
			break;
		case 5:
			System.out.println("New parts: ");
			object.setParts(Map.createSubObjects());
			break;
		}
		return object;
	}
}
