import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Useable extends Object implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2993920639711393879L;
	private String genericUse;
	private Map<Object, String> specificUses;
	
	public Useable(boolean inventoriable, String name, String description, List<Object> parts, String[] combinable,
			int weight, int value) {
		super(inventoriable, name, description, parts, combinable, weight, value);
		// TODO Auto-generated constructor stub
	}

	public String getGenericUse() {
		return genericUse;
	}

	public void setGenericUse(String genericUse) {
		this.genericUse = genericUse;
	}

}
