import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class WorstFit {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String[] input = in.nextLine().trim().split(" ");
		
		ArrayList<Integer> convertedInput = new ArrayList<>();
		
		for(String num: input) {
			try {
				convertedInput.add(Integer.parseInt(num));
			}
			catch(NumberFormatException x){
				System.out.print(x.getMessage());
			}
		}
		
		WorstFit test = new WorstFit(convertedInput);
		test.runWorstFit();
	}
	
	private ArrayList<Integer> files;
	private Queue<Disk> pq;
	
	public WorstFit(ArrayList<Integer> files) {
		this.files = files;
		pq = new PriorityQueue<>();
		pq.offer(new Disk());
	}
	
	public void setFiles(ArrayList<Integer> newFiles) {
		files = newFiles;
	}
	
	public void runWorstFit() {
		int sum = 0;
		for(Integer file : files) {
			sum += file;
			if(pq.peek().getStorage() >= file) {
				pq.peek().addFile(file);
			}
			else {
				Disk newDisk = new Disk();
				newDisk.addFile(file);
				pq.add(newDisk);
			}
		}
		
		System.out.println("Sum of all files divided by 1 mil: " + (((float)sum)/1000000.0));
		System.out.println("Number of Disks used: " + pq.size());
		
		if(files.size() < 100) {
			System.out.println(printHelper(pq));
		}
		pq.clear();
		pq.offer(new Disk());
		
	}
	
	private String printHelper(Queue<Disk> pq) {
		if(pq.isEmpty()) {
			return "";
		}
		System.out.println(pq.peek());
		String disk = pq.poll().toString();
		
		return printHelper(pq) + disk;
	}
	
}
