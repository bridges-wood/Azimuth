import java.io.Serializable;

public class Room implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3521702290298779279L;
	private Object[] contents;
	private Character[] characters;
	private String description;
	
	public Object[] getContents() {
		return contents;
	}
	public void setContents(Object[] contents) {
		this.contents = contents;
	}
	public Character[] getCharacters() {
		return characters;
	}
	public void setCharacters(Character[] characters) {
		this.characters = characters;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
