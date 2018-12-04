import java.io.Serializable;

public class Room implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3521702290298779279L;
	private Object[] contents;
	private Character[] characters;
	private String description;
	private int id;
	private int[] rooms;
	private boolean[] access;
	
	public Room(Object[] contents, Character[] characters, String description, int id, int[] rooms, boolean[] access) {
		this.contents = contents;
		this.characters = characters;
		this.description = description;
		this.id = id;
		this.rooms = rooms;
		this.access = access;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int[] getRooms() {
		return rooms;
	}
	public void setRooms(int[] rooms) {
		this.rooms = rooms;
	}
	public boolean[] getAccess() {
		return access;
	}
	public void setAccess(boolean[] access) {
		this.access = access;
	}
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
