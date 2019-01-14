import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		Game instance = new Game();
		instance.menu();
	}

	private void menu() {
		boolean selection = false;
		int choice = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("1. Load Game");
		System.out.println("2. New Game");
		System.out.println("3. Credits");
		System.out.println("4. Quit");
		while (!selection) {
			try {
				choice = Integer.parseInt(in.nextLine());
				if(choice > 0 && choice < 5) {
					selection = true;
				} else {
					selection = false;
				}
			} catch (Exception e) {
				System.out.println("There was an error with that input.");
				selection = false;
			}
		}
		switch (choice) {
		case(1):
			loadGame();
		case(2):
			newGame();
		case(3):
			credits();
		case(4):
			System.exit(-1);
		}
	}

	private void credits() {
		// TODO Auto-generated method stub
		
	}

	private void newGame() {
		// TODO Auto-generated method stub
		
	}

	private void loadGame() {
		// TODO Auto-generated method stub
		
	}
}
