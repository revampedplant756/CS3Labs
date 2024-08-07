import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class QueueProbs {
	
	public QueueProbs() {

	}
	
	public Queue<Integer> evenFirst(Queue<Integer> nums){
		Queue<Integer> odd = new LinkedList<>();
		Queue<Integer> even = new LinkedList<>();
		
		while(!nums.isEmpty()) {
			if(nums.peek() % 2 == 0) {
				even.offer(nums.poll());
			}
			else {
				odd.offer(nums.poll());
			}	
		}

		while(!odd.isEmpty()) {
			even.offer(odd.poll());
		}
		
		return even;
	}
	
	public boolean numPalindrome(Queue<Integer> nums) {
		Stack<Integer> backSide = new Stack<>();
		List<Integer> container = new ArrayList<>();
		Queue<Integer> frontSide = new LinkedList<>();
		
		boolean atBackSide = false;
		
		while(!nums.isEmpty()) {
			
			if(container.contains(nums.peek())) {
				atBackSide = true;
			}
			container.add(nums.peek());
			if(atBackSide) {
				backSide.push(nums.poll());
			}
			else {
				frontSide.offer(nums.poll());
			}
			
		}

		while(!backSide.isEmpty()) {
			if(backSide.peek() == frontSide.peek()){
				frontSide.poll();
			}
			backSide.pop();
		}
		
		if(frontSide.size() <= 1) {
			return true;
		}
		return false;
	}
	
	public Stack<Integer> primeSieve(int n){
		Queue<Integer> nums = new LinkedList<>();
		int size;
		Stack<Integer> prime = new Stack<>();
		
		for(int e = 2; e <=n ; e++) {
			nums.offer(e);
		}
		
		while(!nums.isEmpty()) {
			prime.push(nums.poll());
			size = nums.size();
			for(int e = 0; e < size; e++) {
				if(nums.peek() % prime.peek() == 0) {
					nums.poll();
				}
				else {
					nums.offer(nums.poll());
				}
			}
		}
		
		return prime;
	}
}
