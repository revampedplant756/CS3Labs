
public class Node implements Comparable<Node>{
	
	public int weight;
	public char let;
	public Node left;
	public Node right;
	
	public Node(int weight) {
		this.weight = weight;
		let = '\0';
		left = null;
		right = null;
	}
	
	public Node(int weight, char let) {
		this(weight);
		this.let = let;	
	}
	
	public boolean haveChar() {
		return ((left == null) && (right == null));
	}

	@Override
	public int compareTo(Node o) {
		
		if(this.weight < o.weight) {
			return -1;
		}
		else if(this.weight > o.weight) {
			return 1;
		}
		
		return 0;
	}
	
	public String toString() {
		
		if(haveChar()) {
			return String.valueOf((int) let);
		}
		return String.valueOf(weight);
	}

}
