import java.util.ArrayList;

public class ShoppingCart {
	
	ArrayList<ItemOrder> cart;
	
	public ShoppingCart() {
		cart = new ArrayList<ItemOrder>();
	}
	
	public void add(ItemOrder newOrder) {
		for(int e = 0; e < cart.size(); e++) {
			if(cart.get(e).equals(newOrder)) {
				cart.remove(e);
				cart.add(e, newOrder);
				return;
			}
		}
		cart.add(newOrder);
	}
	
	public double getTotal() {
		
		double total = 0.0;
		
		for(int e = 0; e < cart.size(); e++) {
			
			//System.out.println(cart.get(e).getPrice());
			total += (cart.get(e).getPrice()) * cart.get(e).qty;
		}
		
		return total;
	}
	
	
}
