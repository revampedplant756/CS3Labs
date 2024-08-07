import java.util.ArrayList;

public class Disk implements Comparable<Disk>{
	private int storage;
	private ArrayList<Integer> files;
	
	public Disk() {
		storage = 1000000;
		files = new ArrayList<>();
	}
	
	public int getStorage() {
		return storage;
	}
	
	public ArrayList<Integer> getFiles() {
		return files;
	}
	
	public void addFile(int file) {
		storage = storage - file;
		files.add(file);
		return;
	}
	
	public String toString() {
		return files.toString();
	}

	@Override
	public int compareTo(Disk o) {
		if(this.storage > o.getStorage()) {
			return -1;
		}
		else if(this.storage < o.getStorage()){
			return 1;
		}
		return 0;
	}
}
