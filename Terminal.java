import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Arrays;


public class Terminal extends Object implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8924818284601108053L;
	private String[] logNames, logs, usernames, passwords;
	private boolean[] locked;
	private int difficulty;

	public Terminal(String name, String description, int difficulty, boolean[] locked, String[] logNames, String[] logs, String[] usernames, String[] passwords) {
		super(false, name, description, null, 0, 0);
		this.usernames = usernames; //0 is MASTER.
		this.passwords = passwords; //0 is appropriate master password.
		this.difficulty = difficulty;
		this.locked = locked;
		this.logs = logs;
	}
	
	/**
	 * Unlocks the given username that the player gives provided that the password
	 * given is correct.
	 * 
	 * @param userAtt the username the player will try to unlock the terminal.
	 * @param passAtt the password the player will user to try to unlock the terminal.
	 */
	public void unlock(String userAtt, String passAtt) {
		
		int pairIndex = Arrays.asList(usernames).indexOf(userAtt);
		if(pairIndex != -1) {
			if(userAtt == usernames[pairIndex] && passAtt == passwords[pairIndex]) {
				this.unLocked(locked, pairIndex);
			}
		}
	}
	
	/**
	 * @param difficulty the level of encryption on the system that affects the difficulty of the hacking process.
	 * @param hackingSkill the skill of the level of the player.
	 */
	public void hack(int difficulty, int hackingSkill) {
		int dim = (int) Math.ceil((double) (1/difficulty * 10)); /*Calculates the dimensions of the array as ten 
		 														   times the reciprocal of the difficulty. This
		 														   is then rounded up to avoid 0 length arrays.*/
		
		int guesses = (int) ((dim*dim * 0.05) + (hackingSkill * 0.05)); /*Guesses are equal 5% of the number of passwords in the grid
		 																 plus 5% of the players hackingSkill.*/
		String[][] passwordsPoss = new String[dim][dim];
		for(int x = 0; x < dim; x++) {
			for(int y = 0; y < dim; y++) {
				try {
					passwordsPoss[x][y] = Utilities.generatePassword(difficulty); //Populates the array with random passwords.
				} catch (FileNotFoundException e) {
					System.out.println("There has been an error. "
					+ "This terminal cannot be hacked due to missing resources"); //If the file cannot be found, an error is returned.
				}
			}
		}
		passwordsPoss[Utilities.rangedRandomInt(0, passwordsPoss[0].length - 1)] //Replaces one of the randomly generated passwords in the grid with the true password.
					 [Utilities.rangedRandomInt(0, passwordsPoss.length - 1)] = passwords[0];
		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				System.out.print(passwordsPoss[x][y] + " ");
			}
			System.out.println(""); //Prints out the 2D array of passwords for the player.
		}
		for(int i = 0; i < guesses; i ++) {
			int sames = 0;
			String guess = Utilities.StrInput(); //Takes in a player's guess at the password.
			if(!guess.equals(passwords[0])) {
				if(guess.length() != passwords[0].length()) {
					System.out.println("That password is not the right length.");
					continue;
				}
				for(int j = 0; j < passwords[0].length(); j++) {
					if(guess.charAt(j) ==  passwords[0].charAt(j)) {
						sames++;
					}
				}
				System.out.println("There are " + sames + " characters the same as the password.");
				continue;
			}
			for(int x = 0; x < locked.length; x++) {
				this.unLocked(locked, x); //Sets the locked value for all the users to unlocked. 
			}
			break;
		}
	}
	
	//Getters and Setters.
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

}
