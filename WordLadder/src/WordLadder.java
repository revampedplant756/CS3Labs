import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordLadder {
	
	Queue<Stack<String>> ladder;
	List<String> dict;
	String firstWord;
	String endWord;
	List<String> alreadyAdded;
	Stack<String> correctladder;
	
	public WordLadder(String firstWord, String endWord) {
		ladder = new LinkedList<>();
		dict = loadDict();
		alreadyAdded = new ArrayList<>();
		correctladder = new Stack<>();
		this.firstWord = firstWord;
		this.endWord = endWord;
		Stack<String> temp = new Stack<>();
		temp.push(firstWord);
		ladder.offer(temp);
		alreadyAdded.add(firstWord);
	}
	
	private ArrayList<String> loadDict() {
		ArrayList<String> temp = new ArrayList<>();
		try {
			Scanner in = new Scanner(new File("dictionary.txt"));
			while(in.hasNext()) {
				temp.add(in.nextLine().toLowerCase());
			}
			return temp;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}
	

	
	public ArrayList<String> differByOneLetter(String word){
		ArrayList<String> words = new ArrayList<>();
		Queue<Character> letters = new LinkedList<>();
		
		for (char ch : word.toCharArray()) {
			letters.offer(ch);
	    }
		
		int size = letters.size();
		
		for(int e = 0; e < dict.size(); e++) {
			if(dict.get(e).length() == word.length()) {
				for(int f = 0; f < size; f++) {
					if(dict.get(e).charAt(f) == letters.peek()) {
						letters.poll();
					}
					else {
						letters.offer(letters.poll());
					}
				}
				if(letters.size() == 1) {
					words.add(dict.get(e));
				}
				letters.clear();
				for (char ch : word.toCharArray()) {
					letters.offer(ch);
			    }
			}
		}
		
		return words;
	}
	
	public boolean updateQueue(ArrayList<String> oneAway) {
		List<String> words = new ArrayList<>();
		while(!ladder.peek().isEmpty()) {
			words.add(ladder.peek().pop());
		}
		ladder.poll();
		
		for(int e = 0; e < oneAway.size(); e++) {
			if(!alreadyAdded.contains(oneAway.get(e))) {
				Stack<String> addtoQueue = new Stack<>();
				for(int f = words.size()-1; f >= 0; f--) {
					addtoQueue.push(words.get(f));
				}
				addtoQueue.push(oneAway.get(e));
				
				ladder.offer(addtoQueue);
				alreadyAdded.add(oneAway.get(e));
				if(oneAway.get(e).equals(endWord)) {
					correctladder = addtoQueue;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean loopTillEndWord(){
		boolean foundLadder = false;
		
		if(firstWord.length() != endWord.length()) {
			return false;
		}
		
		if(firstWord.equals(endWord)) {
			correctladder.add(endWord);
			return true;
		}
		
		while(!ladder.isEmpty() && !foundLadder) {
			foundLadder = updateQueue(differByOneLetter(ladder.peek().peek()));
		}
		
		return foundLadder;
	}
	
	public List<String> getDict(){
		return dict;
	}
	
	public Queue<Stack<String>> getLadder(){
		return ladder;
	}
	
	public Stack<String> getCorrectLadder(){
		return correctladder;
	}
}
