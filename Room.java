import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3521702290298779279L;
	private List<Object> contents;
	private List<Character> characters;
	private String description;
	private int id;
	private Room[] rooms; //Up, down, left, right, forward, backward, in, out.
	private boolean[] access;
	
	public Room(List<Object> contents, List<Character> characters, String description, int id, Room[] rooms, boolean[] access) {
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
	public Room[] getRooms() {
		return rooms;
	}
	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
	public boolean[] getAccess() {
		return access;
	}
	public void setAccess(boolean[] access) {
		this.access = access;
	}
	public List<Object> getContents() {
		return contents;
	}
	public void setContents(List<Object> contents) {
		this.contents = contents;
	}
	public List<Character> getCharacters() {
		return characters;
	}
	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
