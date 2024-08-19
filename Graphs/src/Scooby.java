import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Scooby {
	HashMap<String, String> map;
	
	public Scooby(String[] edges) {
		map = new HashMap<>();
		
		for(String edge: edges) {
			String vertex1 = edge.substring(0,1);
			String vertex2 = edge.substring(1);
			
			if(map.containsKey(vertex1)) {
				map.put(vertex1, map.get(vertex1)+vertex2);
			}
			else {
				map.put(vertex1, vertex2);
			}
			
			if(map.containsKey(vertex2)) {
				map.put(vertex2, map.get(vertex2)+vertex1);
			}
			else {
				map.put(vertex2, vertex1);
			}
		}
 	}
	
	public boolean doesPathExist(String start, String end) {
		Stack<String> dfs = new Stack<>();
		HashSet<String> visited = new HashSet<>();
		
		dfs.push(start);
		String v;
		
		while(!dfs.isEmpty()) {
			v = dfs.pop();
			
			if(v.equals(end)) {
				return true;
			}
			if(!visited.contains(v)) {
				visited.add(v);
				if(!map.containsKey(v)) {
					return false;
				}
				
				String rooms = map.get(v);
				
				for(int e = 0; e < rooms.length(); e++) {
					dfs.add(rooms.substring(e, e+1));
				}
			}
			
		}
		
		return false;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("scooby.dat"));
		
		int data = in.nextInt();
		in.nextLine();
		for(int e = 0; e < data; e++) {
			String[] edges = in.nextLine().trim().split(" ");
			String path = in.nextLine().trim();
			
			Scooby test = new Scooby(edges);
			
			if(test.doesPathExist(path.substring(0, 1), path.substring(1))) {
				System.out.println("yes");
			}
			else {
				System.out.println("no");
			}
		}
		
	}
}
