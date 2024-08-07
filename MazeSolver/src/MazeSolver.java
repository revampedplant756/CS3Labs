import java.util.List;

public abstract class MazeSolver {
	
	protected Maze maze;
	protected boolean exitFound;
	protected boolean noPath;
	
	public MazeSolver(Maze maze) {
		this.maze = maze;
		exitFound = false;
		noPath = false;
		makeEmpty();
		add(this.maze.getStart());
	}
	
	abstract void makeEmpty();
	
	abstract boolean isEmpty();
	
	abstract void add(Square s);
	
	abstract Square next();
	
	abstract void popTop();
	
	abstract void print();
	
	public boolean isSolved() {
		if(isEmpty()) {
			noPath = true;
			return true;
		}
		if(next().equals(maze.getExit())) {
			exitFound = true;
			return true;
		}
		return false;
	}
	
	public void step() {
		print();
		if(next().getStatus() == Square.WORKING) {
			next().setStatus(Square.EXPLORED);
		}
		else if (next().getType() != Square.START){
			while(next().getStatus() == Square.EXPLORED) {
				popTop();
			}
		}
		List<Square> neighbors = maze.getNeighbors(next());
		for(int e = 0; e < neighbors.size(); e++) {
			if(neighbors.get(e).getStatus() == Square.UNKNOWN || neighbors.get(e).getType() == Square.EXIT){
				if(neighbors.get(e).getStatus() == Square.UNKNOWN) {
					neighbors.get(e).setStatus(Square.WORKING);
				}
				add(neighbors.get(e));
			}
		}
		if(next().getType() == Square.START) {
			popTop();
		}
		print();
	}
	
	public String getPath() {
		if(!isSolved()) {
			return "not yet solved";
		}
		else if(noPath) {
			return "unsolvable";
		}
		else if(exitFound) {
			return "solved";
		}
		return "";
	}
	
	public void solve() {
		while(getPath().equals("not yet solved")) {
			step();
		}
	}
}
