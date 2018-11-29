import java.io.File;
import java.io.FileNotFoundException;

public class Terminal extends Object {
	private String[] logNames, logs, usernamesAndPasswords;
	private boolean locked;
	private int difficulty;

	public Terminal(String name, String description, int difficulty, boolean locked, String[] logNames, String[] logs) {
		super(false, name, description, null, 0, 0);
		this.usernamesAndPasswords = usernamesAndPasswords; /*TODO need to get this to generate a series of usernames and passwords for a terminal based on the input array.
		Suggest taking in a string array of usernames and passowords together as one string and streaming them into a map so the usernames can be checked to see if they match
		passwords.
		Need to set up methods for adding usernames and passwords and also one to generate random users and passwords for generic terminals.*/
		this.difficulty = difficulty;
		this.locked = locked;
		this.logs = logs;
	}
	
	public void unlock(String userAtt, String passAtt) {
		this.setLocked(userAtt == username && passAtt == password);
	}
	
	public void hack(int difficulty) {
		int dim = (int) Math.ceil((double) (1/difficulty * 10));
		int guesses = (int) (dim*dim * 0.05);
		String[][] passwords = new String[dim][dim];
		File dictionary = new File("StatRes/Dictionary.txt");
		for(int x = 0; x < dim; x++) {
			for(int y = 0; y < dim; y++) {
				try {
					passwords[x][y] = Utilities.generatePassword(difficulty);
				} catch (FileNotFoundException e) {
					System.out.println("There has been an error. "
					+ "This terminal cannot be hacked due to missing resources");
				}
			}
		}
		passwords[Utilities.rangedRandomInt(0, passwords[0].length - 1)][Utilities.rangedRandomInt(0, passwords.length - 1)] = password;
		for(int i = 0; i < guesses; i ++) {
			int sames = 0;
			String guess = Utilities.StrInput();
			if(!guess.equals(password)) {
				if(guess.length() != password.length()) {
					System.out.println("That password is not the right length.");
					continue;
				}
				for(int j = 0; j < password.length(); j++) {
					if(guess.charAt(j) ==  password.charAt(j)) {
						sames++;
					}
				}
				System.out.println("There are " + sames + " characters the same as the password.");
			}
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public boolean isLocked() {
		return locked;
	}
	

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String[] getLogNames() {
		return logNames;
	}

	public void setLogNames(String[] logNames) {
		this.logNames = logNames;
	}

}
