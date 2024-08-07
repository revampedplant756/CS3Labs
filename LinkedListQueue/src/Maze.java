import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
	
	Square[][] maze;
	Square start;
	Square exit;
	
	public Maze() {
	}
	
	public boolean loadMaze(String fileName) {
		try {
			Scanner in = new Scanner(new File(fileName));
			int row = in.nextInt();
			int col = in.nextInt();
			int type;
			in.nextLine();
			maze = new Square[row][col];
			for(int e = 0; e < row; e++) {
				for(int f = 0; f < col; f++) {
					type = in.nextInt();
					Square s = new Square(e, f, type);
					maze[e][f] = s;
					if(type == 2) {
						start = s;
					}
					else if(type == 3) {
						exit = s;
					}
				}
				in.nextLine();
			}
			return true;
		}
		catch(IOException e){
			return false;
		}
	}
	
	public List<Square> getNeighbors(Square s){
		List<Square> neighbors = new ArrayList<Square>();
		if(s.getRow() != 0) {
			neighbors.add(maze[s.getRow()-1][s.getCol()]);
		}
		if(s.getCol() != maze[0].length-1) {
			neighbors.add(maze[s.getRow()][s.getCol()+1]);
		}
		if(s.getRow() != maze.length-1) {
			neighbors.add(maze[s.getRow()+1][s.getCol()]);
		}
		if(s.getCol() != 0) {
			neighbors.add(maze[s.getRow()][s.getCol()-1]);
		}
		return neighbors;
	}
	
	public Square getStart() {
		return start;
	}
	
	public Square getExit() {
		return exit;
	}
	
	public void reset(){
		for(int e = 0; e < maze.length; e++) {
			for(int f = 0; f< maze[0].length; f++) {
				maze[e][f].reset();
			}
		}
	}
	
	public String toString() {
		String ret = "";
		for(int e = 0; e < maze.length; e++) {
			for(int f = 0; f< maze[0].length; f++) {
				ret += maze[e][f].toString();
				if(f+1 != maze[0].length) {
					ret += " ";
				}
			}
			if(e+1 != maze[0].length) {
				ret += "\n";
			}
		}
		
		return ret;
	}
}
