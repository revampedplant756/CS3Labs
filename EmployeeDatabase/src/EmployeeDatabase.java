import java.util.ArrayList;

public class EmployeeDatabase {
	
	private Employee[] set;
	private int size;
	
	public EmployeeDatabase(int size) {
		set = new Employee[size];
		size = 0;
	}
	
	public EmployeeDatabase() {
		this(11);
	}
	
	public boolean add(Employee e) {
		int keyValue = e.hashCode();
		
		if(contains(e)) {
			return false;
		}
		
		if(set[keyValue] == null) {
			set[keyValue] = e;
			size++;
			return true;
		}
		else {
			for(int f = keyValue+1; f != keyValue; f++) {
				if(set[f] == null) {
					set[f] = e;
					size++;
					return true;
				}
				
				if(f+1 == set.length) {
					f = -1;
				}
			}
			
			return false;
		}
		
	}
	
	public boolean contains(Employee e) {
		int keyValue = e.hashCode();

		
		if(set[keyValue] == null) {
			return false;
		}
		else if(set[keyValue].equals(e)){
			return true;
		}
		else {
			for(int f = keyValue+1; f != keyValue; f++) {
				if(set[f] != null && set[f].equals(e)) {
					return true;
				}
				
				if(set[f] == null) {
					return false;
				}
				
				if(f+1 == set.length) {
					f = -1;
				}
			}
			
			return false;
		}
	}
	
	public boolean remove(Employee e) {
		int keyValue = e.hashCode();
		
		if(!contains(e)) {
			return false;
		}
		
		if(set[keyValue].equals(e)) {
			set[keyValue] = null;
			rehash();
			size--;
			return true;
		}
		else {
			for(int f = keyValue+1; f != keyValue; f++) {
				if(set[f].equals(e)) {
					set[keyValue] = null;
					rehash();
					size--;
					return true;
				}
				
				if(f+1 == set.length) {
					f = -1;
				}
			}
			
			return false;
		}
	}
	
	public int size() {
		return size;
	}
	
	private void rehash() {
		ArrayList<Employee> temp = new ArrayList<>();
		
		for(int e = 0;e < set.length; e++) {
			if(set[e] != null) {
				temp.add(set[e]);
				set[e] = null;
			}
		}
		
		for(Employee e : temp) {
			add(e);
		}
	}
	
	@Override
	public String toString() {
		String print = "[";
		for(Employee e: set) {
			if(e != null) {
				print += (e.getName() + ": " + e.getID() + ", ");
			}
		}
		
		return print.substring(0, print.length()-2) + "]";
	}
	
}
