import java.util.EmptyStackException;

public class MyStack {
	
	Integer[] stack;
	int size;
	
	public MyStack(int initCap) {
		stack = new Integer[initCap];
		size = 0;
	}
	
	public MyStack() {
		this(2);
	}
	
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}
	
	public Integer peek() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		else {
			return stack[size-1];
		}
	}
	
	public Integer pop() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		else {
			Integer temp = 0;
			temp = stack[size-1];
			stack[size-1] = null;
			size--;
			return temp;
		}
	}
	
	public void push(Integer item) {
		if(size == stack.length) {
			doubleCapacity();
		}
		size++;
		stack[size-1] = item;
	}
	
	public String toString() {
		String temp = "[";
		for(int e = 0; e < size; e++) {
			temp += Integer.toString(stack[e]);
			if(e+1<size) {
				temp += ", ";
			}
		}
		return temp + "]";
	}
	
	private void doubleCapacity() {
		Integer[] newStack = new Integer[stack.length * 2];
		for(int e = 0; e < stack.length; e++) {
			newStack[e] = stack[e];
		}
		stack = newStack;
	}
	
}
