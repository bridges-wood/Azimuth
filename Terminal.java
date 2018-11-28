import java.io.BufferedReader;
import java.io.FileReader;
import Utilities.randomLine; //Sort this from the repo.

public class Terminal extends Object {
	private String  username, password;
	private String[] logNames, logs;
	private boolean locked;
	private int difficulty;

	public Terminal(String name, String description, String username, String password, int difficulty, boolean locked, String[] logNames, String[] logs) {
		super(false, name, description, null, 0, 0);
		this.username = username;
		this.password = password;
		this.difficulty = difficulty;
		this.locked = locked;
		this.logs = logs;
	}
	
	public void unlock(String userAtt, String passAtt) {
		this.setLocked(userAtt == username && passAtt == password);
	}
	
	public void hack(int difficulty) {
		int dim = (int) Math.ceil((double) (1/difficulty * 10));
		String[][] passwords = new String[dim][dim];
		BufferedReader reader = new BufferedReader(new FileReader("Dictionary.txt"));
		for(int x = 0; x < dim; x++) {
			for(int y = 0; y < dim; y++) {
				passwords[x][y] = 
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
