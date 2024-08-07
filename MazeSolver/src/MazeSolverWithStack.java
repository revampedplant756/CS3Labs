
public class MazeSolverWithStack extends MazeSolver{

	MyStack worklist;
	
	public MazeSolverWithStack(Maze maze) {
		super(maze);
	}

	@Override
	void makeEmpty() {
		worklist = new MyStack();
	}

	@Override
	boolean isEmpty() {
		if(worklist.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	void add(Square s) {
		worklist.push(s);
		
	}

	@Override
	Square next() {
		return worklist.peek();
	}

	@Override
	void popTop() {
		worklist.pop();
		
	}

	@Override
	void print() {
		System.out.println(worklist.toString());
	}

}
