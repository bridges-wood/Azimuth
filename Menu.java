import java.io.File;
import java.io.IOException;

public class Menu {
	private static File currentFile;
	
	public static void main(String[] args) {
		new Menu();
	}

	public Menu() {
		int choice = 0;
		System.out.println("MENU (Please use numbers)");
		System.out.println("1. New Game");
		System.out.println("2. Load Game");
		System.out.println("3. Options");
		System.out.println("4. Credits");
		System.out.println("5. Quit");
		while (true) { //Continues prompting user for input if a non-terminal input is selected.
			choice = 0;
			System.out.print("> ");
			while (choice > 5 || choice < 1) { //Checks if the user's choice is valid.
				String in = "";
				choice = 0;
				in = Utilities.StrInput();
				try {
					choice = Integer.parseInt(in);
					if (choice > 5 || choice < 1) {
						System.out.print("> ");
					}
				} catch (Exception E) {
					System.out.print("> ");
				}
			}
			switch (choice) {
			case 1:
				newGame();
				break;
			case 2:
				showSaves();
				String in = Utilities.StrInput();
				File file = null;
				if (in.endsWith(".gme")) {
					file = new File("SaveGames/" + in);
				} else {
					file = new File("SaveGames/" + in + ".gme");
				}
				try {
					Game runtime = Utilities.loadGame(file);
					System.out.println("Game successfully loaded.");
					setCurrentFile(file);
					runtime.playGame();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("There has been an error ");
				}
				break;
			case 3:
				options();
				break;
			case 4:
				credits();
				break;
			case 5:
				Utilities.testQuit();
				break;
			}
		}

	}

	public static void showSaves() {
		System.out.println("Your saves: ");
		File[] files = new File("SaveGames/").listFiles();
		int counter = 0;
		for (File file : files) {
			if (file.getName().endsWith(".gme")) {
				counter++;
				System.out.println(counter + ". " + file.getName().substring(0, file.getName().length() -4));
				//Cleans up filename to avoid confusion with name to be inputed in order to get a successful load.
			}
		}
	}

	public void newGame() {
		while (true) {
			System.out.println("File name: ");
			String name = Utilities.StrInput();
			File file = new File("SaveGames/" + name + ".gme");
			try {
				if (file.createNewFile()) {
					System.out.println("Game created.");
					setCurrentFile(file);
					break;
				} else {
					System.out.println("A save with name: " + name + " already exists.");
					continue;
				}
			} catch (IOException e) {
				System.out.println("Something went wrong with that file name.");
			}
		}
		@SuppressWarnings("unused")
		Game runtime = new Game();
	}

	public void options() {
		
	}

	public void credits() {
		System.out.println("/nCredits:");
		System.out.println("Pretty much everything: Max Wood");
		System.out.println("Copyright Max Wood 2019: All Rights Reserved");
	}

	public static File getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(File currentFile) {
		Menu.currentFile = currentFile;
	}

}
