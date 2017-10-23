package inventory;

import java.util.HashMap;
import java.util.Map;

public class Bill {

	private Map<Product, Integer> productQuantityMap;
	private double totalPrice;
	
	Bill() {
		productQuantityMap = new HashMap<Product, Integer>();
		totalPrice = 0;
	}
	
	public boolean addProduct(Product prod, int quantity) {
		if(productQuantityMap.containsKey(prod)) 
			return false;
		productQuantityMap.put(prod, quantity);
		double prodPrice = prod.price();
		prodPrice *= quantity;
//		prodPrice = Math.round(prodPrice * 100);
//		prodPrice /= 100;
		totalPrice += prodPrice;
		return true;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public int getQuantity(Product prod) {
		return productQuantityMap.get(prod).intValue();
	}

	public Map<Product, Integer> getProductsMap() {
		return productQuantityMap;
	}

	@Override
	public String toString() {
		String out = "Bill price " + totalPrice + "$\n";
		for(Product prod: productQuantityMap.keySet()) {
			out += "\t" + prod.toString() + " \t\tX " + productQuantityMap.get(prod) + "\n";
		}
		return out;
	}
	
}
