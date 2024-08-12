import java.util.ArrayList;

public class MyHashTable<K, V>{
	
	private Object[] open;
	private int capacity;
	private int size;
	private V previous;
	
	private class Entry {
		
		public Entry next;
		public K key;
		public V val;
		
		public Entry(K key, V val) {
			this.key = key; 
			this.val = val;
			next = null;
		}
	}
	
	
	public MyHashTable(){
		open = new Object[11];
		size = 0;
		capacity = 11;
		previous = null;
	}
	
	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		int code = Math.abs(key.hashCode() % capacity);
		
		if(open[code] == null) {
			open[code] = new Entry(key, value);
			size++;
			if(checkLoadFactor()) {
				doubleCapacity();
			}
			return null;
		}
		else if(((Entry) open[code]).key.equals(key)) {
			V temp = ((Entry) open[code]).val;
			((Entry) open[code]).val = value;
			return temp;
		}
		else {
			open[code] = findReplace(((Entry) open[code]), key, value);
			
			if(previous == null) {
				size++;
			}
			
			if(checkLoadFactor()) {
				doubleCapacity();
			}
			
			return previous;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void doubleCapacity() {
		ArrayList<Entry> temp = new ArrayList<>();
		
		for(Object e : open) {
			if(e != null) {
				temp.add(((Entry) e));
			}
		}
		
		capacity = capacity * 2;
		
		open = new Object[capacity];
		
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
	
	private Entry findReplace(Entry cur, K key, V value) {
		
		if(cur == null) {
			previous = null;
			return new Entry(key, value);
		}
		else if(cur.key.equals(key)) {
			previous = cur.val;
			cur.val = value;
			return cur;
		}
		
		cur.next = findReplace(cur.next, key, value);
		

		return cur;
	}

	@SuppressWarnings("unchecked")
	public V get(K key) {
		int code = Math.abs(key.hashCode() % capacity);
		
		if(open[code] == null) {
			return null;
		}
		else if(((Entry) open[code]).key.equals(key)) {
			return ((Entry) open[code]).val;
		}
		else {
			
			Entry cur = ((Entry) open[code]);
			
			while(cur.next != null) {
				cur = cur.next;
				
				if(cur == null) {
					return null;
				}
				else if(cur.key.equals(key)) {
					return cur.val;
				}
			}
			
		}
		
		return null;
	}

	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	public V remove(K key) {
		int code = Math.abs(key.hashCode() % capacity);
		
		if(open[code] == null) {
			return null;
		}
		else if(((Entry) open[code]).key.equals(key)) {
			V temp = ((Entry) open[code]).val;
			open[code] = ((Entry) open[code]).next;
			size--;
			return temp;
		}
		else {
			open[code] = removeHelper(((Entry) open[code]).next, ((Entry) open[code]), key);
			
			if(previous != null) {
				size--;
			}
			
			return previous;
		}
	}
	
	private Entry removeHelper(Entry cur, Entry prev, K key) {
		if(cur == null) {
			previous = null;
			return prev;
		}
		else if(cur.key.equals(key)) {
			previous = cur.val;
			prev.next = cur.next;
			return prev;
		}
		
		cur = removeHelper(cur.next, cur, key);

		return cur;
	}

}
