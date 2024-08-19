import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Play {
	ArrayList<String> list;
	HashSet<Integer> visited;
	
	public Play(String file) throws FileNotFoundException {
		Scanner in = new Scanner(new File(file));
		
		
		
		int data = in.nextInt();
		
		for(int e = 0; e < data; e++) {
			in.nextLine();
			list = new ArrayList<>();
			list.add("0");
			visited = new HashSet<>();
			
			int[] processing = new int[3];
			int count = 0;
			
			for(String val : in.nextLine().trim().split(" ")) {
				processing[count] = Integer.parseInt(val);
				count++;
			}
			
			for(int f = 0; f < processing[0]; f++) {
				list.add("");
			}
			
			for(int f = 0; f < processing[1]; f++) {
				int[] path = new int[2];
				count = 0;
				
				for(String val : in.nextLine().trim().split(" ")) {
					path[count] = Integer.parseInt(val);
					count++;
				}
				
				list.set(path[0], list.get(path[0]) + path[1] + " ");
			}
			
			count = 0;
			for(int f = 0; f < processing[2]; f++) {
				int val = dominoes(Integer.parseInt(in.nextLine().trim()));
				count += val;
				
			}
			System.out.println(count);
		}
	}
	
	public int dominoes(int start) {
		if(start >= list.size() || start <= 0) {
			return 0;
		}
		
		
		
		Stack<Integer> dfs = new Stack<>();
		
		int count = 0;
		dfs.push(start);
		
		
		while(!dfs.isEmpty()) {
			Integer v = dfs.pop();
			
			if(!visited.contains(v)) {
				visited.add(v);
				count++;
				for(int e = 0; e < list.get(v).length(); e++) {
					String[] nums = list.get(v).trim().split(" ");
					
					for(String num : nums) {
						dfs.push(Integer.valueOf(num));
					}
					
				}
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Play x = new Play("play.dat");
	}
}
