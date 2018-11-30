import java.io.Serializable;

public class Skill implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4284520134659894370L;
	private String name, description;
	private int level;

	public Skill(String name, String description, int level) {
		this.name = name;
		this.description = description;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
