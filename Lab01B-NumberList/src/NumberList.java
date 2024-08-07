
public class NumberList {
	
	private Integer[] list;
	private int size;
	
	public NumberList() {
		list = new Integer[2];
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String temp = "[";
		for(int e = 0; e < size; e++) {
			temp += Integer.toString(list[e]);
			if(e+1<size) {
				temp += ", ";
			}
		}
		return temp + "]";
	}
	
	private void doubleCapacity() {
		Integer[] newList = new Integer[list.length * 2];
		for(int e = 0; e < list.length; e++) {
			newList[e] = list[e];
		}
		list = newList;
	}
	
	public void add(int index, Integer val) {
		size++;
		if(index > size-1 || index < 0) {
			size--;
			throw new IndexOutOfBoundsException();
		}
		if(size > list.length) {
			doubleCapacity();
		}
		int storage = 0;
		int temp = 0;
		boolean storageInUse = false;
		for(int e = 0; e < size; e++) {
			if(e == index) {
				if(list[e] == null) {
					
					list[e] = val;
				}
				else {
					storage = list[e];
					list[e] = val;
					storageInUse = true;
				}
			}
			if(storageInUse) {
				temp = storage;
				if(list[e+1] == null) {
					list[e+1] = temp;
					storageInUse = false;
				}
				else {
					storage = list[e+1];
					list[e+1] = temp;
				}
			}
			
		}

	}
	
	public boolean add(Integer val) {
		add(size, val);
		return true;
	}
	
	public Integer get(int index) {
		if(index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}
	
	public Integer set(int index, Integer val) {
		if(index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int temp = list[index];
		list[index] = val;
		return temp;
	}
	
	public Integer remove(int index) {
		if(index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int returnInt = 0;
		
		for(int e = index; e < size; e++) {
			if(e == index) {
				returnInt = list[e];
				list[e] = null;
			}
			if(e+1 != size) {
				list[e] = list[e+1];
				list[e+1] = null;
			}
		}
		size--;
		return returnInt;
	}

}
