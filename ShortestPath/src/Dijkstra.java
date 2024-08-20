import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
	
	private Graph graph;
	
	public Dijkstra(Graph graph) {
		this.graph = graph;
	}
	
	public double distance(int source, int destination) {
		dijkstra(source, destination);
		return graph.adjList[graph.getVertexIndex(destination)].distance;
	}
	
	private void dijkstra(int source, int destination) {
		PriorityQueue<Vertex> dij = new PriorityQueue<>();
		HashSet<Integer> visited = new HashSet<>();
		
		Vertex[] list = graph.adjList;
		list[graph.getVertexIndex(source)].distance = 0;
		dij.add(list[graph.getVertexIndex(source)]);
		
		
		
		while(!dij.isEmpty()) {
			Vertex cur = dij.poll();
			for(Integer id : cur.edges) {
				if(!visited.contains(id)) {
					double curDist = list[graph.getVertexIndex(id)].distance;
					if(curDist > (cur.distance + graph.distance(cur.ID, id))) {
						list[graph.getVertexIndex(id)].distance = cur.distance + graph.distance(cur.ID, id);
					}
					dij.add(list[graph.getVertexIndex(id)]);
				}
			}
			visited.add(cur.ID);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Dijkstra test = new Dijkstra(new Graph("input6.txt"));
		System.out.println(test.distance(0, 5));
	}
}
