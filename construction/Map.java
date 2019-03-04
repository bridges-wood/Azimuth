package construction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import apparel.Apparel;
import object.Container;
import object.Key;
import object.Terminal;
import object.Usable;
import weapon.Magazine;
import weapon.Weapon;

public class Map {
	
	public List<object.Object> createSubObjects() {
		boolean creating = true;
		List<object.Object> toReturn = new ArrayList<object.Object>();
		while(creating) {
			System.out.print("Object: ");
			String Class = Utilities.StrInput();
			switch(Class) {
			case ("apparel"):
				toReturn.add(createApparel());
				break;
			case ("container"):
				toReturn.add(createContainer());
				break;
			case ("key"):
				toReturn.add(createKey());
				break;
			case ("object"):
				toReturn.add(createObject());
				break;
			case ("terminal"):
				toReturn.add(createTerminal());
				break;
			case ("usable"):
				toReturn.add(createUsable());
				break;
			case ("magazine"):
				toReturn.add(createMagazine());
				break;
			case ("weapon"):
				toReturn.add(createWeapon());
				break;
			case("quit"):
				creating = false;
				break;
			}
		}
		return toReturn;
	}

	private Weapon createWeapon() {
		System.out.print("Inventoriable: ");
		boolean inv = Boolean.parseBoolean(Utilities.StrInput());
		System.out.print("Name: ");
		String name = Utilities.StrInput();
		System.out.print("Description: ");
		String description = Utilities.StrInput();
		System.out.print("Parts? [y/n]: ");
		String confirm = Utilities.StrInput();
		List<object.Object> parts = Collections.emptyList();
		if(confirm.toLowerCase().equals("y")) {
			parts = createSubObjects();
		}
		return new Weapon(inv, name, description, parts);
	}

	private Magazine createMagazine() {
		// TODO Auto-generated method stub
		return null;
	}

	private Usable createUsable() {
		// TODO Auto-generated method stub
		return null;
	}

	private Terminal createTerminal() {
		// TODO Auto-generated method stub
		return null;
	}

	private object.Object createObject() {
		// TODO Auto-generated method stub
		return null;
	}

	private Key createKey() {
		// TODO Auto-generated method stub
		return null;
	}

	private Container createContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	private Apparel createApparel() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
