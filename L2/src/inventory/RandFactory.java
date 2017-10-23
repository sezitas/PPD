package inventory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandFactory implements IFactory {

	/* (non-Javadoc)
	 * @see product.FactoryInterface#generateProducts(product.Inventory)
	 */
	@Override
	public void generateProducts(Inventory inventory) {
		Random rn = new Random();
		for(Inventory.names name: Inventory.names.values()) {
			int quantity = rn.nextInt(3000);

			double decimal = Math.random();
//			decimal = Math.round(decimal * 100);
//			decimal /= 100;
			double price = rn.nextInt(40) + decimal;

			inventory.addProduct(new Product(name.toString(), price, quantity));
		}
	}

	/* (non-Javadoc)
	 * @see product.FactoryInterface#generateBills(product.Inventory, int)
	 */
	@Override
	public void generateBills(Inventory inventory, int billNumber) {
		Map<Product,Integer> availableProducts = new HashMap<Product, Integer>();
		
		for(Product prod: inventory.getProductList().values()) {
			availableProducts.put(prod, prod.totalQuantity());
		}
		
		for (int i=0; i<billNumber; i++) {
			Bill bill = new Bill();
			for (int j=0; j<=6; j++) {
				Product prod;
				int quantity;
				while(true) {
					prod = getRandomProduct(availableProducts.keySet());
//					System.out.println("avail: " + availableProducts.get(prod));
					if(availableProducts.get(prod) <= 0) 
						break;
					quantity = getRandomQuantity(10);
					if(quantity > availableProducts.get(prod) || quantity == 0) 
						break;
//					System.out.println("quantity = " + quantity);
					if(bill.addProduct(prod, quantity)) {
						availableProducts.replace(prod, availableProducts.get(prod) - quantity);
						break;
					}
				}
			}
			inventory.addBill(bill);
		}
	}

	private Product getRandomProduct(Collection<Product> from) {
		Random rnd = new Random();
		int i = rnd.nextInt(from.size());
		return (Product) from.toArray()[i];
	}

	private int getRandomQuantity(int max) {
		Random rn = new Random();
		return rn.nextInt(max);
	}

}
