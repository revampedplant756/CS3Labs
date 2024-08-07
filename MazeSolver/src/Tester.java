import java.util.List;
import java.util.Stack;

public class Tester {
	public static void main(String[] args) {
		Maze x = new Maze();
		x.loadMaze("maze-2");
		/*System.out.print(x.toString() + "\n");
		List<Square> y = x.getNeighbors(x.getExit());
		for(int e = 0; e < y.size(); e++) {
			System.out.print(y.get(e).toString() + " ");
		}*/
		
		/*MyStack mine = new MyStack();
		Stack<Square> javas = new Stack<>();
		
		mine.push(x.getStart());
		javas.push(x.getStart());
		
		mine.push(x.getExit());
		javas.push(x.getExit());
		
		System.out.println(mine.toString());
		System.out.println(javas.toString());
		
		mine.pop();
		javas.pop();
		
		System.out.println(mine.toString());
		System.out.println(javas.toString());
		
		System.out.println(mine.peek());
		System.out.println(javas.peek());
		
		System.out.println(mine.size());
		System.out.println(javas.size());
		
		System.out.println(mine.isEmpty());
		System.out.println(javas.isEmpty());
		
		mine.clear();
		javas.clear();
		
		System.out.println(mine.toString());
		System.out.println(javas.toString());
		
		System.out.println(mine.isEmpty());
		System.out.println(javas.isEmpty());*/
	}
}
