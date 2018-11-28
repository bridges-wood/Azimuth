import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Utilities {
	
	public static String randomLine(File text) throws FileNotFoundException{
		Random rand = new Random();
		int n = 0;
		for(Scanner sc = new Scanner(text); sc.hasNext();) {
			++n;
			if(rand.nextInt(n) == 0) {
				sc.close(); 
				return sc.nextLine();
			}
		}
		return "";
	}
}
