import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements Serializable {
	private static final long serialVersionUID = 557779407485802525L;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private Player player;
	List<Character> emptyCharacters = new ArrayList<Character>();
	List<Object> emptyObjects = new ArrayList<Object>();
	Object nullObject = new Object(false, "", "", Collections.emptyList(), new String[0], 0, 0);
	Ammunition tempA = new Ammunition("Fists", Collections.emptyList(), 0, 0, 0, "Kinetic", "");
	Weapon fists = new Weapon(false, "Fists", 0, 0, "Your fists are slightly bruised from a previous fight",
			Collections.emptyList(), new String[0], "Melee", player.getREPLICAS()[7] * 2, 1, -1, -1, (float) 1.0,
			(float) 0.05, (float) 2.0, Collections.emptyList(), tempA, -1);

	public Game() {
		this.init();
	}

	public void init() {
		Object bed = new Object(false, "Bed", "It is your bed.", null, null, 400, 0);
		String[] combinable = { "Torch" };
		Object battery = new Object(true, "Battery", "It is a battery.", Collections.emptyList(), combinable, 1, 0);
		List<Object> parts = new ArrayList<Object>();
		parts.add(battery);
		Object torch = new Object(true, "Torch", "It is a torch.", parts, new String[0], 10, 0);
		List<Object> contents = new ArrayList<Object>();
		contents.add(torch);
		contents.add(bed);
		Room first = new Room(contents, emptyCharacters,
				"You are in your bedroom. It's rather spartan with the only feature being a single bed against the back wall.",
				1, null, null);
		player = Utilities.CreateCharacter(false, true, first);
		player.setCurrentRoom(first);
		ArrayList<Room> rooms = new ArrayList<Room>();

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
				Menu.showSaves();
				System.out.println("Input the names of the file you wish to save in: ");
				String name = Utilities.StrInput();
				File file = new File("SaveGames/" + name + ".gme");
				try {
					Utilities.saveGame(file, this, true);
				} catch (IOException e) {
					System.out.println("Save failed.");
				}
				break;

			case ("drop"):
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
				break;

			case ("get"):
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
							System.out.println(j + current.getParts().get(j).getName());
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
								System.out.println("Got this far");
								Object currentPart = current.getParts().get(j);
								if (currentPart.getName().toLowerCase().equals(verbObject)
										&& currentPart.isInventoriable()
										&& (player.getInventorySize() > player.getTotalInventoryWeight()
												+ currentPart.getWeight())) {
									player.addToInventory(currentPart);
									current.getParts().remove(currentPart); // Removes the object from the parts of the
									// meta-object.
									System.out.println("You remove the " + currentPart.getName().toLowerCase()
											+ " from the " + current.getName().toLowerCase() + ".");
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
					break;
				}
				break;

			case ("use"):
				String actStr = "", objStr = "";
				boolean complete = false, successful = false;
				for (int i = 1; i < inputArr.length; i++) {
					if (!complete && !inputArr[i].equals("on")) {
						actStr = actStr + inputArr[i] + " ";
					} else if (!complete) {
						complete = true;
					} else if (complete && !inputArr[i].equals("on")) {
						objStr = objStr + inputArr[i] + " ";
					}
				}
				System.out.println(actStr + " " + objStr);
				actStr = actStr.trim();
				objStr = objStr.trim();
				Object actObj = nullObject, objObj = nullObject;
				for (int i = 0; i < player.getInventory().size(); i++) {
					if (player.getInventory().get(i).getName().toLowerCase().equals(actStr)) {
						actObj = player.getInventory().get(i);
					} else if (player.getInventory().get(i).getName().toLowerCase().equals(objStr)) {
						objObj = player.getInventory().get(i);
					}
				}
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
				if(!successful) {
					if(actStr.equals(player.getEquipped().getName().toLowerCase())) {
						for(int i = 0; i < currentRoom.getCharacters().size(); i++) {
							if(objStr.equals(currentRoom.getCharacters().get(i).getName().toLowerCase())) {
								//TODO create combat method. Allow player to engage other characters.
								successful = true;
							}
						}
						for(int i = 0; i < currentRoom.getContents().size(); i++){
							if(objStr.equals(currentRoom.getContents().get(i).getName().toLowerCase())) {
								//TODO allow the player to damage objects in the room.
								successful = true;
							}
						}
					} else if(actStr.equals(player.getOffHand().getName().toLowerCase())) {
						if(!objStr.equals("")) {
							//TODO allow players to use certain objects on certain others like keys on doors.
						} else {
							//System.out.println(player.getOffHand().getGenericUse()); TODO create this method to allow objects to just be 'used'.
						}
					}
				}
				break;
				
				
			case ("equip"):
				found = false;
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
				break;

			case ("examine"):
				found = false;
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
					System.out.println("> " + player.getOffHand().getName());
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
					break;
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
				break;

			case ("go"):
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
					break;
				}
			}

		}

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
