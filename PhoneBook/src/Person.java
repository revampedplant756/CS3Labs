
public class Person {
	
	private String name;
		
	public Person(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object e) {
		if(((Person) e).name.equals(this.name)) {
			return true;
		}
		
		return false;
	}
	
}
