import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
	private static final long serialVersionUID = 557779407485802525L;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private Player player;
	List<Character> emptyCharacters = new ArrayList<Character>();
	List<Object> emptyObjects = new ArrayList<Object>();

	public Game() {
		this.init();
	}

	public void init() {
		int[] REPLICAS = { 1, 1, 1, 1, 1, 1, 1, 1 };
		// TODO add in a character creator when the player first initialises the game.
		this.player = new Player("Player", 0, 0, 20, 0, null, null, null, null, 20, emptyObjects, REPLICAS, null, null,
				null);
		Object bed = new Object(false, "Bed", "It is your bed.", null, 400, 0);
		Object torch = new Object(true, "Torch", "It is a torch.", null, 10, 0);
		List<Object> contents = new ArrayList<Object>();
		contents.add(torch);
		contents.add(bed);
		Room first = new Room(contents, emptyCharacters, "It is your bedroom", 1, null, null);
		player.setCurrentRoom(first);
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(first);
		setRooms(rooms);
		playGame();
	}

	public void playGame() {
		boolean playing = true;
		boolean moved = true;
		while (playing) {
			if (moved) {
				System.out.println(player.getCurrentRoom().getDescription());
				if (player.getCurrentRoom().getContents().size() > 0) {
					System.out.println("Around you, you can see: ");
					for (int i = 0; i < player.getCurrentRoom().getContents().size(); i++) {
						//Set this to make it say a or an before each object.
						System.out.println("> " + player.getCurrentRoom().getContents().get(i).getName());
					}
					System.out.println();
				} else {
					System.out.println("There is nothing in this room.");
				}
				if (player.getCurrentRoom().getCharacters().size() > 0) {
					System.out.println("There are others with you. You see: ");
					for (int i = 0; i < player.getCurrentRoom().getCharacters().size(); i++) {
						System.out.println(player.getCurrentRoom().getCharacters().get(i).getName());
					}
				} else {
					System.out.println("There is no one else here.");
				}
				moved = false;
			}
			String userIn = Utilities.StrInput();
			String[] inputArr = userIn.split(" ");
			String verb = inputArr[0];
			String verbObject = "";
			for (int i = 1; i < inputArr.length; i++) {
				verbObject = inputArr[i] + " ";
			}
			verbObject = verbObject.toLowerCase().trim();
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
					Utilities.saveGame(file, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case ("get"):
				for (int i = 0; i < player.getCurrentRoom().getContents().size(); i++) { // Checks if player wants to
																							// get meta-objects in a
																							// room.
					Object current = player.getCurrentRoom().getContents().get(i);
					if (current.getName().toLowerCase().equals(verbObject) && current.isInventoriable()
							&& (player.getInventorySize() > player.getTotalInventoryWeight() + current.getWeight())) {
						player.addToInventory(current); // If the object is what they are trying to get, and it is able
														// to be picked up, they can, as long as it isn't too heavy.
						player.getCurrentRoom().getContents().remove(current); // Removes the object from the room.
						System.out.println("You pick the " + current.getName().toLowerCase() + " up.");
						break;
					} else if (current.getParts() != null && current.getParts().size() > 0) { // Checks if they want to
																								// get sub-objects.
						for (i = 0; i < current.getParts().size(); i++) {
							Object currentPart = current.getParts().get(i);
							if (current.getName().toLowerCase().equals(verbObject) && current.isInventoriable()
									&& (player.getInventorySize() > player.getTotalInventoryWeight()
											+ current.getWeight())) {
								player.addToInventory(current);
								current.getParts().remove(currentPart); // Removes the object from the parts of the
																		// meta-object.
								System.out.println("You pick the " + currentPart.getName().toLowerCase() + " up.");
								break;
							}
						}
					}
				}
				break;
			case ("use"):
				// Allows use of keys on doors etc. to solve puzzles or whatever else.
				// TODO Will need a different way of handling input to check if the two objects
				// can be used on each other.
				// This will also require a change to the object class to determine which
				// objects can interact with others as well as possible states like locked and
				// unlocked etc.
				break;
			case ("engage"):
				// TODO Initiates a fight.
				break;
			case ("examine"):
				boolean found = false;
				for (int i = 0; i < player.getCurrentRoom().getContents().size(); i++) {
					Object current = player.getCurrentRoom().getContents().get(i);
					if (current.getName().toLowerCase().equals(verbObject)) {
						System.out.println(current.getDescription());
						found = true;
						break;
					}
					// TODO finish this to allow the player to examine other things like their own
					// weapons etc.
				}
				if (!found)
					System.out.println("You couldn't find that to look at.");
				break;
			case ("go"):
				switch (verbObject) {
				case ("up"):
					if (player.getCurrentRoom().getAccess()[0]) {
						player.setCurrentRoom(player.getCurrentRoom().getRooms()[0]);
						moved = true;
					}
					break;
				case ("down"):
					if (player.getCurrentRoom().getAccess()[1]) {
						player.setCurrentRoom(player.getCurrentRoom().getRooms()[1]);
						moved = true;
					}
					break;
				case ("left"):
					if (player.getCurrentRoom().getAccess()[2]) {
						player.setCurrentRoom(player.getCurrentRoom().getRooms()[2]);
						moved = true;
					}
					break;
				case ("right"):
					if (player.getCurrentRoom().getAccess()[3]) {
						player.setCurrentRoom(player.getCurrentRoom().getRooms()[3]);
						moved = true;
					}
					break;
				case ("forwards"):
					if (player.getCurrentRoom().getAccess()[4]) {
						player.setCurrentRoom(player.getCurrentRoom().getRooms()[4]);
						moved = true;
					}
					break;
				case ("backwards"):
					if (player.getCurrentRoom().getAccess()[5]) {
						player.setCurrentRoom(player.getCurrentRoom().getRooms()[5]);
						moved = true;
					}
					break;
				case ("in"):
					if (player.getCurrentRoom().getAccess()[6]) {
						player.setCurrentRoom(player.getCurrentRoom().getRooms()[6]);
						moved = true;
					}
					break;
				case ("out"):
					if (player.getCurrentRoom().getAccess()[7]) {
						player.setCurrentRoom(player.getCurrentRoom().getRooms()[7]);
						moved = true;
					}
					break;
				}
				// TODO add more verbs.
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
