import java.util.EmptyStackException;

public class MyStack implements StackADT{
	
	Square[] stack;
	int size;
	
	public MyStack(int initCap) {
		stack = new Square[initCap];
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
	
	public Square peek() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		else {
			return stack[size-1];
		}
	}
	
	public Square pop() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		else {
			Square temp;
			temp = stack[size-1];
			stack[size-1] = null;
			size--;
			return temp;
		}
	}
	
	public void push(Square item) {
		if(size == stack.length) {
			doubleCapacity();
		}
		size++;
		stack[size-1] = item;
	}
	
	public String toString() {
		String temp = "[";
		for(int e = 0; e < size; e++) {
			temp += stack[e].toString();
			if(e+1<size) {
				temp += ", ";
			}
		}
		return temp + "]";
	}
	
	private void doubleCapacity() {
		Square[] newStack = new Square[stack.length * 2];
		for(int e = 0; e < stack.length; e++) {
			newStack[e] = stack[e];
		}
		stack = newStack;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		for(int e = 0; e < size; e++) {
			stack[e] = null;
		}
		size = 0;
	}
	
}
