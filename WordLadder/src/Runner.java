import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Runner {
	public static void main(String[] args) {
		WordLadder x = new WordLadder("sail", "ruin");
		Queue<Stack<String>> test = new LinkedList<>();
		
		testInput();
		
		/*boolean foundOrNot = x.loopTillEndWord();
		
		if(foundOrNot) {
			System.out.println("Found a ladder! >>> " + x.getCorrectLadder().toString());
		}
		else {
			System.out.println("No ladder between");
		}*/
		
	}
	
	private static void testInput() {
		try {
			Scanner in = new Scanner(new File("input.txt"));
			ArrayList<String[]> inputs = new ArrayList<>();
			while(in.hasNextLine()) {
				String[] lineIn = in.nextLine().split(" ");
				inputs.add(lineIn);
			}
			boolean foundOrNot;
			for(int e = 0; e < inputs.size(); e++) {
				WordLadder x = new WordLadder(inputs.get(e)[0], inputs.get(e)[1]);
				foundOrNot = x.loopTillEndWord();
				
				if(foundOrNot) {
					System.out.println("Found a ladder! >>> " + x.getCorrectLadder().toString());
				}
				else {
					System.out.println("No ladder between " + inputs.get(e)[0] + " and " + inputs.get(e)[1]);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
