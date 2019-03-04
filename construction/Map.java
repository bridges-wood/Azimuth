package construction;

import java.util.ArrayList;
import java.util.List;

import apparel.LeftArm;
import object.Terminal;
import weapon.Weapon;

public class Map {
	
	public List<Object> createRoomContents() {
		boolean creating = true;
		List<Object> toReturn = new ArrayList<Object>();
		while(creating) {
			System.out.print("Object Type: ");
			String Class = Utilities.StrInput();
			switch(Class) {
			case ("apparel"):
				toReturn.add(createApparel());
				break;
			case ("helmet"):
				toReturn.add(createHelmet());
				break;
			case ("leftarm"):
				toReturn.add(createLeftArm());
				break;
			case ("leftleg"):
				toReturn.add(createLeftLeg());
				break;
			case ("rightarm"):
				toReturn.add(createRightArm());
				break;
			case ("rightleg"):
				toReturn.add(createRightLeg());
				break;
			case ("torso"):
				toReturn.add(createTorso());
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
			}
		}
		return toReturn;
	}

	private Weapon createWeapon() {
		// TODO Auto-generated method stub
		return null;
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

	private Object createObject() {
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

	private Torso createTorso() {
		// TODO Auto-generated method stub
		return null;
	}

	private RightLeg createRightLeg() {
		// TODO Auto-generated method stub
		return null;
	}

	private RightArm createRightArm() {
		// TODO Auto-generated method stub
		return null;
	}

	private LeftLeg createLeftLeg() {
		// TODO Auto-generated method stub
		return null;
	}

	private LeftArm createLeftArm() {
		// TODO Auto-generated method stub
		return null;
	}

	private helmet createHelmet() {
		// TODO Auto-generated method stub
		return null;
	}

	private Apparel createApparel() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
