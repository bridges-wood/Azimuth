
public class Terminal extends Object {
	private String menu;
	private String password;
	private String[] logName;
	private String[] logs;
	private String difficulty;
	
	public Terminal( String name, String description) {
		super(false, name, description, null, 0, 0);
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

}
