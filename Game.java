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
		int[] REPLICAS = {1,1,1,1,1,1,1,1};
		//TODO add in a character creator when the player first initialises the game.
		this.player = new Player("Player", 0, 0, 20, 0, null, null, null, null, 20, null, REPLICAS, null, null, null);
		Object bed = new Object(false, "Bed", "It is your bed.", null, 400, 0);
		Object[] contents = {bed};
		Room first = new Room(contents, null, "It is your bedroom", 1, null, null);
		player.setCurrentRoom(first);
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(first);
		setRooms(rooms);
		playGame();
	}
	
	public void playGame() {
		boolean playing = true;
		while(playing) {
			String userIn = Utilities.StrInput();
			String[] inputArr = userIn.split(" ");
			String verb = inputArr[0];
			String verbObject = "";
			for(int i = 1; i < inputArr.length; i++) {
				verbObject = inputArr[i] + " ";
			}
			verbObject = verbObject.toLowerCase().trim();
			switch(verb) {
				case("save"):
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
				case("get"):
					break;
				case("use"):
					break;
				case("engage"):
					break;
				case("examine"):
					for(int i = 0; i < player.getCurrentRoom().getContents().length; i++) {
						Object current = player.getCurrentRoom().getContents()[i];
						if(current.getName().toLowerCase().equals(verbObject)) {
							System.out.println(current.getDescription());
							break;
						}
						//finish this to allow the player to examine other things like their own weapons etc.
					}
				break;
				//TODO add more verbs.
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
