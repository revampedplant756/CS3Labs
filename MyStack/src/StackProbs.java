import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class StackProbs {
	
	public StackProbs(){
		
	}
	
	public Stack<Integer> doubleUp(Stack<Integer> nums){
		Stack<Integer> temp = new Stack<>();
		
		while(!nums.isEmpty()) {
			temp.push(nums.pop());
		}
		while(!temp.isEmpty()) {
			nums.push(temp.peek());
			nums.push(temp.pop());
		}
		return nums;
	}
	
	public Stack<Integer> posAndNeg(Stack<Integer> nums){
		Stack<Integer> retStack = new Stack<>();
		Stack<Integer> posStorage = new Stack<>();
		
		while(!nums.isEmpty()) {
			if(nums.peek() < 0) {
				retStack.push(nums.pop());
			}
			else {
				posStorage.push(nums.pop());
			}
		}
		while(!posStorage.isEmpty()) {
			retStack.push(posStorage.pop());
		}
		
		return retStack;
	}
	
	public Stack<Integer> shiftByN(Stack<Integer> nums, int n){
		Stack<Integer> temp = new Stack<>();
		Stack<Integer> storeNums = new Stack<>();
		Stack<Integer> flip = new Stack<>();
		
		while(!nums.isEmpty()){
			temp.push(nums.pop());
		}
		for(int e = 0; e < n; e++) {
			storeNums.push(temp.pop());
		}
		while(!storeNums.isEmpty()){
			flip.push(storeNums.pop());
		}
		while(!temp.isEmpty()){
			nums.push(temp.pop());
		}
		while(!flip.isEmpty()){
			nums.push(flip.pop());
		}
		return nums;
	}
	
	public String reverseVowels(String str) {
		String[] vowels = new String[] {"a", "e", "i", "o", "u"};
		Stack<String> vowelStorage = new Stack<>();
		
		for(int e = 0; e < str.length(); e++) {
			if(Arrays.asList(vowels).contains(str.substring(e, e+1))) {
				vowelStorage.push(str.substring(e, e+1));
			}
		}

		for(int e = 0; e < str.length(); e++) {
			if(Arrays.asList(vowels).contains(str.substring(e, e+1))) {
				str = str.substring(0, e) + vowelStorage.pop() + str.substring(e+1);
			}
		}

		return str;
	}
	
	public boolean bracketBalance(String s) {
		Stack<String> balance = new Stack<>();
		String previousChar = s.substring(0, 1);
		balance.push(previousChar);
		
		for(int e = 1; e < s.length(); e++) {
			if(balance.peek().equals("(")) {
				if(s.substring(e, e+1).equals(")")) {
					balance.pop();
				}
				else {
					balance.push(s.substring(e, e+1));
				}
			}
			else if(balance.peek().equals("[")) {
				if(s.substring(e, e+1).equals("]")) {
					balance.pop();
				}
				else {
					balance.push(s.substring(e, e+1));
				}
			}
			else if(balance.peek().equals("\"")) {
				if(s.substring(e, e+1).equals("\"")) {
					balance.pop();
				}
				else {
					balance.push(s.substring(e, e+1));
				}
			}
		}
		return balance.isEmpty();
	}
}