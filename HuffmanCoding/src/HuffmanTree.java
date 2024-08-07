import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HuffmanTree {
	
	private Node root;
	private HashMap<Integer, String> routes;
	
	public HuffmanTree(int[] counts) {
		
		routes = new HashMap<>();
		Queue<Node> queue = new PriorityQueue<Node>();
		
		for(int e = 0; e < counts.length; e++) {
			if(counts[e] != 0) {
				queue.offer(new Node(counts[e], (char) e));
			}
		}
		
		queue.offer(new Node(1, (char) 256));
		
		while(queue.size() > 1) {
			Node left = queue.poll();
			Node right = queue.poll();
			
			Node comb = new Node(left.weight + right.weight);
			comb.right = right;
			comb.left = left;
			queue.offer(comb);
		}
		
		root = queue.peek();
		
	}
	
	public HuffmanTree(String codeFile) {
		root = new Node(0);
		try {
			Scanner in = new Scanner(new File(codeFile));
			
			while(in.hasNextLine()) {
				char cur = (char) Integer.parseInt(in.nextLine());
				String path = in.nextLine().trim();
				
				root = buildTreeHelper(root, cur, path);
				
			}
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		
		//TreePrinter.printTree(root);
		
	}
	
	private Node buildTreeHelper(Node curNode, char set, String path) {
		if(path.length() == 0) {
			curNode.let = set;
			return curNode;
		}
		
		int bit = Integer.parseInt(path.substring(0, 1));
		
		if(bit == 0) {
			if(curNode.left == null) {
				curNode.left = buildTreeHelper(new Node(0), set, path.substring(1));
			}
			else {
				curNode.left = buildTreeHelper(curNode.left, set, path.substring(1));
			}
		}
		else {
			if(curNode.right == null) {
				curNode.right = buildTreeHelper(new Node(0), set, path.substring(1));
			}
			else {
				curNode.right = buildTreeHelper(curNode.right, set, path.substring(1));
			}
		}
		
		return curNode;
	}
	
	
	public void write(String fileName) {
		String outputFileName = fileName.substring(0, fileName.length()-4) + ".code";
		PrintWriter diskFile = null;
		
		try { 
	    diskFile = new PrintWriter(new File(outputFileName)); 
		}
		catch (IOException io) { 
		    System.out.println("Could not create file: " + outputFileName); 
		}

		diskFile.print(writeHelper(root, ""));

		diskFile.close();
	}
	
	private String writeHelper(Node curNode, String path) {
		if(curNode.haveChar()) {
			routes.put(((int) curNode.let), path);
			return String.valueOf(((int) curNode.let)) + "\n" + path;
		}
		
		String Right = "";
		String Left = "";
		
		if(curNode.left != null) {
			Left = writeHelper(curNode.left, path + "0");
		}
		
		if(curNode.right != null) {
			Right = writeHelper(curNode.right, path + "1");
		}
		
		return Left + "\n" + Right;
	}
	
	public void encode(BitOutputStream out, String file) {
		write(file);
		out = new BitOutputStream(file.substring(0, file.length()-3) + "short");
		
		try {
			Scanner in = new Scanner(new File(file));
			while(in.hasNextLine()) {
				String line = in.nextLine();
				
				for(int e = 0; e < line.length(); e++) {
					String bits = routes.get((int) line.charAt(e));
					
					//System.out.println(line.charAt(e));
					//System.out.println(bits);
					
					for(int f = 0; f < bits.length(); f++) {
						out.writeBit(Integer.parseInt(bits.substring(f, f+1)));
					}
				}
				
				if(in.hasNextLine()) {
					String lineBits = routes.get(10);
					
					for(int f = 0; f < lineBits.length(); f++) {
						out.writeBit(Integer.parseInt(lineBits.substring(f, f+1)));
					}
				}
			}
			
			String endBits = routes.get(256);
			
			//System.out.println("end");
			//System.out.println(endBits);
			
			for(int f = 0; f < endBits.length(); f++) {
				out.writeBit(Integer.parseInt(endBits.substring(f, f+1)));
			}
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		out.close();
		return;
	}
	
	public void decode(BitInputStream in, String outfile) {
		in = new BitInputStream(outfile.substring(0, outfile.length()-3) + "short");
		String outputFileName = outfile;
		PrintWriter diskFile = null;
		
		try { 
	    diskFile = new PrintWriter(new File(outputFileName)); 
		}
		catch (IOException io) { 
		    System.out.println("Could not create file: " + outputFileName); 
		}
		
		int bit = in.readBit();
		char cur = '\0';
		Node curNode = root;
		
		while((int) cur != 256) {
			//System.out.print(bit);
			
			if(bit == 0) {
				curNode = curNode.left;
			}
			else {
				curNode = curNode.right;
			}
			
			if(curNode.haveChar()) {
				if(((int) curNode.let) == 256) {
					break;
				}
				else {
					//System.out.println(String.valueOf(curNode.let));
					diskFile.print(String.valueOf(curNode.let));
					cur = curNode.let;
					curNode = root;
				}
			}
			
			bit = in.readBit();
		}

		diskFile.close();
		in.close();
		
	}
	
}
