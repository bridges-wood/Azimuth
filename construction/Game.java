package construction;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import apparel.Apparel;
import object.Container;
import object.Door;
import object.Key;
import object.Object;
import object.Terminal;
import object.Usable;
import weapon.Ammunition;
import weapon.Bullet;
import weapon.Magazine;
import weapon.Weapon;
import construction.Menu;

public class Game implements Serializable {
	private static final long serialVersionUID = 557779407485802525L;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private Player player;
	List<Character> emptyCharacters = new ArrayList<Character>();
	List<Object> emptyObjects = new ArrayList<Object>();
	Object nullObject = new Object(false, "", "", Collections.emptyList(), new String[0], 0, 0);
	Ammunition tempA = new Ammunition("Fists", Collections.emptyList(), 0, 0, 0, "Kinetic", "");
	Weapon fists = new Weapon(false, "Fists", 0, 0, "Your fists are slightly bruised from a previous fight",
			Collections.emptyList(), new String[1], "Melee", 0, 1, -1, -1, (float) 1.0, (float) 0.05, (float) 2.0,
			Collections.emptyList(), tempA);
	PublicSettings settings = new PublicSettings();
	public Game() {
		Usable bed = new Usable(false, "Bed", "It is your bed.", null, null, 400, 0, "You use the bed and feel rested.",
				"", Collections.emptyMap());
		Key key = new Key("Key", "It is a key.", 10);
		Map<Object, String> uses = new HashMap<Object, String>();
		uses.put(key, "You manage to unlock the chest.");
		Key[] keys = { key };
		String[] combinable = { "Torch" };
		Object battery = new Object(true, "Battery", "It is a battery.", Collections.emptyList(), combinable, 1, 0);
		List<Object> parts = new ArrayList<Object>();
		parts.add(battery);
		uses.remove(key);
		uses.put(battery, "You put the battery into the torch and it flickers to life.");
		Usable torch = new Usable(true, "Torch", "It is a torch.", parts, new String[0], 10, 0,
				"The torch can't be used like this.", "Off", uses);
		List<Object> chestContents = new ArrayList<Object>();
		chestContents.add(torch);
		Container chest = new Container("Chest", "It is a chest.", chestContents, 200, 100, uses, true, keys);
		boolean[] locked = { true };
		String[] usernames = { "user" };
		String[] passwords = { "pass" };
		Terminal terminal = new Terminal("My terminal", "It is your terminal.", 5, locked, new String[0], new String[0],
				usernames, passwords);
		List<Object> contents = new ArrayList<Object>();
		contents.add(terminal);
		contents.add(bed);
		contents.add(chest);
		contents.add(key);
		Bullet ninemmA = new Bullet(Collections.emptyList(), 2, 0, 9);
		Weapon ninemm = new Weapon(true, "9mm pistol", 1, 20, "A lightweight, compact 9mm pistol.",
				Collections.emptyList(), new String[] { "9mm" }, "Kinetic", 10, 1, 25, 5, (float) 0.75, (float) 0.15,
				(float) 2.0, Collections.emptyList(), ninemmA);
		contents.add(ninemm);
		Magazine ninemmMag = new Magazine("9mm Magazine", null, 10, ninemmA);
		contents.add(ninemmMag);
		Room first = new Room(contents, emptyCharacters,
				"You are in your bedroom. It's rather spartan with the only feature being a single bed against the back wall.",
				null, null);
		ArrayList<Room> rooms = new ArrayList<Room>();
		player = Utilities.CreateCharacter(false, true, first);
		player.setCurrentRoom(first);
		fists.setDamage(player.getREPLICAS()[7] * 2);
		// TODO Clean up this method to add rooms, objects and characters separately.
		rooms.add(first);
		setRooms(rooms);
		playGame();
	}

	void playGame() {
		// Insures that initialised game is saved.
		try {
			Utilities.saveGame(Menu.getCurrentFile(), this, false);
		} catch (IOException e1) {
			System.out.println("Autosave failed.");
		}
		boolean playing = true;
		boolean moved = true;
		Utilities.playAudio(new File("StatRes/Cold-Moon.wav"), 5);
		while (playing) {
			Room currentRoom = player.getCurrentRoom(); // Impermanent room reference.
			if (moved) {
				Utilities.describeRoom(currentRoom); // If the player has moved they are given the description of the
														// new room that they are in.
				moved = false;
			}
			String userIn = Utilities.StrInput();
			String[] inputArr = userIn.split(" ");
			String verb = inputArr[0];
			String verbObject = "";
			for (int i = 1; i < inputArr.length; i++) {
				verbObject = verbObject + inputArr[i] + " ";
			}
			verbObject = verbObject.toLowerCase().trim(); // Input handling.

			switch (verb) {
			case ("quit"):
				Utilities.testQuit();
				break;
			case ("save"):
				save();
				break;
			case ("drop"):
				drop(currentRoom, verbObject);
				break;
			case ("get"):
				get(currentRoom, verbObject);
				break;
			case ("use"):
				use(inputArr, currentRoom);
				break;
			case ("equip"):
				equip(verbObject);
				break;
			case ("examine"):
				examine(currentRoom, verbObject);
				break;
			case ("go"):
				go(currentRoom, verbObject);
				break;
			case ("help"):
				getHelp(verbObject);
				break;
			case ("load"):
				load(currentRoom, verbObject);
				break;
			case ("settings"):
				Utilities.options();
			break;
			}
		}

	}

	private void load(Room currentRoom, String verbObject) {
		Weapon finalWeapon = fists;
		int location = -2;
		if (player.getEquipped().getName().toLowerCase().equals(verbObject)) {
			finalWeapon = player.getEquipped();
			location = -1;
		}
		if (location == -2) {
			for (int i = 0; i < player.getInventory().size(); i++) {
				if (player.getInventory().get(i).getClass().getSimpleName().equals("Weapon")) {
					Weapon tempWeapon = (Weapon) player.getInventory().get(i);
					if (tempWeapon.getName().toLowerCase().equals(verbObject)) {
						location = i;
						finalWeapon = tempWeapon;
						break;
					}
				}
			}
		}
		for (int i = 0; i < player.getInventory().size(); i++) {
			if (player.getInventory().get(i).getClass().getSimpleName().equals("Magazine")) {
				Magazine tempMagazine = (Magazine) player.getInventory().get(i);
				if (tempMagazine.getAmmunition().equals(finalWeapon.getAmmunition())) {
					if (location == -1) {
						List<Object> temp = player.getEquipped().getParts();
						temp.add(tempMagazine);
						player.getEquipped().setParts(temp);
						player.getInventory().remove(tempMagazine);

					} else {
						List<Object> temp = new ArrayList<Object>(player.getInventory().get(location).getParts());
						temp.add(tempMagazine);
						player.getInventory().get(location).setParts(temp);
						player.getInventory().remove(tempMagazine);
					}
					System.out.print("You load the ");
					for (Object u : finalWeapon.getParts()) {
						System.out.println(u.getName());
					}
					break;
				}
			}
		}
	}

	private void getHelp(String verbObject) {
		switch (verbObject) {
		case ("quit"):
			System.out.println("[Usage: quit] Quits the game.");
			break;
		case ("save"):
			System.out.println(
					"[Usage: save] Saves the game. Prompts the user for an existing file to save the game in.");
			break;
		case ("drop"):
			System.out.println("[Usage: drop <object>] Allows the player to drop the specified object.");
			break;
		case ("get"):
			System.out.println("[Usage: get <object>] Allows the player to pick up the specified object.");
			break;
		case ("use"):
			System.out.println(
					"[Usage: use <object> on <object> | use <object>] Allows the player to use the specified object.");
			break;
		case ("equip"):
			System.out.println("[Usage: equip <object>] Allows the player to equip the specified object.");
			break;
		case ("examine"):
			System.out.println("[Usage: examine <object>] Allows the player to examine the specified object.");
			break;
		case ("go"):
			System.out.println("[Usage: go <direction>] Allows the player to move in the specified direction.");
			break;
		case ("help"):
			System.out.println("[Usage: help <verb>] Tells the player how to use the specified verb.");
			break;
		default:
			System.out.println("Verbs:");
			System.out.println("quit");
			System.out.println("save");
			System.out.println("drop");
			System.out.println("get");
			System.out.println("use");
			System.out.println("equip");
			System.out.println("examine");
			System.out.println("go");
			System.out.println("help");
			break;
		}

	}

	private void save() {
		Menu.showSaves();
		System.out.println("Input the names of the file you wish to save in: ");
		String name = Utilities.StrInput();
		File file = new File("SaveGames/" + name + ".gme");
		try {
			Utilities.saveGame(file, this, true);
		} catch (IOException e) {
			System.out.println("Save failed.");
		}
	}

	private void drop(Room currentRoom, String verbObject) {
		boolean success = false;
		for (int i = 0; i < player.getInventory().size(); i++) {
			Object current = player.getInventory().get(i);
			if (current.getName().toLowerCase().equals(verbObject)) {
				currentRoom.getContents().add(current);
				player.getInventory().remove(current);
				success = true;
			}
		}
		if (!success) {
			if (player.getOffHand().getName().toLowerCase().equals(verbObject)) {
				currentRoom.getContents().add(player.getOffHand());
				player.setOffHand(nullObject);
				success = true;
			} else if (player.getEquipped().getName().toLowerCase().equals(verbObject)
					&& !(player.getEquipped().getName().equals("Fists"))) {
				currentRoom.getContents().add(player.getEquipped());
				player.setEquipped(fists);
				success = true;
			}
		}
		if (success)
			System.out.println("You drop the " + verbObject + ".");
	}

	private void get(Room currentRoom, String verbObject) {
		boolean found = false;
		for (int i = 0; i < currentRoom.getContents().size(); i++) { // Checks if player wants to get
																		// meta-objects in room.
			Object current = currentRoom.getContents().get(i);
			if (current.getName().toLowerCase().equals(verbObject) && current.isInventoriable()
					&& (player.getInventorySize() > player.getTotalInventoryWeight() + current.getWeight())) {
				player.addToInventory(current); // If the object is what they are trying to get, and it is able
												// to be picked up, they can, as long as it isn't too heavy.
				currentRoom.getContents().remove(current); // Removes the object from the room.
				System.out.println("You pick the " + current.getName().toLowerCase() + " up.");
				found = true;
				break;
			} else if (current.getParts() != null && current.getParts().size() > 0) { // Checks if they want to
				boolean allowed = true;
				if (current.getClass().getSimpleName().equals("Container")) {
					Container container = (Container) current;
					allowed = !container.isLocked();// get sub-objects.
				}
				if (allowed) {
					for (int j = 0; j < current.getParts().size(); j++) {
						Object currentPart = current.getParts().get(j);
						if (currentPart.getName().toLowerCase().equals(verbObject) && currentPart.isInventoriable()
								&& (player.getInventorySize() > player.getTotalInventoryWeight()
										+ currentPart.getWeight())) {
							player.addToInventory(currentPart);
							current.getParts().remove(currentPart); // Removes the object from the parts of the
																	// meta-object.
							System.out.println("You pick the " + currentPart.getName().toLowerCase() + " up.");
							found = true;
							break;
						}
					}
				}
			}
			if (found)
				break;
		}
		if (!found) {
			for (int i = 0; i < player.getInventory().size(); i++) {
				Object current = player.getInventory().get(i);
				if (current.getParts() != null && current.getParts().size() > 0) { // Checks if they want to
					// get sub-objects.
					for (int j = 0; j < current.getParts().size(); j++) {
						Object currentPart = current.getParts().get(j);
						if (currentPart.getName().toLowerCase().equals(verbObject) && currentPart.isInventoriable()
								&& (player.getInventorySize() > player.getTotalInventoryWeight()
										+ currentPart.getWeight())) {
							player.addToInventory(currentPart);
							current.getParts().remove(currentPart); // Removes the object from the parts of the
							// meta-object.
							System.out.println("You remove the " + currentPart.getName().toLowerCase() + " from the "
									+ current.getName().toLowerCase() + ".");
							found = true;
							break;
						}
					}
				}

			}
		}
		if (!found) {
			if (player.getOffHand().getName().toLowerCase().equals(verbObject)) {
				player.getInventory().add(player.getOffHand());
				player.setOffHand(nullObject);
				System.out.println("You put the " + verbObject + " away.");
				found = true;
			}
		}
		if (!found) {
			if (player.getEquipped().getName().toLowerCase().equals(verbObject)) {
				player.getInventory().add(player.getEquipped());
				player.setEquipped(fists);
				System.out.println("You holster your weapon once again.");
				found = true;
			}
		}
		if (!found) {
			System.out.println("You couldn't pick that up.");
		}
	}

	private void use(String[] inputArr, Room currentRoom) {
		String actStr = "", objStr = "";
		boolean complete = false, successful = false;
		for (int i = 1; i < inputArr.length; i++) { // Checks for everything in the input.
			if (!complete && !inputArr[i].equals("on")) {
				actStr = actStr + inputArr[i] + " "; // If strings are before the on they are added to actStr.
			} else if (!complete) {
				complete = true;
			} else if (complete && !inputArr[i].equals("on")) {
				objStr = objStr + inputArr[i] + " "; // If they are after the on, they are added to objStr.
			}
		}
		actStr = actStr.trim();
		objStr = objStr.trim();
		// ----
		Object actObj = nullObject, objObj = nullObject;
		for (int i = 0; i < player.getInventory().size(); i++) { // For all the objects in the player's inventory, if
																	// the names match the handled input they are set as
																	// the objects.
			if (player.getInventory().get(i).getName().toLowerCase().equals(actStr)) {
				actObj = player.getInventory().get(i);
			} else if (player.getInventory().get(i).getName().toLowerCase().equals(objStr)) {
				objObj = player.getInventory().get(i);
			}
		}
		// ----
		if (!actObj.equals(nullObject) && !actObj.getCombinable().equals(new String[0])
				&& actObj.getCombinable().length > 0) {
			for (int i = 0; i < actObj.getCombinable().length; i++) {
				if (!objObj.equals(nullObject) && actObj.getCombinable()[i].toLowerCase().equals(objStr)) {
					objObj.getParts().add(actObj); // Adds the object to the parts of the other respective object.
					player.getInventory().remove(actObj);
					successful = true;
				}
			}
		}
		if (successful)
			System.out.println(actStr.substring(0, 1).toUpperCase() + actStr.substring(1) + " and " + objStr
					+ " successfully combined.");
		// ----
		if (!successful) {
			if (actStr.equals(player.getEquipped().getName().toLowerCase())) {
				for (int i = 0; i < currentRoom.getCharacters().size(); i++) {
					if (objStr.equals(currentRoom.getCharacters().get(i).getName().toLowerCase())) {
						//TODO sort for agressive npcs...
						List<Character> hostiles = new ArrayList<Character>();
						combatHandler();
						successful = true;
					}
				}
				for (int i = 0; i < currentRoom.getContents().size(); i++) {
					if (objStr.equals(currentRoom.getContents().get(i).getName().toLowerCase())) {
						// If the player is trying to use the weapon on an object in the room...
						// TODO allow the player to damage objects in the room.
						successful = true;
					}
				}
				// ----
			} else if (!successful && actStr.equals(player.getOffHand().getName().toLowerCase())) {
				if (objStr.equals("")) {
					if (player.getOffHand().getClass().getSimpleName().equals("Usable")) {
						Usable current = (Usable) player.getOffHand(); // If the object in the player's off-hand is
																		// actually usable...
						for (int i = 0; i < current.getParts().size(); i++) {
							switch (current.getParts().get(i).getName()) { // If it contains a battery, the device can
																			// be turned on or off.
							case ("Battery"):
								if (current.getObjectState().equals("Off")) {
									System.out.println("You turn the torch on.");
									current.setObjectState("On");
									successful = true;
									break;
								} else {
									System.out.println("You turn the torch off.");
									current.setObjectState("Off");
									successful = true;
									break;
								}

							}
							// TODO Capacity to add more variety to usable off-hand objects.
						}
					}
				} else {
					Usable target = null;
					int location = -1;
					for (int i = 0; i < currentRoom.getContents().size(); i++) {
						if (objStr.equals(currentRoom.getContents().get(i).getName().toLowerCase())) {
							target = (Usable) currentRoom.getContents().get(i);
							location = i;
						}
					}
					if (currentRoom.getContents().get(location).getClass().getSimpleName().equals("Usable")) {
						Object key = nullObject;
						Set<Object> keys = target.getSpecificUses().keySet();
						for (Object i : keys) {
							if (i.equals(player.getOffHand()))
								key = i; // Scans the dictionary of keys and values of the target object. Key is the
											// object that the player has in their off hand. This will determine if the
											// player can actually use their off hand.
						}
						if (!key.equals(nullObject)) {
							System.out.println(target.getSpecificUses().get(key));
						}
					}
					Class<? extends Object> temp = currentRoom.getContents().get(location).getClass();
					String absoluteName = "Object";
					try {
						Class<?> tempClass = temp.getClassLoader()
								.loadClass(currentRoom.getContents().get(location).getClass().getSimpleName());
						java.lang.Object tempObj = tempClass.cast(currentRoom.getContents().get(location));
						absoluteName = tempObj.getClass().getSimpleName();
					} catch (ClassNotFoundException e) {
						System.out.println("This is not possible.");
					}
					switch (absoluteName) {
					case ("Door"):
						Door door = (Door) currentRoom.getContents().get(location);
						for (int i = 0; i < door.getWorkingKeys().length; i++) {
							if (door.getWorkingKeys()[i].equals((Key) player.getOffHand())) {
								door.setLocked(false);
								player.setOffHand(nullObject);
							}
						}
						successful = true;
						break;
					case ("Container"):
						Container container = (Container) currentRoom.getContents().get(location);
						for (int i = 0; i < container.getWorkingKeys().length; i++) {
							if (container.getWorkingKeys()[i].equals(player.getOffHand())) {
								container.setLocked(false);
								player.setOffHand(nullObject);
								System.out.println("You unlock the " + container.getName().toLowerCase() + ".");
							}
						}
						successful = true;
						break;
					case ("Puzzle"):
						successful = true;
						break;
					case ("Remains"):
						successful = true;
						break;
					case ("Terminal"):
						successful = true;
						break;
					case ("Player"):
						successful = true;
						break;

					}
				}
			} else if (!successful) {
				int location = -1;
				String absoluteName = "";
				for (int i = 0; i < currentRoom.getContents().size(); i++) {
					if (currentRoom.getContents().get(i).getName().toLowerCase().equals(actStr))
						location = i;
				}
				if (location != -1)
					absoluteName = currentRoom.getContents().get(location).getClass().getSimpleName();
				switch (absoluteName) {
				// Generic use of objects in the room.
				case ("Terminal"):
					Terminal terminal = (Terminal) currentRoom.getContents().get(location);
					terminalHandler(terminal);
					break;
				case ("Usable"):
					Usable usable = (Usable) currentRoom.getContents().get(location);
					System.out.println(usable.getGenericUse());
					break;
				}

			}
		}
	}

	private void combatHandler() {
		// TODO Auto-generated method stub
		
	}

	private void terminalHandler(Terminal terminal) {
		System.out.println("\n" + terminal.getName());
		System.out.println("1. Login");
		System.out.println("2. ###HACK###");
		// TODO determine how to control the output of what options are available to the
		// player. If the terminal has been successfully hacked, the terminal should
		// just unlock straight away etc.
		int choice = -1;
		while (choice > 2 || choice < 1) {
			try {
				choice = Integer.parseInt(Utilities.StrInput());
			} catch (NumberFormatException e) {
				System.out.println("Please input a number to continue.");
			}
		}
		switch (choice) {
		case 1:
			System.out.print("Username: ");
			String username = Utilities.StrInput();
			System.out.print("Password: ");
			String password = Utilities.StrInput();
			terminal.unlock(username, password);
			break;
		case 2:
			terminal.hack(terminal.getDifficulty(), player.getSkills().get(1).getLevel());
			break;
		}
		if (!terminal.isLocked(0)) {
			if (!(terminal.getLogs().length == 0)) {
				for (int i = 0; i < terminal.getLogNames().length; i++) {
					System.out.println(terminal.getLogNames()[i]);
					System.out.println(terminal.getLogs()[i]);
				}
			}
		}
	}

	private void equip(String verbObject) {
		boolean found = false;
		for (int i = 0; i < player.getInventory().size(); i++) {
			Object current = player.getInventory().get(i);
			if (current.getName().toLowerCase().equals(verbObject)) {
				if (current.getClass().getSimpleName().equals("Weapon")) {
					Weapon tempW = (Weapon) player.getInventory().get(i);
					player.getInventory().add(player.getEquipped());
					player.setEquipped(tempW);
					player.getInventory().remove(tempW);
					found = true;
				} else if (current.getClass().getSimpleName().equals("Apparel")) {
					Apparel tempA = (Apparel) player.getInventory().get(i);
					if (tempA.isUnderClothes()) {
						player.getInventory().add(player.getUnderClothes());
						player.setUnderClothes(tempA);
						found = true;
					} else {
						player.getInventory().add(player.getArmour()[tempA.getArea()]);
						Apparel[] tempAr = player.getArmour();
						tempAr[tempA.getArea()] = tempA;
						player.setArmour(tempAr);
						player.getInventory().remove(tempA);
						found = true;
					}
				} else {
					if (!player.getOffHand().getName().equals(""))
						player.getInventory().add(player.getOffHand());
					player.setOffHand(current);
					player.getInventory().remove(current);
					found = true;
				}
			}
		}
		if (found)
			System.out.println("Successfully equipped " + verbObject + ".");
	}

	private void examine(Room currentRoom, String verbObject) {
		boolean found = false;
		if (verbObject.equals("room")) {
			Utilities.describeRoom(currentRoom);
			found = true;
		} else if (verbObject.equals("equipped")) {
			System.out.println("You have equipped: ");
			Apparel[] armour = player.getArmour();
			for (int i = 0; i < 6; i++) {
				Apparel current = armour[i];
				if (!current.getName().equals("")) {
					System.out.print("> ");
					if (current.getName().toLowerCase().startsWith("[aeiou]")) {
						System.out.print("An ");
					} else {
						System.out.print("A ");
					}
					System.out.println(current.getName().toLowerCase());
				}
			}
			if (!player.getEquipped().getName().equals("Fists")) {
				System.out.println("> " + player.getEquipped().getName());
			}
			System.out.println("> " + player.getUnderClothes().getName());
			if (!player.getOffHand().getName().equals("")) {
				System.out.println("> " + player.getOffHand().getName());
			}
			found = true;
		} else if (verbObject.equals("inventory")) {
			if (player.getInventory().size() > 0) {
				System.out.println("In your inventory you have: ");
				for (int i = 0; i < player.getInventory().size(); i++) {
					String currentName = player.getInventory().get(i).getName();
					System.out.print("> ");
					if (currentName.startsWith("[aeiou]")) {
						System.out.print("An ");
					} else {
						System.out.print("A ");
					}
					System.out.println(currentName.toLowerCase());
					// TODO case for if amount of ammunition in inventory.
				}
			} else {
				System.out.println("Your inventory is empty.");
			}
			found = true;
		} else {
			for (int i = 0; i < currentRoom.getContents().size(); i++) {
				Object current = currentRoom.getContents().get(i);
				if (current.getName().toLowerCase().equals(verbObject)) {
					System.out.println(current.getDescription());
					if (current.getClass().getSimpleName().equals("Container")) {
						Container container = (Container) current;
						if (!container.isLocked()) {
							if (container.getParts().size() > 0) {
								Utilities.printSubObjects(current);
							} else {
								System.out.println("It is empty.");
							}
						} else {
							System.out.println("It is locked.");
						}
					} else {
						Utilities.printSubObjects(current);
					}
					found = true;
					break;
				} else if (found == false && current.getParts() != null && current.getParts().size() > 0) {
					for (int j = 0; j < current.getParts().size(); j++) {
						Object currentPart = current.getParts().get(j);
						if (currentPart.getName().toLowerCase().equals(verbObject)) {
							System.out.println(currentPart.getDescription());
							found = true;
							break;
						}
					}
					continue;
				}
			}
			for (int i = 0; i < player.getInventory().size(); i++) {
				Object current = player.getInventory().get(i);
				if (current.getName().toLowerCase().equals(verbObject)) {
					System.out.println(current.getDescription());
					Utilities.printSubObjects(current);
					found = true;
					break;
				}
			}
		}
		if (!found)
			System.out.println("You couldn't find that to look at.");
	}

	private boolean go(Room currentRoom, String verbObject) {
		boolean moved = false;
		switch (verbObject) {
		case ("up"):
			if (currentRoom.getAccess()[0]) {
				player.setCurrentRoom(currentRoom.getRooms()[0]);
				moved = true;
			}
			break;
		case ("down"):
			if (currentRoom.getAccess()[1]) {
				player.setCurrentRoom(currentRoom.getRooms()[1]);
				moved = true;
			}
			break;
		case ("left"):
			if (currentRoom.getAccess()[2]) {
				player.setCurrentRoom(currentRoom.getRooms()[2]);
				moved = true;
			}
			break;
		case ("right"):
			if (currentRoom.getAccess()[3]) {
				player.setCurrentRoom(currentRoom.getRooms()[3]);
				moved = true;
			}
			break;
		case ("forwards"):
			if (currentRoom.getAccess()[4]) {
				player.setCurrentRoom(currentRoom.getRooms()[4]);
				moved = true;
			}
			break;
		case ("backwards"):
			if (currentRoom.getAccess()[5]) {
				player.setCurrentRoom(currentRoom.getRooms()[5]);
				moved = true;
			}
			break;
		case ("in"):
			if (currentRoom.getAccess()[6]) {
				player.setCurrentRoom(currentRoom.getRooms()[6]);
				moved = true;
			}
			break;
		case ("out"):
			if (currentRoom.getAccess()[7]) {
				player.setCurrentRoom(currentRoom.getRooms()[7]);
				moved = true;
			}
		}
		return moved;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
