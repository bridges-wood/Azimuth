package construction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import apparel.Apparel;
import object.Container;
import object.Key;
import object.Terminal;
import object.Usable;
import weapon.Ammunition;
import weapon.Bullet;
import weapon.FerrousProjectile;
import weapon.Magazine;
import weapon.PlasmaCell;
import weapon.Weapon;

public class Map implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2735214656381464211L;
	public List<object.Object> existing = new ArrayList<object.Object>();

	public List<object.Object> createSubObjects() {
		boolean creating = true;
		List<object.Object> toReturn = new ArrayList<object.Object>();
		while (creating) {
			System.out.print("Object: ");
			String Class = Utilities.StrInput();
			switch (Class) {
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
			case ("existing"):
				// TODO handle adding precreated objects to rooms. Allow minor modification
				break;
			case ("quit"):
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
		if (confirm.toLowerCase().equals("y")) {
			parts = createSubObjects();
		}
		return new Weapon(inv, name, description, parts);
	}

	private Magazine createMagazine() {
		System.out.print("Name: ");
		String name = Utilities.StrInput();
		System.out.print("Description: ");
		String description = Utilities.StrInput();
		System.out.print("Rounds: ");
		int rounds = Integer.parseInt(Utilities.StrInput());
		Ammunition ammunition = createAmmunition();
		return new Magazine(name, description, rounds, ammunition);
	}

	private Ammunition createAmmunition() {
		System.out.print("Type: ");
		String type = Utilities.StrInput();
		switch (type) {
		case ("bullet"):
			return (createBullet());
		case ("ferrous"):
			return(createFerrousProjectile());
		case ("plasma"):
			return(createPlasmaCell());
		}
		return null;
	}

	private Bullet createBullet() {
		System.out.print("Name: ");
		String name = Utilities.StrInput();
		System.out.print("Calibre");
		Float calibre = Float.parseFloat(Utilities.StrInput());
		return new Bullet(name, calibre);
	}

	private PlasmaCell createPlasmaCell() {
		return null;
		// TODO Auto-generated method stub
		
	}

	private FerrousProjectile createFerrousProjectile() {
		return null;
		// TODO Auto-generated method stub
		
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
