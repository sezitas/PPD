package main;

import inventory.Bill;
import inventory.Inventory;
import inventory.Product;

public class Worker implements Runnable{

	Inventory inventory;
	int start;
	int end;

	Worker (Inventory inventory, int start, int end) {
		this.inventory = inventory;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		//		System.out.println("start= " + start + " end= " + end);

		for (int i = start; i <= end; i++) { 
			double cash = 0;
			Bill bill = inventory.getBill(i);
			synchronized(inventory.Lock) {
				for(Product prod: bill.getProductsMap().keySet()) {
					int quantity = bill.getProductsMap().get(prod);
					cash += prod.price() * quantity;
					prod.sell(quantity);
				}
			inventory.addCash(cash);
			inventory.closeBill(bill);
			}
		}
	}

}
