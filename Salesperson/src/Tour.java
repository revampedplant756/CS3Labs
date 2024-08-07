public class Tour 
{
	
	public static void main(String[] args) {
		Point a = new Point(100, 100);
		Point b = new Point(500, 100);
		Point c = new Point(500, 500);
		Point d = new Point(100, 500);
		
		Tour squareTour = new Tour(a, b, c, d);
		
		squareTour.show();
		System.out.println(squareTour.distance());
		
		StdDraw.setXscale(0, 600);
		StdDraw.setYscale(0, 600);
		
		squareTour.draw();
	}
	
	
	private class Node{
		Point p;
		Node next;
		
		Node(Point p){
			this.p= p;		}
	}
	
	private Node head;
	
	/** create an empty tour */
	public Tour()
	{
		head = null;
	}
	
	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d)
	{
		head = new Node(a);
		head.next = new Node(b);
		head.next.next = new Node(c);
		head.next.next.next = new Node(d);
		head.next.next.next.next = head;
	}
	
	/** print tour (one point per line) to std output */
	public void show()
	{
		Node temp = head;
		if(temp == null) {
			return;
		}
		do {
			System.out.println(temp.p.toString());
			temp = temp.next;
		}
		while(temp != head);
		
	}
	
	/** draw the tour using StdDraw */
	public void draw()
	{
		Node temp = head;
		if(temp == null) {
			return;
		}
		do {
			temp.p.drawTo(temp.next.p);
			temp = temp.next;
		}
		while(temp != head);
	}
	
	/** return number of nodes in the tour */
	public int size()
	{
		Node temp = head;
		int count = 0;
		if(temp == null) {
			return 0;
		}
		
		do {
			count++;
			temp = temp.next;
		}
		while(temp != head);
		return count;
	}
	
	/** return the total distance "traveled", from start to all nodes and back to start */
	public double distance()
	{
		Node temp = head;
		Double dist = 0.0;
		if(temp == null) {
			return 0;
		}
		do {
			dist += temp.p.distanceTo(temp.next.p);
			temp = temp.next;
		}
		while(temp != head);
		return dist;
	}
	
	/** insert p using nearest neighbor heuristic */
    public void insertNearest(Point p) 
    {
    	if(size() == 0) {
    		head = new Node(p);
    		head.next = head;
    	}
    	
        int size = size();
        double smallestDist = Integer.MAX_VALUE;
        Node temp  = head;
        Node smallestPoint = null;
        do {
        	if(p.distanceTo(temp.p) < smallestDist) {
        		smallestDist = p.distanceTo(temp.p);
        		smallestPoint = temp;
        	}
        	temp = temp.next;
        	if(temp == smallestPoint) {
        		Node newPoint = new Node(p);
        		newPoint.next = temp.next;
        		temp.next = newPoint;
        	}
        }
        while(size == size());
    }

	/** insert p using smallest increase heuristic */
    public void insertSmallest(Point p) 
    {
    	if(size() == 0) {
    		head = new Node(p);
    		head.next = head;
    	}
    	
        int size = size();
        double smallestDist = Integer.MAX_VALUE;
        Node temp  = head;
        Node pointOfInsertion = null;
        do {
        	Node newPoint = new Node(p);
    		newPoint.next = temp.next;
    		temp.next = newPoint;
        	if(distance() < smallestDist) {
        		smallestDist = distance();
        		pointOfInsertion = temp;
        	}
        	temp.next = newPoint.next;
        	temp = temp.next;
        	if(temp == pointOfInsertion) {
        		newPoint = new Node(p);
        		newPoint.next = temp.next;
        		temp.next = newPoint;
        	}
        }
        while(size == size());
    }
}