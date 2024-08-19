import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PrimePath {
	
	private ArrayList<Integer> primeNumbers;
	private String[] adjList;
	
	public PrimePath() {
		primeNumbers = new ArrayList<>();
		adjList = new String[10000];
		
		for(int e = 1000; e < 10000; e++) {
			if(isPrime(e)) {
				primeNumbers.add(e);
			}
		}
		
		boolean oneDiff = false;
		boolean twoDiff = false;
		
		for(int cur: primeNumbers) {
			
			//System.out.println(cur);
			
			for(int compare: primeNumbers) {
				oneDiff = false;
				twoDiff = false;
				int temp = cur;
				int temp2 = compare;
				
				for(int e = 0; e < 4; e++) {
					if(temp%10 != temp2%10) {
						if(oneDiff == true) {
							twoDiff = true;
							continue;
						}
						else {
							oneDiff = true;
						}
					}
					
					temp = temp/10;
					temp2 = temp2/10;
				}
				
				if(oneDiff && !twoDiff) {
					if(adjList[cur] == null) {
						adjList[cur] = String.valueOf(compare) + " ";
					}
					else {
						adjList[cur] = adjList[cur] + String.valueOf(compare) + " ";
					}
				}
			}
		}
		
		Scanner in = new Scanner(System.in);
		System.out.print("Input : ");
		int start = in.nextInt();
		int end = in.nextInt();
		System.out.print("Output : " + path(start, end));
	}
	
	public int path(int start, int end) {
		if(start == end) {
			return 0;
		}
		
		if(adjList[start] == null || adjList[end] == null) {
			return -1;
		}
		
		int[] prev = new int[10000];
		Queue<Integer> bfs = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		
		bfs.add(start);
		visited.add(bfs.peek());
		
		while(!bfs.isEmpty()) {
			int cur = bfs.poll();
			
			if(cur == end) {
				break;
			}
			
			for(String num: adjList[cur].trim().split(" ")) {
				if(!visited.contains(Integer.parseInt(num))) {
					visited.add(Integer.parseInt(num));
					prev[Integer.parseInt(num)] = cur;
					bfs.add(Integer.parseInt(num));
				}
			}
		}
		
		Integer temp = end;
		int count = 0;
		
		while(temp != null) {
			temp = prev[temp];
			count++;
			if(temp == start) {
				return count;
			}
		}
		
		return -1;
	}
	
    private boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1)
            return false;
         // For n=2 or n=3 it will check
        if (n == 2 || n == 3)
            return true;
        // For multiple of 2 or 3 This will check 
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        // It will check all the others condition
        for (int i = 5; i <= Math.sqrt(n); i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }
    
    public static void main(String[] args) {
    	PrimePath x = new PrimePath();
    }
	
}
