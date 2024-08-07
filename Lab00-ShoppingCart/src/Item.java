
public class Item {
	
	public String name;
	public double price;
	public int bulkQty;
	public double bulkPrice;
	
	public Item(String name, double price) {
		this(name, price, 0, price);
	}
	
	public Item(String name, double price, int bulkQty, double bulkPrice) {
		if (price >= 0 || bulkQty >= 0 || bulkPrice >= 0) {
			this.name = name;
			this.price = price;
			this.bulkQty = bulkQty;
			this.bulkPrice = bulkPrice;
		}
		else {throw new IllegalArgumentException ("error");}
	}
	
	public double priceFor(int quantity) {
		if (quantity >= 0) {
			if (quantity >= bulkQty) {
				return bulkPrice;
			}
			else {
				return price;
			}
		}
		else {throw new IllegalArgumentException ("error");}
	}
	
	@Override public boolean equals(Object obj) {
		
		Item x = (Item) obj;
		
		if(x.name == name) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String convertedPrice = price+"";
		String printing = name + ", $" + convertedPrice;
		
		if (bulkQty > 0 && bulkPrice > 0) {
			String convertedBulkPrice = bulkPrice+"";
			printing += " (Bulk Pricing: " + Integer.toString(bulkQty) + " for " + convertedBulkPrice + ")";
		}
		
		return printing;
	}
}
