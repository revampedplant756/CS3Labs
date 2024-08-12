import java.util.ArrayList;

public class PhoneBook implements IMap{
	
	private Entry[] open;
	private int capacity;
	private int size;
	private PhoneNumber previous;
	
	private class Entry {
		
		public Entry next;
		public Person key;
		public PhoneNumber val;
		
		public Entry(Person key, PhoneNumber val) {
			this.key = key; 
			this.val = val;
			next = null;
		}
	}
	
	
	public PhoneBook(){
		open = new Entry[11];
		size = 0;
		capacity = 11;
		previous = null;
	}
	
	@Override
	public PhoneNumber put(Person person, PhoneNumber phone) {
		int code = person.hashCode() % capacity;
		
		if(open[code] == null) {
			open[code] = new Entry(person, phone);
			size++;
			if(checkLoadFactor()) {
				doubleCapacity();
			}
			return null;
		}
		else if(open[code].key.equals(person)) {
			PhoneNumber temp = open[code].val;
			open[code].val = phone;
			return temp;
		}
		else {
			open[code] = findReplace(open[code], person, phone);
			
			if(previous == null) {
				size++;
			}
			
			if(checkLoadFactor()) {
				doubleCapacity();
			}
			
			return previous;
		}
	}
	
	private void doubleCapacity() {
		ArrayList<Entry> temp = new ArrayList<>();
		
		for(Entry e : open) {
			if(e != null) {
				temp.add(e);
			}
		}
		
		capacity = capacity * 2;
		
		open = new Entry[capacity];
		
		for(Entry e : temp) {
			put(e.key, e.val);
		}
	}
	
	private boolean checkLoadFactor() {
		if(((double) size) / ((double) capacity) > .75) {
			return true;
		}
		
		return false;
	}
	
	private Entry findReplace(Entry cur, Person person, PhoneNumber phone) {
		
		if(cur == null) {
			previous = null;
			return new Entry(person, phone);
		}
		else if(cur.key.equals(person)) {
			previous = cur.val;
			cur.val = phone;
			return cur;
		}
		
		cur.next = findReplace(cur.next, person, phone);
		
		
		return cur;
	}

	@Override
	public PhoneNumber get(Person person) {
		int code = person.hashCode() % capacity;
		
		if(open[code] == null) {
			return null;
		}
		else if(open[code].key.equals(person)) {
			return open[code].val;
		}
		else {
			
			Entry cur = open[code];
			
			while(cur.next != null) {
				cur = cur.next;
				
				if(cur == null) {
					return null;
				}
				else if(cur.key.equals(person)) {
					return cur.val;
				}
			}
			
		}
		
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public PhoneNumber remove(Person person) {
		int code = person.hashCode() % capacity;
		
		if(open[code] == null) {
			return null;
		}
		else if(open[code].key.equals(person)) {
			PhoneNumber temp = open[code].val;
			open[code] = open[code].next;
			size--;
			return temp;
		}
		else {
			open[code] = removeHelper(open[code].next, open[code], person);
			
			if(previous != null) {
				size--;
			}
			
			return previous;
		}
	}
	
	private Entry removeHelper(Entry cur, Entry prev, Person person) {
		if(cur == null) {
			previous = null;
			return prev;
		}
		else if(cur.key.equals(person)) {
			previous = cur.val;
			prev.next = cur.next;
			return prev;
		}
		
		cur = removeHelper(cur.next, cur, person);

		return cur;
	}

}
