import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
	
	private int v;
	private int e;
	public Vertex[] adjList;
	public int source;
	public int destination;
	
	public Graph(String file) throws FileNotFoundException {
		Scanner in = new Scanner(new File(file));
		
		int v = in.nextInt();
		int e = in.nextInt();
		in.nextLine();
		
		adjList = new Vertex[v];
		
		for(int f = 0; f < v; f++) {
			adjList[f] = new Vertex(in.nextInt(), in.nextInt(), in.nextInt());
			in.nextLine();
		}
		
		for(int f = 0; f < e; f++) {
			int source = in.nextInt();
			adjList[getVertexIndex(source)].edges.add(in.nextInt());
			
			in.nextLine();
		}
		
		source = in.nextInt();
		destination = in.nextInt();
	}
	
	public int getVertexIndex(int ID) {
		for(int w = 0; w < adjList.length; w++) {
			if(adjList[w].ID == ID) {
				return w;
			}
		}
		
		return -1;
	}
	
	public double distance(int from, int to) {
		Vertex v1 = adjList[getVertexIndex(from)];
		Vertex v2 = adjList[getVertexIndex(to)];
		
		return Math.sqrt(Math.pow((v2.x - v1.x) , 2) + Math.pow((v2.y - v1.y) , 2));
	}
	
	@Override
	public String toString() {
		String temp = "";
		for(Vertex ve : adjList) {
			temp += ve.toString() + "\n";
			for(Integer x : ve.edges) {
				temp += x + " ";
			}
			temp += "\n";
		}
		
		return temp;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Graph temp = new Graph("input6.txt");
		System.out.print(temp.distance(0, 1));
	}
}
