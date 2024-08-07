
public class MazeSolverWithQueue extends MazeSolver{
	
	MyQueue<Square> worklist;
	
	public MazeSolverWithQueue(Maze maze) {
		super(maze);
	}

	@Override
	void makeEmpty() {
		worklist = new MyQueue<Square>();;
	}

	@Override
	boolean isEmpty() {
		return worklist.isEmpty();
	}

	@Override
	void add(Square s) {
		worklist.offer(s);
	}

	@Override
	Square next() {
		return worklist.peek();
	}

	@Override
	void popTop() {
		worklist.poll();
		
	}

	@Override
	void print() {
		System.out.println(worklist.toString());
	}
	
}
