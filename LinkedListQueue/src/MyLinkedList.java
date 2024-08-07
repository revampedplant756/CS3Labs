
public class MyLinkedList <T>{
	
	private ListNode head;
	private ListNode tail;
	private int size;
	
	private class ListNode{
		T val;
		ListNode next;
		
		public ListNode(T val) { this.val= val; }
		
		public String toString() {return "" + this.val;}
	}
	
	public MyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	public MyLinkedList(T val){
		head = new ListNode(val);
		tail = head;
		size = 1;
	}
	
	public MyLinkedList(T... vals) {
		this();
		for(int e = 0; e < vals.length; e++) {
			add(vals[e]);
		}
	}
	
	public void add(T newVal) {
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
	
	public boolean contains(T target) {
		
		ListNode temp = head;
		
		while(temp != null) {
			if(target.equals(temp.val)) {
				return true;
			}
			temp = temp.next;
		}
		
		return false;
	}
	
	public T get(int index) {
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
	
	public int indexOf(T target) {
		 int count = 0;
		
		ListNode temp = head;
		
		while(temp != null) {
			if(target.equals(temp.val)) {
				return count;
			}
			temp = temp.next;
			count++;
		}
		return -1;
	}
	
	public void set(T newVal, int index) {
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
	
	public T remove(int index) {
		int count = 0;
		T val;
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
				if(index == size()) {
					tail = temp.next;
				}
				return val;
			}
			temp = temp.next;
			count++;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public void add(T newVal, int index) {
		int count = 0;
		ListNode add = new ListNode(newVal);
		if(index == count) {
			T temp;
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


