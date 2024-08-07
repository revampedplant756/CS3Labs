
public class MinHeapGeneric<T extends Comparable<T>>
{
	private T[] heap;
	private int size;
	public static final int DEFAULT_CAPACITY = 8;
	
	public MinHeapGeneric(T[] heap) {
		this.heap = heap;
		size = heap.length-1;
		buildHeap();
	}
	
	@SuppressWarnings("unchecked")
	public MinHeapGeneric() {
		heap = (T[]) (new Comparable[DEFAULT_CAPACITY]);
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public T peekMinimum() {
		return heap[1];
	}
	
	public int getLeftChildIndex(int index) {
		if(index > size) {
			return -1;
		}
		else {
			if(2*index > size) {
				return -1;
			}
			else {
				if(heap[2*index] == null) {
					return -1;
				}
				return 2*index;
			}
		}
	}
	
	public int getRightChildIndex(int index) {
		if(index > size) {
			return -1;
		}
		else {
			if((2*index+1) > size) {
				return -1;
			}
			else {
				if(heap[2*index+1] == null) {
					return -1;
				}
				return 2*index+1;
			}
		}
	}
	
	public int getParentIndex(int index) {
		if(index >= heap.length) {
			return -1;
		}
		else {
			if(index/2 == 0) {
				return -1;
			}
			else {
				if(heap[index/2] == null) {
					return -1;
				}
				return index/2;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void doubleCapacity() {
		T[] newArray = (T[]) (new Comparable[heap.length*2]);
		for(int e = 1; e < heap.length; e++) {
			newArray[e] = heap[e];
		}
		heap = newArray;
	}
	
	public void insert(T value) {
		size +=1;
		if(size >= heap.length) {
			doubleCapacity();
		}
		heap[size] = value;
		bubbleUp(size);
		
	}
	
	private void bubbleUp(int index) {
		if(index == 1) {
			return;
		}
		if(getParentIndex(index) == -1) {
			return;
		}
		if(heap[getParentIndex(index)].compareTo(heap[index]) > 0) {
			T temp = heap[index];
			heap[index] = heap[getParentIndex(index)];
			heap[getParentIndex(index)] = temp;
			bubbleUp(getParentIndex(index));
		}
		return;
	}
	
	public T popMinimum() {
		T ret = heap[1];
		heap[1] = heap[size];
		heap[size] = null;
		size--;
		siftDown(1);
		return ret;
	}
	
	private void siftDown(int index) {
		if(getLeftChildIndex(index) == -1) {
			return;
		}
		if((heap[getLeftChildIndex(index)]).compareTo(heap[index]) < 0) {
			T temp = heap[index];
			heap[index] = heap[getLeftChildIndex(index)];
			heap[getLeftChildIndex(index)] = temp;
			siftDown(getLeftChildIndex(index));
		}
		if(getRightChildIndex(index) == -1) {
			return;
		}
		if(heap[getRightChildIndex(index)].compareTo(heap[index]) < 0) {
			T temp = heap[index];
			heap[index] = heap[getRightChildIndex(index)];
			heap[getRightChildIndex(index)] = temp;
			siftDown(getRightChildIndex(index));
		}
		return;
	}
	
	public void buildHeap() {
		if(size == 1 || size == 0) {
			return;
		}
		if(getParentIndex(size) == 1) {
			siftDown(1);
			return;
		}
		buildHeapHelper(getParentIndex(getParentIndex(size)));
		return;
	}
	
	private void buildHeapHelper(int index) {
		if(getParentIndex(index) == -1) {
			return;
		}
		
		if(getRightChildIndex(index) != -1) {
			siftDown(getRightChildIndex(index));
		}
		if(getLeftChildIndex(index) != -1) {
			siftDown(getLeftChildIndex(index));
		}
		buildHeapHelper(getParentIndex(index));
	}
	
	@Override
	public String toString()
	{
		String output = "";

		for (int i = 1; i <= getSize(); i++) 
			output += heap[i] + ", ";

		return output.substring(0, output.lastIndexOf(",")); //lazily truncate last comma
	}

	/** method borrowed with minor modifications from the internet somewhere, for printing */
	public void display() {
		int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
		String dots = "...............................";
		System.out.println(dots + dots);      
		while (j <= this.getSize())
		{
			if (column == 0)                 
				for (int k = 0; k < nBlanks; k++)
					System.out.print(' ');

			System.out.print((heap[j] == null)? "" : heap[j]);
			
			if (++column == itemsPerRow) {
				nBlanks /= 2;                 
				itemsPerRow *= 2;             
				column = 0;                   
				System.out.println();         
			}
			else                             
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' ');
			
			j++;
		}
		System.out.println("\n" + dots + dots); 
	}
}
