import java.util.Stack;

public class RecursionProbs {
	public static void main(String[] args) {
		
		RecursionProbs test = new RecursionProbs();
		
		System.out.println(test.sumReciprocals(10));
		
		System.out.println(test.productOfEvens(4));
		
		System.out.println(test.conversion(1453, 8));
		
		System.out.println(test.matchingDigits(298892, 7892));
		
		Stack<Integer> tester = new Stack<>();
		tester.push(3);
		tester.push(7);
		tester.push(12);
		tester.push(9);
		test.doubleUp(tester);
		System.out.println(tester.toString());
		
		
		test.printThis(8);
		System.out.println();
		
		test.printNums2(9);
		System.out.println();
	}
	
	public RecursionProbs() {
		
	}
	
	public double sumReciprocals(int n) {
		if(n == 1) {
			return 1/n;
		}
			
		return 1/(double)n + sumReciprocals(n-1);
	}
	
	public int productOfEvens(int n) {
		if(n == 1) {
			return n*2;
		}
			
		return n*2 *productOfEvens(n-1);
	}
	
	public String conversion(int num, int base) {
		int newNum = num/base;
		int remainder = num % base;
		if(newNum == 0) {
			return Integer.toString(remainder);
		}
		
		return conversion(newNum, base) + Integer.toString(remainder);
	}
	
	public int matchingDigits(int a, int b) {
		String conA = Integer.toString(a);
		String conB = Integer.toString(b);
		
		if(conA.contains(conB)) {
			return conB.length();
		}
		else if(conB.contains(conA)) {
			return conA.length();
		}
		if(conA.length() < conB.length()) {

			return matchingDigits(a, b%((int)(Math.pow(10, conB.length()-1))));
		}
		else if(conA.length() > conB.length()){
			return matchingDigits(a%((int)(Math.pow(10, conA.length()-1))), b);
		}
		else {
			if(conA.length()-1 == 0 || conB.length()-1 == 0) {
				return 0;
			}
			return matchingDigits(a%((int)(Math.pow(10, conA.length()-1))), b%((int)(Math.pow(10, conB.length()-1))));
		}
	}
	
	public void doubleUp(Stack<Integer> nums) {
		int cur = nums.pop();
		if(nums.isEmpty()) {
			nums.push(cur);
			nums.push(cur);
			return;
		}
		doubleUp(nums);
		nums.push(cur);
		nums.push(cur);
		return;
	}
	
	public void printThis(int n) {
		if(n == 1) {
			System.out.print("*");
			return;
		}
		if(n == 2) {
			System.out.print("**");
			return;
		}
		System.out.print("<");
		printThis(n-2);
		System.out.print(">");
		return;
	}
	
	public void printNums2(int n) {
		if(n == 1) {
			System.out.print("1");
			return;
		}
		if(n == 2) {
			System.out.print("1 1");
			return;
		}
		System.out.print((n+1)/2 + " ");
		printNums2(n-2);
		System.out.print(" " + (n+1)/2);
		return;
	}
}
