import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
	
	public int x;
	public int y;
	public int ID;
	public List<Integer> edges;
	public double distance;
	
	public Vertex(int ID, int x, int y){
		this.x = x;
		this.y = y;
		this.ID = ID;
		edges = new ArrayList<>();
		distance = Double.POSITIVE_INFINITY;
	}
	
	@Override
	public String toString() {
		return "ID: " + ID + ", x: " + x + ", y: " + y;
	}
	
	@Override
	public int compareTo(Vertex o) {
		if(this.distance < o.distance) {
			return -1;
		}
		
		if(this.distance > o.distance) {
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public int hashCode() {
		return ID;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.ID == ((Vertex) o).ID) {
			return true;
		}
		
		return false;
	}
	
}
