import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
	private static final long serialVersionUID = 557779407485802525L;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private Player player;

	public Game() {
		this.init();
	}

	public void init() {
		int[] REPLICAS = { 1, 1, 1, 1, 1, 1, 1, 1 };
		// TODO add in a character creator when the player first initialises the game.
		List<Object> emptyInventory = new ArrayList<Object>();
		this.player = new Player("Player", 0, 0, 20, 0, null, null, null, null, 20, emptyInventory, REPLICAS, null, null, null);
		Object bed = new Object(false, "Bed", "It is your bed.", null, 400, 0);
		Object torch = new Object(true, "Torch", "It is a torch.", null, 10, 0);
		List<Object> contents = new ArrayList<Object>();
		contents.add(torch);
		contents.add(bed);
		Room first = new Room(contents, null, "It is your bedroom", 1, null, null);
		player.setCurrentRoom(first);
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(first);
		setRooms(rooms);
		playGame();
	}

	public void playGame() {
		boolean playing = true;
		while (playing) {
			//TODO when player enters a room, display the description, contents and occupying characters.
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
				for (int i = 0; i < player.getCurrentRoom().getContents().size(); i++) { //Checks if player wants to get meta-objects in a room.
					Object current = player.getCurrentRoom().getContents().get(i);
					if (current.getName().toLowerCase().equals(verbObject) && current.isInventoriable() && (player.getInventorySize() > player.getTotalInventoryWeight() + current.getWeight())) {
						player.addToInventory(current); //If the object is what they are trying to get, and it is able to be picked up, they can, as long as it isn't too heavy.
						player.getCurrentRoom().getContents().remove(current); //Removes the object from the room.
						System.out.println("You pick the " + current.getName().toLowerCase() + " up.");
						break;
					} else if (current.getParts() != null && current.getParts().size() > 0) { //Checks if they want to get sub-objects.
						for (i = 0; i < current.getParts().size(); i++) {
							Object currentPart = current.getParts().get(i);
							if (current.getName().toLowerCase().equals(verbObject) && current.isInventoriable() && (player.getInventorySize() > player.getTotalInventoryWeight() + current.getWeight())) {
								player.addToInventory(current);
								current.getParts().remove(currentPart); //Removes the object from the parts of the meta-object.
								System.out.println("You pick the " + currentPart.getName().toLowerCase() + " up.");
								break;
							}
						}
					}
				}
				break;
			case ("use"):
				//Allows use of keys on doors etc. to solve puzzles or whatever else.
				//TODO Will need a  different way of handling input to check if the two objects can be used on each other.
				//This will also require a change to the object class to determine which objects can interact with others as well as possible states like locked and unlocked etc. 
				break;
			case ("engage"):
				//TODO Initiates a fight.
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
				if(!found) System.out.println("You couldn't find that to look at.");
				break;
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
