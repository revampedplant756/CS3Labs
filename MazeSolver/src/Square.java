public class Square 
{
	//These values are used to denote what type of Square this is in the maze
	final static int EMPTY = 0; //an empty space
	final static int WALL  = 1; //a wall
	final static int START = 2; //the start 
	final static int EXIT  = 3; //the exit

	//These values indicate the status of a particular Square while the maze is being solved, for the GUI
	final static char WORKING      = 'o'; //currently on the work list (the stack)
	final static char EXPLORED     = '.'; //done, already explored
	final static char ON_EXIT_PATH = 'x'; //on the exit path, USED IN A LATER PROJECT
	final static char UNKNOWN      = '_'; //not known / visited yet (empty cells only)

	private int  row, col; //r, c location in the maze
	private int  type;     //type of this square, e.g. empty, wall, etc.
	private char status;   //the status of a room being explored, shown by the GUI
	
	public Square(int row, int col, int type) {
		this.row = row;
		this.col = col;
		this.type = type;
		if(type == 0) {
			status = '_';
		}
	}
	
	public String toString() {
		switch(type) {
			case 0:
				return Character.toString(status);
			case 1:
				return "#";
			case 2:
				return "S";
			case 3:
				return "E";
		}
		
		return "";
	}
	
	public boolean equals(Object obj) {
		try {
			Square x = (Square)obj;
			if(this.row == x.row && this.col == x.col) {
				return true;
			}
			return false;
		}
		catch(ClassCastException e) {
			return false;
		}
	}
	
	public void reset() {
		if(type == 0) {
			status = '_';
		}
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getType() {
		return type;
	}
	
	public char getStatus() {
		return status;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}
}