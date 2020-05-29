package object;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;

import construction.Utilities;

public class Terminal extends Object{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8924818284601108053L;
	private String[] logNames, logs, usernames, passwords;
	private boolean[] locked;
	private int difficulty;

	public Terminal(String name, String description, int difficulty, boolean[] locked, String[] logNames, String[] logs,
			String[] usernames, String[] passwords) {
		super(false, name, description, Collections.emptyList(), null);
		this.usernames = usernames; // 0 is MASTER.
		this.passwords = passwords; // 0 is appropriate master password.
		this.difficulty = difficulty;
		this.locked = locked;
		this.logs = logs;
		this.logNames = logNames;
	}

	/**
	 * Unlocks the given username that the player gives provided that the password
	 * given is correct.
	 * 
	 * @param userAtt
	 *            the username the player will try to unlock the terminal.
	 * @param passAtt
	 *            the password the player will user to try to unlock the terminal.
	 */
	public void unlock(String userAtt, String passAtt) {

		int pairIndex = Arrays.asList(usernames).indexOf(userAtt);
		if (pairIndex != -1) {
			if (userAtt == usernames[pairIndex] && passAtt == passwords[pairIndex]) {
				this.unLocked(locked, pairIndex);
				System.out.println("You unlock " + userAtt + "'s account");
			}
		}
	}

	/**
	 * @param difficulty
	 *            the level of encryption on the system that affects the difficulty
	 *            of the hacking process.
	 * @param hackingSkill
	 *            the skill of the level of the player.
	 */
	public void hack(int difficulty) {
		int dim = difficulty;
		int guesses = (int) (dim * dim * 0.05); /*
												 * Guesses are equal 5% of the number of passwords in the grid plus 5%
												 * of the players hackingSkill.
												 */
		String[][] passwordsPoss = new String[dim][dim];
		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				try {
					String tempPass = Utilities.generatePassword(difficulty);
					while (tempPass.length() < this.getPasswords()[0].length() + 1) {
						tempPass = tempPass + Utilities.generatePassword(difficulty);
					}
					passwordsPoss[x][y] = tempPass.substring(0, this.getPasswords()[0].length()); // Populates the array
																									// with random
					// passwords.
				} catch (FileNotFoundException e) {
					System.out.println(
							"There has been an error. " + "This terminal cannot be hacked due to missing resources");
					// If the file cannot be found, an error is returned.
				}
			}
		}
		passwordsPoss[Utilities.rangedRandomInt(0, dim - 1)][Utilities.rangedRandomInt(0, dim - 1)] = passwords[0];
		// Replaces one of the randomly generated generated passwords in the grid with
		// the true password.
		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				System.out.print(passwordsPoss[x][y] + " ");
			}
			System.out.println(); // Prints out the 2D array of passwords for the player.
		} // TODO account for length in output. Longest word =
			// pneumonoultramicroscopicsilicovolcanoconiosis.
		for (int i = 0; i < guesses; i++) {
			int sames = 0;
			String guess = Utilities.StrInput(); // Takes in a player's guess at the password.
			if (!guess.equals(passwords[0])) {
				if (guess.length() != passwords[0].length()) {
					System.out.println("That password is not the right length.");
					continue;
				}
				for (int j = 0; j < passwords[0].length(); j++) {
					if (guess.charAt(j) == passwords[0].charAt(j)) {
						sames++;
					}
				}
				System.out.println("There are " + sames + " characters the same as the password.");
				continue;
			} else {
				System.out.println(this.getName() + " is now unlocked.");
				for (int x = 0; x < locked.length; x++) {
					this.unLocked(locked, x); // Sets the locked value for all the users to unlocked.
				}
				break;
			}
		}
	}

	// Getters and Setters.
	public String[] getPasswords() {
		return passwords;
	}

	public void setPassword(String[] passwords) {
		this.passwords = passwords;
	}

	public String[] getLogs() {
		return logs;
	}

	public void setLogs(String[] logs) {
		this.logs = logs;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String[] getUsernames() {
		return usernames;
	}

	public void setUsernames(String[] usernames) {
		this.usernames = usernames;
	}

	public boolean isLocked(int index) {
		return locked[index];
	}

	public boolean[] getLocked() {
		return locked;
	}

	public void setPasswords(String[] passwords) {
		this.passwords = passwords;
	}

	public void setLocked(boolean[] locked) {
		this.locked = locked;
	}

	public void unLocked(boolean[] locked, int index) {
		this.locked[index] = false;
	}

	public String[] getLogNames() {
		return logNames;
	}

	public void setLogNames(String[] logNames) {
		this.logNames = logNames;
	}

	public static Terminal modifyTerminal(int selection, Terminal terminal) {
		switch (selection) {
		case 1:
			System.out.println("New name: ");
			terminal.setName(Utilities.StrInput());
			break;
		case 2:
			System.out.println("New description: ");
			terminal.setDescription(Utilities.StrInput());
			break;
		case 3:
			System.out.println("New difficulty: ");
			terminal.setDifficulty(Utilities.handleIntInput(1, Integer.MAX_VALUE));
			break;
		case 4:
			System.out.println("User modification: ");
			System.out.println("1. Delete User");
			System.out.println("2. Add User");
			int option = Utilities.handleIntInput(1, 2);
			switch (option) {
			case 1:
				int removalIndex = -1;
				int counter = 0;
				for (String i : terminal.usernames) {
					System.out.println(counter + ". " + i);
					counter++;
				}
				String toDelete = Utilities.StrInput().toLowerCase();
				for (int i = 0; i < terminal.usernames.length; i++) {
					if (terminal.usernames[i].toLowerCase().equals(toDelete)) {
						removalIndex = i;
					}
				}
				terminal.logs = (String[]) reduceArray(terminal.logs, removalIndex);
				terminal.logNames = (String[]) reduceArray(terminal.logNames, removalIndex);
				terminal.usernames = (String[]) reduceArray(terminal.usernames, removalIndex);
				terminal.passwords = (String[]) reduceArray(terminal.passwords, removalIndex);
				terminal.locked = (boolean[]) reduceArray(terminal.locked, removalIndex);
				break;
			case 2:
				System.out.println("Locked: ");
				boolean isLocked = Boolean.parseBoolean(Utilities.StrInput());
				System.out.println("Log name: ");
				String logName = Utilities.StrInput();
				System.out.println("Log: ");
				String log = Utilities.StrInput();
				System.out.println("Username: ");
				String username = Utilities.StrInput();
				System.out.println("Password: ");
				String password = Utilities.StrInput();
				System.out.println("Insertion index: ");
				int index = Integer.parseInt(Utilities.StrInput());
				terminal.logs = (String[]) expandArray(terminal.logs, log, index);
				terminal.logNames = (String[]) expandArray(terminal.logNames, logName, index);
				terminal.usernames = (String[]) expandArray(terminal.usernames, username, index);
				terminal.passwords = (String[]) expandArray(terminal.passwords, password, index);
				terminal.locked = (boolean[]) expandArray(terminal.locked, isLocked, index);
				break;
			}
		}
		return terminal;
	}

	private static boolean[] reduceArray(boolean[] array, int toRemove) {
		boolean[] newArray = new boolean[array.length - 1];
		int counter = -1;
		for (int i = 0; i < array.length; i++) {
			counter++;
			if (i == toRemove) {
				counter--;
				continue;
			}
			newArray[counter] = array[i];
		}
		return newArray;
	}

	private static java.lang.Object[] reduceArray(java.lang.Object[] array, int toRemove) {
		java.lang.Object[] newArray = new java.lang.Object[array.length - 1];
		array[toRemove] = null;
		int counter = -1;
		for (java.lang.Object i : array) {
			counter++;
			if (!i.equals(null)) {
				counter--;
				continue;
			}
			newArray[counter] = i;
		}
		return newArray;
	}

	private static java.lang.Object[] expandArray(java.lang.Object[] array, java.lang.Object toInsert, int index) {
		java.lang.Object[] newArray = new java.lang.Object[array.length + 1];
		if(index > array.length) {
			index = array.length;
		} else if(index < 0) {
			index = 0;
		}
		boolean found = false;
		for(int i = 0; i < array.length; i++) {
			if (i == index) {
				found = true;
			} 
			if (found) {
				newArray[i+1] = array[i];
			} else {
				newArray[i] = array[i];
			}
		}
		return newArray;
	}

	private static boolean[] expandArray(boolean[] array, boolean toInsert, int toRemove) {
		boolean[] newArray = new boolean[array.length - 1];
		int counter = -1;
		for (int i = 0; i < array.length; i++) {
			counter++;
			if (i == toRemove) {
				counter--;
				continue;
			}
			newArray[counter] = array[i];
		}
		return newArray;
	}
}
