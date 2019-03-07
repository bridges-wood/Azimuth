package construction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import apparel.Apparel;
import object.Container;
import object.Key;
import object.Object;
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
	public List<object.Object> objects = new ArrayList<object.Object>();
	public List<Room> rooms = new ArrayList<Room>();

	public static void main(String[] args) {
		Map running = new Map();
		running.createMap();
	}

	private void createMap() {
		boolean creating = true;

		while (creating) {
			System.out.print("Type: ");
			String Class = Utilities.StrInput().toLowerCase();
			switch (Class) {
			case ("room"):
				rooms.add(createRoom());
				break;
			case ("existing"):
				// TODO handle adding precreated room to rooms. Allow minor modification
				break;
			case ("save"):
				save(rooms, "room");
				break;
			case ("load"):
				rooms = (List<Room>) load();
			case ("quit"):
				creating = false;
				break;
			}
		}
	}

	private Room createRoom() {
		System.out.print("Name :");
		String name = Utilities.StrInput();
		System.out.println("Contents: ");
		List<object.Object> contents = createSubObjects();
		System.out.println("Characters: ");
		List<construction.Character> characters = createCharacters();
		System.out.println("Description: ");
		String description = Utilities.StrInput();
		System.out.println("Rooms: ");
		Room[] connections = new Room[8];
		Arrays.fill(connections, null);
		for (Room room : rooms) {
			System.out.println(room.getName());
			if (Utilities.StrInput().equals("y")) {
				System.out.print("Location: ");
				try {
					connections[Integer.parseInt(Utilities.StrInput())] = room;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Access: ");
		boolean[] access = new boolean[8];
		Arrays.fill(access, false);
		boolean empty = true;
		for (Room ob : connections) {
			if (ob != null) {
				empty = false;
				break;
			}
		}
		if (!empty) {
			for (int i = 0; i < access.length; i++) {
				System.out.println(connections[i].getDescription());
				if (Utilities.StrInput().equals("true")) {
					access[i] = true;
				}
			}
		}
		return new Room(name, contents, characters, description, connections, access);
	}

	private List<Character> createCharacters() {
		System.out.print("Name: ");
		System.out.print("Armour: ");
		System.out.print("Name: ");
		System.out.print("Name: ");
		System.out.print("Name: ");
		return null;
	}

	public List<object.Object> createSubObjects() {
		boolean creating = true;
		List<object.Object> toReturn = new ArrayList<object.Object>();
		while (creating) {
			System.out.print("Object: ");
			String Class = Utilities.StrInput().toLowerCase();
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
			case ("save"):
				save(objects, "object");
				break;
			case ("load"):
				objects = (List<object.Object>) load();
			case ("quit"):
				creating = false;
				break;
			}
		}
		return toReturn;
	}

	private List<?> load() {
		List<?> existing = Collections.emptyList();
		try {
			FileInputStream fileStream = new FileInputStream(new File("StatRes/ObjectRepository.rep"));
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
			existing = (List<?>) objectStream.readObject();
			objectStream.close();
			fileStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existing;
	}

	private void save(List<?> toSave, String type) {
		if (type.equals("object")) {
			try {
				FileOutputStream fileOut = new FileOutputStream(new File("StatRes/ObjectRepository.rep"));
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(toSave);
				out.close();
				fileOut.close();
				System.out.println("Data saved in: StatRes/ObjectRepository.rep");
			} catch (IOException i) {
				i.printStackTrace();
			}
		} else if (type.equals("room")) {
			try {
				FileOutputStream fileOut = new FileOutputStream(new File("StatRes/RoomRepository.rep"));
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(toSave);
				out.close();
				fileOut.close();
				System.out.println("Data saved in: StatRes/RoomRepository.rep");
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
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
		String type = Utilities.StrInput().toLowerCase();
		switch (type) {
		case ("bullet"):
			return (createBullet());
		case ("ferrous"):
			return (createFerrousProjectile());
		case ("plasma"):
			return (createPlasmaCell());
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
		System.out.print("Name: ");
		String name = Utilities.StrInput();
		System.out.print("Calibre");
		Float calibre = Float.parseFloat(Utilities.StrInput());
		return new PlasmaCell(name, calibre);
	}

	private FerrousProjectile createFerrousProjectile() {
		System.out.print("Name: ");
		String name = Utilities.StrInput();
		System.out.print("Calibre");
		Float calibre = Float.parseFloat(Utilities.StrInput());
		return new FerrousProjectile(name, calibre);
	}

	private Usable createUsable() {
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
		//TODO Combinable
		String[] combinable = null;
		
		System.out.print("Generic use statement: ");
		String genericUse = Utilities.StrInput();
		System.out.print("Object state: ");
		String objectState = Utilities.StrInput();
		
		//TODO Specific uses.
		java.util.Map<Object, String> specificUses = null;
		
		return new Usable(inv, name, description, parts, combinable, genericUse, objectState, specificUses);
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
