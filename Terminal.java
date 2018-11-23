
public class Terminal extends Object {
	private String menu, username, password, difficulty;
	private String[] logName, logs;

	public Terminal(String name, String description, String menu, String username, String password, String difficulty) {
		super(false, name, description, null, 0, 0);
		this.menu = menu;
		this.setUsername(username);
		this.password = password;
		this.difficulty = difficulty;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getLogName() {
		return logName;
	}

	public void setLogName(String[] logName) {
		this.logName = logName;
	}

	public String[] getLogs() {
		return logs;
	}

	public void setLogs(String[] logs) {
		this.logs = logs;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
