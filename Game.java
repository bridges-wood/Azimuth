import java.io.Serializable;

public class Game implements Serializable {
	private static final long serialVersionUID = 557779407485802525L;
	
	public Game() {
		this.init();
	}

	public void init() {
		int[] REPLICAS = {1,1,1,1,1,1,1,1};
		Player player = new Player("Player", 0, 0, 20, 0, null, null, null, null, 20, null, REPLICAS, null, null, null);
		Object bed = new Object(false, "Bed", "It is your bed.", null, 400, 0);
		Object[] contents = {bed};
		Room first = new Room(contents, null, "It is your bedroom", 1, null, null);
		player.setCurrentRoom(first);
		Room[] rooms = {first};
		this.playGame(rooms, player);
	}

	public void playGame(Room[] rooms, Player player) {
		boolean victory = false;
		while(victory = false) {
			String userIn = Utilities.StrInput();
			String[] inputArr = userIn.split(" ");
			String verb = inputArr[0];
			String verbObject = "";
			for(int i = 1; i < inputArr.length; i++) {
				verbObject = inputArr[i] + " ";
			}
			verbObject.trim();
			switch(verb) {
				case("get"):
				case("use"):
				case("engage"):
				case("examine"):
					for(int i = 0; i < player.getCurrentRoom().getContents().length; i++) {
						if(player.getCurrentRoom().getContents()[i].getName() == verbObject);
						//finish this to allow the player to examine objects.
					}
			}
		}
	}
}
