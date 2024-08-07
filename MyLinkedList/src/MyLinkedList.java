
public class MyLinkedList {
	
	private ListNode head;
	private ListNode tail;
	private int size;
	
	private class ListNode{
		int val;
		ListNode next;
		
		public ListNode(int val) { this.val= val; }
		
		public String toString() {return "" + this.val;}
	}
	
	public MyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	public MyLinkedList(int val){
		head = new ListNode(val);
		tail = head;
		size = 1;
	}
	
	public void add(int newVal) {
		if(head == null) {
			head = new ListNode(newVal);
			tail = head;
			size = 1;
			return;
		}
		
		tail.next = new ListNode(newVal);
		
		tail = tail.next;
		size++;
	}
	
	public boolean contains(int target) {
		
		ListNode temp = head;
		
		while(temp != null) {
			if(target == temp.val) {
				return true;
			}
			temp = temp.next;
		}
		
		return false;
	}
	
	public int get(int index) {
		int count = 0;
		
		ListNode temp = head;
		
		while(temp != null) {
			if(count == index) {
				return temp.val;
			}
			temp = temp.next;
			count++;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public int indexOf(int target) {
		int count = 0;
		
		ListNode temp = head;
		
		while(temp != null) {
			if(target == temp.val) {
				return count;
			}
			temp = temp.next;
			count++;
		}
		return -1;
	}
	
	public void set(int newVal, int index) {
		int count = 0;
		
		ListNode temp = head;
		
		while(temp != null) {
			if(count == index) {
				temp.val = newVal;
				return;
			}
			temp = temp.next;
			count++;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public int size() {
		return size;
	}
	
	public int sizeRecursive(ListNode current) {
		if(current.next == null) {
			return 1;
		}
		
		return 1 + sizeRecursive(current.next);
	}
	
	public boolean isEmpty() {
		if(size() == 0) {
			return true;
		}
		return false;
	}
	
	public int remove(int index) {
		int count = 0;
		int val = 0;
		if(index == count) {
			val = head.val;
			head = head.next;
			size--;
			return val;
		}
		count++;
		ListNode temp = head;
		
		while(temp.next != null) {
			if(count == index) {
				val = temp.next.val;
				temp.next = temp.next.next;
				size--;
				return val;
			}
			temp = temp.next;
			count++;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public void add(int newVal, int index) {
		int count = 0;
		ListNode add = new ListNode(newVal);
		if(index == count) {
			int temp = 0;
			temp = head.val;
			head.val = newVal;
			add.val = temp;
			add.next = head.next;
			head.next = add;
			size++;
			return;
		}
		count++;
		ListNode temp = head;
		
		while(temp != null) {
			if(count == index) {
				add.next = temp.next;
				temp.next = add;
				if(index == size()) {
					tail = temp.next;
				}
				size++;
				return;
			}
			temp = temp.next;
			count++;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public String toString() {
		String ret = "[";
		
		ListNode temp = head;
		
		while(temp != null) {
			ret += temp.val;
			if(temp.next != null) {
				ret += ", ";
			}
			temp = temp.next;
		}
		
		return ret + "]";
	}
	
}


