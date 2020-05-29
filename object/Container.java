package object;

import java.util.ArrayList;
import java.util.List;

import construction.Map;
import construction.Utilities;

public class Container extends Object {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2179747136654751547L;
	private boolean locked;
	private Key[] workingKeys;

	public Container(String name, String description, List<Object> parts, boolean locked, Key[] workingKeys) {
		super(false, name, description, parts, new String[0]);
		this.locked = locked;
		this.workingKeys = workingKeys;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Key[] getWorkingKeys() {
		return workingKeys;
	}

	public void setWorkingKeys(Key[] workingKeys) {
		this.workingKeys = workingKeys;
	}
	
	public static Container modifyContainer(int selection, Container container) {
		switch (selection) {
		case 1:
			System.out.println("New name: ");
			container.setName(Utilities.StrInput());
			break;
		case 2:
			System.out.println("New description: ");
			container.setDescription(Utilities.StrInput());
			break;
		case 3:
			System.out.println("New parts: ");
			container.setParts(Map.createSubObjects());
			break;
		case 4:
			System.out.println("New locked: ");
			container.setLocked(Boolean.parseBoolean(Utilities.StrInput()));
			break;
		case 5:
			System.out.println("5. Usable keys: ");
			for (Object i : Map.objects) {
				if (i.getClass().getSimpleName().equals("Key")) {
					System.out.println(i.getName());
				}
			}
			List<Key> workingKeys = new ArrayList<Key>();
			while (true) {
				System.out.println("Key name: ");
				String choice = Utilities.StrInput();
				if (choice.equals("quit")) {
					break;
				} else {
					for (Object i : Map.objects) {
						if (i.getClass().getSimpleName().equals("Key")) {
							Key key = (Key) i;
							if (i.getName().toLowerCase().equals(choice.toLowerCase())) {
								workingKeys.add(key);
							}
						}
					}
				}
			}
			container.setWorkingKeys((Key[]) workingKeys.toArray());
			break;
		}
		return container;
	}

}
