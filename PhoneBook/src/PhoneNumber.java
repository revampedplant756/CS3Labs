
public class PhoneNumber {
	
	private int number;
	
	public PhoneNumber(int number) {
		this.number = number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	@Override
	public int hashCode() {

		return number;
	}
	
	
	@Override
	public boolean equals(Object e) {
		if(((PhoneNumber) e).number == this.number) {
			return true;
		}
		
		return false;
	}
	
}
