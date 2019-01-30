import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Utilities {
	
	public static void describeRoom(Room currentRoom) {
		System.out.println(currentRoom.getDescription());
		if (currentRoom.getContents().size() > 0) {
			System.out.println("Around you, you can see: ");
			for (int i = 0; i < currentRoom.getContents().size(); i++) {
				String currentName = currentRoom.getContents().get(i).getName();
				System.out.print("> ");
				if (currentName.startsWith("[aeiou]")) {
					System.out.print("An ");
				} else {
					System.out.print("A ");
				}
				System.out.println(currentName.toLowerCase());
			}
			System.out.println();
		} else {
			System.out.println("There is nothing in this room.");
		}
		if (currentRoom.getCharacters().size() > 0) {
			System.out.println("There are others with you. You see: ");
			for (int i = 0; i < currentRoom.getCharacters().size(); i++) {
				System.out.println(currentRoom.getCharacters().get(i).getName());
			}
		} else {
			System.out.println("There is no one else here.");
		}
	}
	
	public static Player CreateCharacter(boolean debug, boolean player, Room currentRoom) {
		int r = 0;
		int e = 0;
		int p = 0;
		int l = 0;
		int i = 0;
		int c = 0;
		int a = 0;
		int s = 0;
		System.out.println("CHARACTER CREATOR: ");
		System.out.print("Name > ");
		String name = Utilities.StrInput();
		boolean inComplete = true;
		while(inComplete) {
			System.out.println("SKILL POINT ASSIGNMENT (24 Max)");
			System.out.print("Rationality > ");
			r = Integer.parseInt(Utilities.StrInput());
			System.out.print("Endurance > ");
			e = Integer.parseInt(Utilities.StrInput());
			System.out.print("Perception > ");
			p = Integer.parseInt(Utilities.StrInput());
			System.out.print("Luck > ");
			l = Integer.parseInt(Utilities.StrInput());
			System.out.print("Intelligence > ");
			i = Integer.parseInt(Utilities.StrInput());
			System.out.print("Charisma > ");
			c = Integer.parseInt(Utilities.StrInput());
			System.out.print("Agility > ");
			a = Integer.parseInt(Utilities.StrInput());
			System.out.print("Strength > ");
			s = Integer.parseInt(Utilities.StrInput());
			if(r+e+p+l+i+c+a+s <= 24) {
				inComplete = false; 
			} else System.out.println("These should add to 15.");
		}
		int[] REPLICAS = {r,e,p,l,i,c,a,s};
		//TODO Allow any character to be added to the game during runtime.
		Ammunition tempA = new Ammunition("Fists", Collections.emptyList(),0,0,0,"Kinetic", "");
		int[] resistances = {0, 10, 5, 15, 5};
		List <Skill> emptyS = new ArrayList<Skill>();
		List <Object> emptyO = new ArrayList<Object>();
		Apparel tempAp = new Apparel("Uniform", "It is your uniform, basically all you are seen in now.", Collections.emptyList(), 1, 10, true, 1, resistances, new int[8], 10);
		Weapon tempW = new Weapon(false, "Fists", 0, 0, "Your fists are slightly bruised from a previous fight", Collections.emptyList(), new String[0], "Melee", s * 2, 1, -1, -1, (float) 1.0, (float) 0.05, (float) 2.0, Collections.emptyList(), tempA, -1);
		int[] armourRes = new int[5];
		int[] REPLICASmods = new int[8];
		Object nullObject = new Object(false, "", "", Collections.emptyList(), new String[0], 0, 0);
		Apparel[] armour = {new Apparel("", "", emptyO, 0, 0, false, 0, armourRes, REPLICASmods, 0), new Apparel("", "", emptyO, 0, 0, false, 1, armourRes, REPLICASmods, 0), new Apparel("", "", emptyO, 0, 0, false, 2, armourRes, REPLICASmods, 0), new Apparel("", "", emptyO, 0, 0, false, 3, armourRes, REPLICASmods, 0), new Apparel("", "", emptyO, 0, 0, false, 4, armourRes, REPLICASmods, 0), new Apparel("", "", emptyO, 0, 0, false, 5, armourRes, REPLICASmods, 0)};
		return new Player(name, "It is you.", 0, 1, s*10, 0, armour, tempAp.getResistances(), tempAp, tempW, nullObject, s*10, emptyO, REPLICAS, emptyS, "None", currentRoom);
	}

	public static String randomLine(File text) throws FileNotFoundException {
		// Takes an input text file and when a randomly generated integer is equal to 0,
		// returns a line of the text file.
		Random rand = new Random();
		int n = 0;
		for (Scanner sc = new Scanner(text); sc.hasNext();) {
			++n;
			if (rand.nextInt(n) == 0) {
				sc.close();
				return sc.nextLine();
			}
		}
		return "";
	}
	
	public static void printSubObjects(Object object) {
		//Prints all the sub-parts of an object to the user.
		if (object.getParts() != null && object.getParts().size() > 0) {
			for (int i = 0; i < object.getParts().size(); i++) {
				System.out.println("You see it contains: ");
				String currentName = object.getParts().get(i).getName();
				System.out.print("> ");
				if (currentName.startsWith("[aeiou]")) {
					System.out.print("An ");
				} else {
					System.out.print("A ");
				}
				System.out.println(currentName.toLowerCase());
			}
		}
	}
	
	public static String StrInput() {
		// Takes string input from the console.
		@SuppressWarnings("resource") // Suppression of Resource Not Closed.
		Scanner in = new Scanner(System.in);
		String out = "";
		out = in.nextLine();
		// in.close(); //not closed in order to allow looping in Menu. Max 30/11/18
		return out;
	}

	public static String StrReplace(String str, int index, char newChar) {
		// Takes an input string and changes a character at a given index.
		StringBuilder out = new StringBuilder(str);
		out.setCharAt(index, newChar);
		return out.toString();
	}

	public static int rangedRandomInt(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}

	public static String generatePassword(int difficulty) throws FileNotFoundException {
		/*
		 * Generates random passwords using random words from a dictionary file and
		 * adding a number of non-alphanumeric visible characters based on the
		 * difficulty.
		 */
		File file = new File("StatRes/Dictionary.txt");
		String base = Utilities.randomLine(file);
		for (int i = 0; i < difficulty / 3; i++) {
			int index = Utilities.rangedRandomInt(base.length() - 1, 0);
			Utilities.StrReplace(base, index, (char) Utilities.rangedRandomInt(33, 63));
		}
		return base;
	}

	public static Game loadGame(File file) throws ClassNotFoundException, IOException {
		FileInputStream fileStream = new FileInputStream(file);
		ObjectInputStream objectStream = new ObjectInputStream(fileStream);
		Game stored = (Game) objectStream.readObject();
		objectStream.close();
		fileStream.close();
		return stored;
	}

	public static void saveGame(File file, Game game, boolean display) throws IOException {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
			if(display) System.out.println("Data saved in: " + file.getName());
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public static void testQuit() {
		System.out.println("Are you sure that you want to quit? [Y/n]");
		System.out.print("> ");
		String confirm = Utilities.StrInput();
		if (confirm.toLowerCase().equals("y")) System.exit(0);
	}
}
