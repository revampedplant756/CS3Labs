import java.io.File;	
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//import MyBST.BSTNode;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree
{
	
	private BTNode root;
	private BTNode curNode;
	private String fileName;
	
	private class BTNode{
		String val;
		BTNode left, right;
		
		public BTNode(String val){
			this.val = val;
			left = right = null;
		}
		
		public String toString() {return "" + this.val; }
	}
	
	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
	public GameTree(String fileName)
	{
		try {
			Scanner in = new Scanner(new File(fileName));
			root = loadGameHelper(root, in.nextLine().trim(), in);
			curNode = root;
			this.fileName = fileName;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private BTNode loadGameHelper(BTNode curNode, String input, Scanner in) {
		if(input.endsWith("?")) {
			curNode = new BTNode(input);
			curNode.left = loadGameHelper(curNode.left, in.nextLine().trim(), in);
			curNode.right = loadGameHelper(curNode.right, in.nextLine().trim(), in);
			return curNode;
		}
		curNode = new BTNode(input);
		return curNode;
	}
	
	

	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA)
	{
		curNode.right = new BTNode(curNode.val);
		curNode.val = newQ;
		curNode.left = new BTNode(newA);
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer()
	{
		if(!getCurrent().endsWith("?")) {
			return true;
		}

		return false;
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent()
	{
		return curNode.val; //replace
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo)
	{
		if(yesOrNo == Choice.Yes) {
			curNode = curNode.left;
		}
		else {
			curNode = curNode.right;
		}
		return;
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart()
	{
		curNode = root;
	}

	@Override
	public String toString()
	{
		return toStringHelper(root, 0, "");
	}
	
	private String toStringHelper(BTNode curNode, int level, String curString) {
		if(curNode.right == null && curNode.left == null) {
			String dashes = "";
			for(int e = 0 ; e < level; e++) {
				dashes += "- ";
			}
			return curString += dashes + curNode.val + "\n";
		}
		
		String right = toStringHelper(curNode.right, level+1, curString);
		String left = toStringHelper(curNode.left, level+1, curString);
		
		String dashes = "";
		for(int e = 0 ; e < level; e++) {
			dashes += "- ";
		}
		
		return right + (dashes + curNode.val + "\n") + left;
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame()
	{
		PrintWriter diskFile = null;
		try { 
	    diskFile = new PrintWriter(new File(fileName)); 
		}
		catch (IOException io) { 
	    System.out.println("Could not create file: " + fileName); 
		}
		//diskFile has the familiar print and println methods of System.out
		//see the PrintWriter class' API for more info
		//diskFile.print("First line has one number: ");
		//diskFile.println(123);
		
		diskFile.print(saveGameHelper(root));
		//MUST explicitly close file! You will lose all data and end up with an empty file if not 
		diskFile.close();
	}
	
	public String saveGameHelper(BTNode curNode) {
		String curString = "";
		if(curNode.val.endsWith("?")) {
			curString += curNode.val + "\n";
			curString += saveGameHelper(curNode.left) + "\n";
			curString += saveGameHelper(curNode.right);
			return curString;
		}
		
		return curNode.val;
	}
}
