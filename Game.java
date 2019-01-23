import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

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
		this.player = new Player("Player", 0, 0, 20, 0, null, null, null, null, 20, null, REPLICAS, null, null, null);
		Object bed = new Object(false, "Bed", "It is your bed.", null, 400, 0);
		Object[] contents = { bed };
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
				System.out.println("Are you sure you want to quit? [Y/n]");
				String response = Utilities.StrInput(); //Ch
				if (response.toLowerCase().equals("y")) System.exit(0);
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
				for (int i = 0; i < player.getCurrentRoom().getContents().length; i++) { //Checks if player wants to get meta-objects in a room.
					Object current = player.getCurrentRoom().getContents()[i];
					if (current.getName().toLowerCase().equals(verbObject) && current.isInventoriable()) {
						player.addToInventory(current); //If the object is what they are trying to get, and it is able to be picked up, they can.
						break;
					} else if (current.getParts().length > 0) { //Checks if they want to get sub-objects.
						for (i = 0; i < current.getParts().length; i++) {
							current = player.getCurrentRoom().getContents()[i];
							if (current.getName().toLowerCase().equals(verbObject) && current.isInventoriable()) {
								player.addToInventory(current);
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
				for (int i = 0; i < player.getCurrentRoom().getContents().length; i++) {
					Object current = player.getCurrentRoom().getContents()[i];
					if (current.getName().toLowerCase().equals(verbObject)) {
						System.out.println(current.getDescription());
						break;
					}
					// TODO finish this to allow the player to examine other things like their own
					// weapons etc.
				}
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
