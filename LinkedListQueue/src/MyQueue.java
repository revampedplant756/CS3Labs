
public class MyQueue <T> implements QueueADT<T>{
	
	private MyLinkedList<T> queue;
	
	public MyQueue() {
		queue = new MyLinkedList<T>();
	}
	
	public MyQueue(T... vals) {
		this();
		for(int e = 0; e < vals.length; e++) {
			offer(vals[e]);
		}
	}
	
	
	@Override
	public void offer(T item) {
		queue.add(item);
	}

	@Override
	public T poll() {
		return queue.remove(0);
	}

	@Override
	public T peek() {
		return queue.get(0);
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public void clear() {
		while(!isEmpty()) {
			poll();
		}
	}
	
	public String toString() {
		return queue.toString();
	}

}
