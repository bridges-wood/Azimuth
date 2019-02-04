import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
			Collections.emptyList(), tempA, -1);

	public Game() {
		this.init();
	}

	public void init() {
		Usable bed = new Usable(false, "Bed", "It is your bed.", null, null, 400, 0, "You use the bed and feel rested.",
				"", Collections.emptyMap());
		Object key = new Object(true, "Key", "It is a key.", Collections.emptyList(), new String[0], 1, 0);
		Map<Object, String> uses = new HashMap<Object, String>();
		uses.put(key, "You manage to unlock the chest.");
		Usable chest = new Usable(false, "Chest", "It is a chest.", Collections.emptyList(), new String[0], 0, 1,
				"You try to open the chest but it doesn't budge.", "Locked", uses);
		String[] combinable = { "Torch" };
		Object battery = new Object(true, "Battery", "It is a battery.", Collections.emptyList(), combinable, 1, 0);
		List<Object> parts = new ArrayList<Object>();
		parts.add(battery);
		uses.remove(key);
		uses.put(battery, "You put the battery into the torch and it flickers to life.");
		Usable torch = new Usable(true, "Torch", "It is a torch.", parts, new String[0], 10, 0,
				"The torch can't be used like this.", "Off", uses);
		List<Object> contents = new ArrayList<Object>();
		contents.add(torch);
		contents.add(bed);
		contents.add(chest);
		contents.add(key);
		Room first = new Room(contents, emptyCharacters,
				"You are in your bedroom. It's rather spartan with the only feature being a single bed against the back wall.",
				1, null, null);
		ArrayList<Room> rooms = new ArrayList<Room>();
		player = Utilities.CreateCharacter(false, true, first);
		player.setCurrentRoom(first);
		fists.setDamage(player.getREPLICAS()[7] * 2);
		// TODO Clean up this method to add rooms, objects and characters separately.
		rooms.add(first);
		setRooms(rooms);
		playGame();
	}

	public void playGame() {
		// Insures that initialised game is saved.
		try {
			Utilities.saveGame(Menu.getCurrentFile(), this, false);
		} catch (IOException e1) {
			System.out.println("Autosave failed.");
		}
		boolean playing = true;
		boolean moved = true;
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
				verbObject = inputArr[i] + " ";
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
			}
		}

	}

	public void save() {
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

	public void drop(Room currentRoom, String verbObject) {
		boolean success = false;
		for (int i = 0; i < player.getInventory().size(); i++) {
			Object current = player.getInventory().get(i);
			if (current.getName().toLowerCase().equals(verbObject)) {
				currentRoom.getContents().add(current);
				player.getInventory().remove(current);
				success = true;
			}
		}
		if (success)
			System.out.println("You drop the " + verbObject + ".");
	}

	public void get(Room currentRoom, String verbObject) {
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
																						// get sub-objects.
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

	public void use(String[] inputArr, Room currentRoom) {
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
					objObj.getParts().add(actObj);
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
						// TODO create combat method. Allow player to engage other characters.
						successful = true;
					}
				}
				for (int i = 0; i < currentRoom.getContents().size(); i++) {
					if (objStr.equals(currentRoom.getContents().get(i).getName().toLowerCase())) {
						// TODO allow the player to damage objects in the room.
						successful = true;
					}
				}
			} else if (!successful && actStr.equals(player.getOffHand().getName().toLowerCase())) {
				System.out.println("Got this far A");
				if (objStr.equals("")) {
					if (player.getOffHand().getClass().getSimpleName().equals("Usable")) {
						Usable current = (Usable) player.getOffHand();
						for (int i = 0; i < current.getParts().size(); i++) {
							System.out.println(current.getObjectState());
							// current.setObjectState("Off");
							// TODO solution: create a public usable variable that is updated when the
							// player equips an object. All references are made to that, circumventing
							// casting problems.
							switch (current.getParts().get(i).getName()) {
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
					Set<Object> keys = target.getSpecificUses().keySet();
					Object key = nullObject;
					for (Object i : keys) {
						if (i.equals(player.getOffHand()))
							key = i;
						// TODO check if the object can be used with the object in the player's offhand.
					}
					if (location != -1 && !key.equals(nullObject)) {
						System.out.println(target.getSpecificUses().get(key));
						Class<? extends Object> temp = currentRoom.getContents().get(location).getClass();
						String absoluteName = "Object";
						try {
							Class<?> tempClass = temp.getClassLoader()
									.loadClass(currentRoom.getContents().get(location).getClass().getSimpleName());
							java.lang.Object tempObj = tempClass.cast(currentRoom.getContents().get(location));
							absoluteName = tempObj.getClass().getSimpleName();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						switch (absoluteName) {
						case ("Door"):
						case ("Container"):
						case ("Puzzle"):
						case ("Remains"):
						case ("Terminal"):
						case ("Player"):
						}
						successful = true;
						// TODO create this method to allow objects to just be 'used'.
					}
				}
			}
		} else if (!successful) {

			int location = -1;
			for (int i = 0; i < currentRoom.getContents().size(); i++) {
				if (currentRoom.getContents().get(i).getName().toLowerCase().equals(actStr))
					location = i;
			}
			Class<? extends Object> temp = currentRoom.getContents().get(location).getClass();
			String absoluteName = "Object";
			try {
				Class<?> tempClass = temp.getClassLoader()
						.loadClass(currentRoom.getContents().get(location).getClass().getSimpleName());
				java.lang.Object tempObj = tempClass.cast(currentRoom.getContents().get(location));
				absoluteName = tempObj.getClass().getSimpleName();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Usable used = (Usable) currentRoom.getContents().get(location);
			System.out.println(used.getGenericUse());
			switch (absoluteName) {
			// TODO for different types of object, do different things.
			}
		}
	}

	public void equip(String verbObject) {
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

	public void examine(Room currentRoom, String verbObject) {
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
					Utilities.printSubObjects(current);
					found = true;
					break;
				} else if (current.getParts() != null && current.getParts().size() > 0) {
					for (i = 0; i < current.getParts().size(); i++) {
						Object currentPart = current.getParts().get(i);
						if (currentPart.getName().toLowerCase().equals(verbObject)) {
							System.out.println(currentPart.getDescription());
							found = true;
							break;
						}
					}
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

	public boolean go(Room currentRoom, String verbObject) {
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
