
public class Employee {
	
	private String name;
	private int ID;
	
	public Employee(String name, int ID) {
		this.name = name;
		this.ID = ID;
	}
	
	@Override
	public int hashCode() {
		//change to tableSize
		return ID % 11;
	}
	
	@Override
	public boolean equals(Object e) {
		if(this.ID == ((Employee)(e)).ID && this.name.equals(((Employee)(e)).name)) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}
}
