package construction;

public class Test {

	public static void main(String[] args) {
		//TODO implement test to see if this system works. int[] ints

	}

	public static java.lang.Object[] expandArray(java.lang.Object[] array, java.lang.Object toInsert, int index) {
		java.lang.Object[] newArray = new java.lang.Object[array.length + 1];
		if(index > array.length) {
			index = array.length;
		} else if(index < 0) {
			index = 0;
		}
		boolean found = false;
		for(int i = 0; i < array.length; i++) {
			if (i == index) {
				found = true;
			} 
			if (found) {
				newArray[i+1] = array[i];
			} else {
				newArray[i] = array[i];
			}
		}
		return newArray;
	}
}
