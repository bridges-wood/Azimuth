package apparel;

import java.io.Serializable;
import java.util.List;

import object.Object;

public class Apparel extends Object implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8229924260087295982L;
	private boolean isUnderClothes;
	private int area;

	public Apparel(String name, String description, List<Object> parts, boolean isUnderClothes, int area) {
		super(true, name, description, parts, null);
		this.isUnderClothes = isUnderClothes;
		this.area = area;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public boolean isUnderClothes() {
		return isUnderClothes;
	}

	public void setUnderClothes(boolean isUnderClothes) {
		this.isUnderClothes = isUnderClothes;
	}
}
