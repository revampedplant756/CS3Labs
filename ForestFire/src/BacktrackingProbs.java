import java.util.Arrays;
import java.util.List;

public class BacktrackingProbs {
	
	public static void main(String[] args) {
		BacktrackingProbs test = new BacktrackingProbs();
		test.printBinary(3);
		System.out.println("\n");
		
		test.climbStairs(4);
		System.out.println();
		
		test.campsite(2, 1);
		System.out.println();
		
		System.out.println(test.getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19));
		System.out.println();
		
		System.out.println(test.makeChange(100));
		System.out.println();
		
		System.out.println(test.longestCommonSub("ollow", "ello"));
	}
	
	public void printBinary(int digits) {
		String soFar = "";
		printBinaryHelper(digits, soFar);
	}
	
	private void printBinaryHelper(int digits, String soFar) {
		if(soFar.length() == digits) {
			System.out.print(soFar + " ");
			return;
		}
		
		printBinaryHelper(digits, soFar + "0");
		printBinaryHelper(digits, soFar + "1");
		return;
	}
	
	public void climbStairs(int steps) {
		climbStairsHelper(steps, 0, "");
	}
	
	private void climbStairsHelper(int steps, int sum, String soFar) {
		if(sum == steps) {
			System.out.println(soFar.substring(2));
			return;
		}
		climbStairsHelper(steps, sum + 1, soFar + ", 1");
		
		if(sum+2 <= steps) {
			climbStairsHelper(steps, sum + 2, soFar + ", 2");
		}
	}
	
	public void campsite(int x, int y) {
		campsiteHelper(x, y, 0, 0, "");
	}
	
	private void campsiteHelper(int x, int y, int curX, int curY, String soFar) {
		if(curX == x && curY == y) {
			System.out.println(soFar);
			return;
		}
		if(curX < x) {
			campsiteHelper(x, y, curX+1, curY, soFar + "E ");
		}
		if(curY < y) {
			campsiteHelper(x, y, curX, curY+1, soFar + "N ");
		}
		if(curY < y && curX < x) {
			campsiteHelper(x, y, curX+1, curY+1, soFar + "NE ");
		}
	}
	
	public int getMax(List<Integer> nums, int limit) {
		return getMaxHelper(nums, limit, 0, 0);
	}
	
	private int getMaxHelper(List<Integer> nums, int limit, int curSum, int curIndex){
		if(curIndex >= nums.size()) {
			if(curSum > limit){
				return Integer.MIN_VALUE;
			}
			else {
				return curSum;
			}
		}
		
		int path1 = getMaxHelper(nums, limit, curSum+nums.get(curIndex), curIndex+1);
		int path2 = getMaxHelper(nums, limit, curSum, curIndex+1);
	
		if(path1 > path2) {
			return path1;
		}
		return path2;
	}
	
	public int makeChange(int amount) {
		return makeChangeHelper(amount, 0, 25);
	}
	
	private int makeChangeHelper(int amount, int curAmount, int type) {
		if(curAmount > amount) {
			return 0;
		}
		if(curAmount == amount) {
			return 1;
		}
		int pathQuarter = 0;
		int pathDime = 0;
		int pathNickel = 0;
		int pathPenny = 0;
		if(type == 25) {
			pathQuarter = makeChangeHelper(amount, curAmount+25, 25);
			pathDime = makeChangeHelper(amount, curAmount+10, 10);
			pathNickel = makeChangeHelper(amount, curAmount+5, 5);
			pathPenny = makeChangeHelper(amount, curAmount+1, 1);
		}
		if(type == 10) {
			pathDime = makeChangeHelper(amount, curAmount+10, 10);
			pathNickel = makeChangeHelper(amount, curAmount+5, 5);
			pathPenny = makeChangeHelper(amount, curAmount+1, 1);
		}
		if(type == 5) {
			pathNickel = makeChangeHelper(amount, curAmount+5, 5);
			pathPenny = makeChangeHelper(amount, curAmount+1, 1);
		}
		if(type == 1) {
			pathPenny = makeChangeHelper(amount, curAmount+1, 1);
		}
		
		return pathPenny + pathNickel + pathDime + pathQuarter;
	}
	
	public String longestCommonSub(String A, String B) {
		if(A.substring(0, 1).equals(B.substring(0, 1))) {
			return longestCommonSubHelper(A, B, "", true);
		}
		
		return longestCommonSubHelper(A, B, "", false);
	}
	
	private String longestCommonSubHelper(String A, String B, String curLongest, boolean startWithSame) {
		String longest1 = "";
		String longest2 = "";
		if(startWithSame) {
			if(A.length() == 0 || B.length() == 0) {
				return curLongest;
			}
			if(B.indexOf(A.substring(0, 1)) != -1) {
				longest1 = longestCommonSubHelper(A.substring(1), B.substring(B.indexOf(A.substring(0, 1))), curLongest + A.substring(0, 1), startWithSame);
			}
			else {
				longest1 = longestCommonSubHelper(A.substring(1), B.substring(B.length()), curLongest, startWithSame);
			}
			if(A.length() >= 2) {
				curLongest = "";
				if(B.indexOf(A.substring(1, 2)) != -1) {
					longest2 = longestCommonSubHelper(A.substring(2), B.substring(B.indexOf(A.substring(1, 2))), curLongest + A.substring(1, 2), startWithSame);
				}
				else {
					longest2 = longestCommonSubHelper(A.substring(1), B.substring(B.length()), curLongest, startWithSame);
				}
			}
			
			if(longest1.length() > longest2.length()) {
				return longest1;
			}
			
			return longest2;
		}
		String path1 = longestCommonSubHelper(A, B.substring(1), curLongest, true);
		String path2 = longestCommonSubHelper(A.substring(0, 1), B, curLongest, true);
		
		if(path1.length() > path2.length()) {
			return path1;
		}
		return path2;
	}
}
