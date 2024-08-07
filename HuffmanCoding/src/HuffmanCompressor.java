import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanCompressor {
	
	public static void main(String[] args) {
		HuffmanCompressor.compress("Hamlet.txt");
		
		HuffmanCompressor.expand("Hamlet.code", "Hamlet.new");
	}
	
	public HuffmanCompressor() {
		
	}
	
	public int[] countFrequencies(String file) {
		int[] counts = new int[256];
		try {
			Scanner in = new Scanner(new File(file));
			while(in.hasNextLine()) {
				String line = in.nextLine();
				for(int e = 0; e < line.length(); e++) {
					counts[line.charAt(e)]++;
				}
				if(in.hasNextLine()) {
					counts[10]++;
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		return counts;
	}
	
	public static void compress(String file) {
		int[] freq = new HuffmanCompressor().countFrequencies(file);
		
		HuffmanTree tree = new HuffmanTree(freq);
		
		tree.encode(null, file);
		
	}
	
	public static void expand(String codeFile, String newFile) {
		HuffmanTree rebuild = new HuffmanTree(codeFile);
		
		rebuild.decode(null, newFile);
	}
}
