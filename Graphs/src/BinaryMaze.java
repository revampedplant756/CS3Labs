import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BinaryMaze {
	
	private int rows;
	private int cols;
	private int[][] matrix;
	
	public BinaryMaze(String file) throws FileNotFoundException {
		Scanner in = new Scanner(new File(file));
		
		rows = in.nextInt();
		cols = in.nextInt();
		int testCases = in.nextInt();
		in.nextLine();
		
		int[][] maze = new int[rows][cols];
		matrix = new int[rows*cols][rows*cols];
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				maze[r][c] = in.nextInt();
			}
			in.nextLine();
		}
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(maze[r][c] == 1) {
					if(r != 0) {
						if(maze[r-1][c] == 1) {
							matrix[toMatrix(r, c)][toMatrix(r-1, c)] = 1;
						}
					}
					
					if(r < maze.length-1) {
						if(maze[r+1][c] == 1) {
							matrix[toMatrix(r, c)][toMatrix(r+1, c)] = 1;
						}
					}
					
					if(c != 0) {
						if(maze[r][c-1] == 1) {
							matrix[toMatrix(r, c)][toMatrix(r, c-1)] = 1;
						}
					}
					
					if(c < maze.length-1) {
						if(maze[r][c+1] == 1) {
							matrix[toMatrix(r, c)][toMatrix(r, c+1)] = 1;
						}
					}
				}
			}
		}
		
		for(int e = 0; e < testCases; e++) {
			System.out.println(findPath(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
			in.nextLine();
		}
	}
	
	public int findPath(int sR, int sC, int dR, int dC) {
		Queue<Location> bfs = new LinkedList<>();
		HashSet<Location> visited = new HashSet<>();
		
		bfs.offer(new Location(sR, sC));
		Location dest = new Location(dR, dC);
		
		while(!bfs.isEmpty()) {
			visited.add(bfs.peek());
			Location cur = bfs.poll();
			if(cur.equals(dest)) {
				return cur.dist;
			}
			
			List<Location> adj = new ArrayList<>();
			
			for(int x  = 0; x < matrix[toMatrix(cur.r, cur.c)].length; x++) {
 				if(matrix[toMatrix(cur.r, cur.c)][x] == 1 && !visited.contains(new Location(toMaze(x)))) {
 					Location temp = new Location(toMaze(x));
 					temp.setDist(cur.dist+1);
					bfs.add(temp);
				}
			}
		}
		
		return -1;
		
	}
	
	private class Location {
		
		public int r;
		public int c;
		public int dist;
		
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
			dist = 0;
		}
		
		public Location(int[] comb) {
			r = comb[0];
			c = comb[1];
			dist = 0;
		}
		
		public void setDist(int x) {
			dist = x;
			return;
		}
		
		@Override
		public int hashCode() {
			return r+c;
		}
		
		@Override
		public boolean equals(Object x) {
			if(this.r == ((Location)x).r && this.c == ((Location)x).c) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public String toString() {
			return "(" + r + ", " + c + ")";
		}
		
	}
	
	private int toMatrix(int r, int c) {
		return (r * cols) + c;
	}
	
	private int[] toMaze(int index) {
		int[] temp = new int[2];
		
		temp[0] = index / cols;
		temp[1] = index - (cols*temp[0]);
		
		return temp;
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		BinaryMaze test = new BinaryMaze("maze.dat");
	}
	
}
