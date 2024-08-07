import java.util.ArrayList;

public class Catalog {
	
	public String name = "";
	public ArrayList<Item> list = new ArrayList<Item>();
	
	public Catalog(String name) {
		this.name = name;
	}
	
	public void add(Item item) {
		list.add(item);
	}
	
	public int size() {
		return list.size();
	}
	
	public Item get (int index) {
		return list.get(index);
	}
	
	public String getName() {
		return name;
	}
	
}
