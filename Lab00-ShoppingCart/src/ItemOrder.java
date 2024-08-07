
public class ItemOrder {
	
	public Item item;
	public int qty;
	
	public ItemOrder(Item item, int qty) {
		this.item = item;
		this.qty = qty;
	}
	
	public double getPrice() {
		return item.priceFor(qty);
	}
	
	public Item getItem() {
		return item;
	}
	
	@Override public boolean equals(Object obj) {
		ItemOrder x = (ItemOrder) obj;
		
		if(x.getItem().equals(item)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Qty: " + Integer.toString(qty)+ " of " + item.toString();
	}
}
