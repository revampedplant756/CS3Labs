import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BoggleSolver
{
	
	HashSet<String> dictionary;
	
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver(String dictionaryName)
	{
		dictionary = new HashSet<String>();
		try {
			Scanner in  = new Scanner(new File(dictionaryName));
			
			while(in.hasNext()) {
				dictionary.add(in.nextLine().trim());
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{	
		HashSet<String> curWords = new HashSet<String>();
		for(int rows = 0; rows < board.rows(); rows++) {
			for(int cols = 0; cols < board.cols(); cols++) {
				for(String word : getAllValidWordsHelper(board, new HashSet<String>(), "", rows, cols, new HashSet<String>())) {
					curWords.add(word);
				}
			}
		}
		return curWords;
	}
	
	private Iterable<String> getAllValidWordsHelper(BoggleBoard board, HashSet<String> curWords, String curString, int r, int c, HashSet<String> visited){
		//	System.out.println(curString);
		if(curString.length() >= 3) {
			//System.out.println(curString);
			if(dictionary.contains(curString)) {
				curWords.add(curString);
			}
		}
		
		if(visited.contains(r + "" + c)) {
			return curWords;
		}
		
		HashSet<String> newVisited = new HashSet<String>();
		for(String word : visited) {
			newVisited.add(word);
		}
		newVisited.add(r + "" + c);
		
		if(r - 1 >= 0) {
			if(board.getLetter(r, c) == 'Q') {
				getAllValidWordsHelper(board, curWords, curString + "QU", r - 1, c, newVisited);
			}
			else {
				getAllValidWordsHelper(board, curWords, curString + board.getLetter(r, c), r - 1, c, newVisited);
			}
		}
		
		if(r + 1 < board.rows()) {
			if(board.getLetter(r, c) == 'Q') {
				getAllValidWordsHelper(board, curWords, curString + "QU", r + 1, c, newVisited);
			}
			else {
				getAllValidWordsHelper(board, curWords, curString + board.getLetter(r, c), r + 1, c, newVisited);
			}
		}
		
		if(c - 1 >= 0) {
			if(board.getLetter(r, c) == 'Q') {
				getAllValidWordsHelper(board, curWords, curString + "QU", r, c - 1, newVisited);
			}
			else {
				getAllValidWordsHelper(board, curWords, curString + board.getLetter(r, c), r, c - 1, newVisited);
			}
		}
		
		if(c + 1 < board.cols()) {
			if(board.getLetter(r, c) == 'Q') {
				getAllValidWordsHelper(board, curWords, curString + "QU", r, c + 1, newVisited);
			}
			else {
				getAllValidWordsHelper(board, curWords, curString + board.getLetter(r, c), r, c + 1, newVisited);
			}
		}
		
		if(c + 1 < board.cols() && r + 1 < board.rows()) {
			if(board.getLetter(r, c) == 'Q') {
				getAllValidWordsHelper(board, curWords, curString + "QU", r + 1, c + 1, newVisited);
			}
			else {
				getAllValidWordsHelper(board, curWords, curString + board.getLetter(r, c), r + 1, c + 1, newVisited);
			}
		}
		
		if(c - 1 >= 0 && r - 1 >= 0) {
			if(board.getLetter(r, c) == 'Q') {
				getAllValidWordsHelper(board, curWords, curString + "QU", r - 1, c - 1, newVisited);
			}
			else {
				getAllValidWordsHelper(board, curWords, curString + board.getLetter(r, c), r - 1, c - 1, newVisited);
			}	
		}
		
		if(c + 1 < board.cols() && r - 1 >= 0) {
			if(board.getLetter(r, c) == 'Q') {
				getAllValidWordsHelper(board, curWords, curString + "QU", r - 1, c + 1, newVisited);
			}
			else {
				getAllValidWordsHelper(board, curWords, curString + board.getLetter(r, c), r - 1, c + 1, newVisited);
			}
		}
		
		if(c - 1 >= 0 && r + 1 < board.rows()) {
			if(board.getLetter(r, c) == 'Q') {
				getAllValidWordsHelper(board, curWords, curString + "QU", r + 1, c - 1, newVisited);
			}
			else {
				getAllValidWordsHelper(board, curWords, curString + board.getLetter(r, c), r + 1, c - 1, newVisited);
			}
		}
		
		return curWords;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
		if(dictionary.contains(word)) {
			if(word.length() > 2 && word.length() <= 4) {
				return 1;
			}
			if(word.length() == 5) {
				return 2;
			}
			if(word.length() == 6) {
				return 3;
			}
			if(word.length() == 7) {
				return 5;
			}
			if(word.length() >= 8) {
				return 11;
			}
		}
		
		return 0;
	}
	
	public void print() {
		for(String e: dictionary) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH   = "./data/";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");
		
		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}
		
		System.out.println("Score = " + totalPoints); //should print 84
		
		//new BoggleGame(4, 4);
	}

}
