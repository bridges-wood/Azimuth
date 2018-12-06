import java.io.File;
import java.io.IOException;

public class Menu {
	
	public static void main(String[] args) {
		new Menu();
	}

	public Menu() {
		int choice = 0;
			System.out.println("MENU");
			System.out.println("1. New Game");
			System.out.println("2. Load Game");
			System.out.println("3. Options");
			System.out.println("4. Credits");
			System.out.println("5. Quit");
			System.out.print("> ");
			while (choice > 5 || choice < 1) {
				String in = "";
				choice = 0;
			in = Utilities.StrInput();
			try {
				choice = Integer.parseInt(in);
				if(choice > 5 || choice < 1) {
					System.out.print("> ");
				}
			} catch (Exception E) {
				System.out.print("> ");
			}
		}
			switch (choice) {
			case 1: showSaves();
					String in = Utilities.StrInput();
					File file = new File(in);
				try {
					Utilities.loadGame(file);
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("There has been an error ");
				}
			case 2: newGame();
			case 3:	options();
			case 4: credits();
			case 5: System.out.println("Are you sure that you want to quit? [Y/n]");
					System.out.print("> ");
					String confirm = Utilities.StrInput();
					if(confirm.toLowerCase() == "y") {
						System.exit(0);
					}
			}
			
	}
	
	public void showSaves() {
		File[] files = new File("SaveGames/").listFiles();
		int counter = 0;
		for(File file : files) {
			if(file.getName().endsWith(".gme")) {
				counter++;
				System.out.println(counter + ". " + file.getName());
			}
		}
	}
	public void newGame() {
		System.out.println("File name: ");
		String name = Utilities.StrInput();
		File file = new File("Savegames/" + name + ".gme");
		try {
			if(file.createNewFile()) {
				System.out.println("Game created.");
			} else {
				System.out.println("A save with name: " + name + " already exists.");
			}
		} catch (IOException e) {
			System.out.println("Something went wrong with that file name.");
		}
	}
	
	public void options() {
		
	}
	
	public void credits() {
		
	}
	
}
