import java.util.LinkedList;
import java.util.Queue;

public class Runner {
	public static void main(String[] args) {
		QueueProbs x = new QueueProbs();
		
		int[] test1 = new int[] {3, 5, 4, 17, 6, 83, 1, 84, 16, 37};
		Queue<Integer> testQueue = arrayToQueue(test1);
		
		System.out.println(testQueue.toString());
		testQueue = x.evenFirst(testQueue);
		System.out.println(testQueue.toString());
		
		int[] test2 = new int[] {3, 8, 17, 9, 17, 8, 3};
		testQueue = arrayToQueue(test2);
		
		int[] test3 = new int[] {3, 10, 17, 9, 17, 8, 3};
		testQueue = arrayToQueue(test3);
		
		System.out.println(x.numPalindrome(testQueue));
		
		System.out.println(x.primeSieve(100).toString());
		
	}
	
	private static Queue<Integer> arrayToQueue(int[] x){
		Queue<Integer> testQueue = new LinkedList<>();
		for(int e: x) {
			testQueue.offer(e);
		}
		
		return testQueue;
	}
}
