import java.io.Serializable;
import java.util.List;


public class Object implements Serializable { /**
	 * 
	 */
	private static final long serialVersionUID = 251080304814776649L;
//TODO Terminals (controls and personal), rest-places, consumables, keys, crafting areas.
	private boolean inventoriable;
	private String name, description;
	private List<Object> parts;
	private int weight, value;

	public Object(boolean inventoriable, String name, String description, 
			List<Object> parts, int weight, int value) {
		this.inventoriable = inventoriable;
		this.name = name;
		this.description = description;
		this.parts = parts;
		this.weight = weight;
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public boolean isInventoriable() {
		return inventoriable;
	}
	

	public void setInventoriable(boolean inventoriable) {
		this.inventoriable = inventoriable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Object> getParts() {
		return parts;
	}

	public void setParts(List<Object> parts) {
		this.parts = parts;
	}

}
