package construction;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3521702290298779279L;
	private String name;
	private List<object.Object> contents;
	private List<Character> characters;
	private String description;
	private Room[] rooms; //Up, down, left, right, forward, backward, in, out.
	private boolean[] access;
	
	public Room(String name, List<object.Object> contents, List<Character> characters, String description, Room[] rooms, boolean[] access) {
		this.name = name;
		this.contents = contents;
		this.characters = characters;
		this.description = description;
		this.rooms = rooms;
		this.access = access;
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
	public List<object.Object> getContents() {
		return contents;
	}
	public void setContents(List<object.Object> contents) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
