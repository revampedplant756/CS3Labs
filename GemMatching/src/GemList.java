public class GemList 
{
	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99); //not a mistake, should still work 
		System.out.println("\n" + list); 
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
	    list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
	    System.out.println("\n" + list); 
	    System.out.println("size = " + list.size() + ", score = " + list.score()); 
	    list.draw(0.6);
	  
	    list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
	    System.out.println("\n" + list);
	    System.out.println("size = " + list.size() + ", score = " + list.score());
	    list.draw(0.5);
	  
	    list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
	    System.out.println("\n" + list);
	    System.out.println("size = " + list.size() + ", score = " + list.score());
	    list.draw(0.4);
	  
	    list.insertBefore(new Gem(GemType.GREEN, 50), 2);
	    System.out.println("\n" + list);
	    System.out.println("size = " + list.size() + ", score = " + list.score());
	    list.draw(0.3);
		 	
	}
	
	private class Node{
		private Gem gem;
		private Node next;
		
		Node(Gem gem){
			this.gem = gem;
		}
		
		public String toString() {
			return gem.toString();
		}
	}
	
	private Node head;
	private int size;
	
	public GemList() {
		head = null;
		size = 0;
	}
	
	public GemList(Gem gem) {
		head = new Node(gem);
		size = 1;
	}
	
	public int size() {
		return size;
	}
	
	public void draw(double y) {
		Node temp = head;
		int count = 0;
		while(temp != null) {
			temp.gem.draw(GemGame.indexToX(count), y);
			count++;
			temp = temp.next;
		}
	}
	
	public String toString() {
		if(size == 0) {
			return "<none>";
		}
		
		Node temp = head;
		String ret = "";
		
		while(temp != null) {
			ret += temp.gem.toString();
			if(temp.next != null) {
				ret += " -> ";
			}
			temp = temp.next;
		}
		
		return ret;
	}
	
	public void insertBefore(Gem gem, int index) {
		if(size == 0) {
			head = new Node(gem);
			size++;
			return;
		}
		if(index == 0) {
			Node stor = new Node(head.gem);
			stor.next = head.next;
			head.next = stor;
			head.gem = gem;
			size++;
			return;
		}
		Node temp = head;
		int count = 0;
		while(temp.next != null) {
			if(count+1 == index) {
				Node stor = new Node(gem);
				stor.next = temp.next;
				temp.next = stor;
				size++;
				return;
			}
			count++;
			temp = temp.next;
		}
		if(temp.next == null) {
			temp.next = new Node(gem);
			size++;
			return;
		}
	}
	
	public int score() {
		Node temp = head;
		String CurrentColor = "";
		int multCount = 1;
		int totalScore = 0;
		int ColorCount = 0;
		
		while(temp != null) {
			if(CurrentColor.equals(temp.gem.toString())) {
				multCount++;
				ColorCount += temp.gem.getPoints();
			}
			else {
				totalScore += multCount * ColorCount;
				CurrentColor = temp.gem.toString();
				multCount = 1;
				ColorCount = 0;
				ColorCount += temp.gem.getPoints();
			}
			
			temp = temp.next;
		}
		
		totalScore += multCount * ColorCount;
		
		return totalScore;
	}
}
