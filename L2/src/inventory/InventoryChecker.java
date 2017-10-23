package inventory;

import java.util.Random;

public class InventoryChecker implements Runnable {
	
	Inventory inventory;
	
	public InventoryChecker(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public void run() {
		while(true) {
			inventory.runCheck();
			try {
				Thread.sleep(new Random().nextInt(10) + 10);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
