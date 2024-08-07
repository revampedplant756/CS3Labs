import java.util.Stack;

public class Runner {
	
	public static void main(String[] args) {
		/*StackProbs instance = new StackProbs();
		Stack<Integer> test1 = makeStack(new int[] {1, 2, 3, 4, 5, 6, 7, 8});
		
		//Stack<Integer> y = instance.doubleUp(x);
		instance.doubleUp(test1);
		System.out.print(test1.toString() + "\n");
		
		Stack<Integer> test2 = makeStack(new int[] {1, 2, -3, -4, 5, -6, 7, -8});
		test2 = instance.posAndNeg(test2);
		System.out.print(test2.toString() + "\n");
		
		Stack<Integer> test3 = makeStack(new int[] {1, 2, -3, -4, 5, -6, 7, -8});
		System.out.print(test3.toString() + "\n");
		instance.shiftByN(test3, 3);
		System.out.print(test3.toString() + "\n");
		
		String test4 = "hello how are you";
		System.out.print(test4.toString() + "\n");
		test4 = instance.reverseVowels(test4);
		System.out.print(test4.toString() + "\n");
		
		String test5 = "(\"(([()])))\")";
		boolean test5Result = instance.bracketBalance(test5);
		System.out.print(test5Result + "\n");
		
		String test6 = "(\"([()[]()])()\")";
		boolean test6Result = instance.bracketBalance(test6);
		System.out.print(test6Result + "\n");*/
		
		double[] test = new double[2];
		System.out.print(test[0]);
	}
	
	public static Stack<Integer> makeStack(int[] nums){
		Stack<Integer> stack = new Stack<>();
		for(int num: nums) {
			stack.push(num);
		}
		return stack;
	}
	
}
