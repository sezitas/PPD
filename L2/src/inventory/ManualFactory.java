package inventory;

public class ManualFactory implements IFactory {

	@Override
	public void generateProducts(Inventory inventory) {
		inventory.addProduct(new Product(Inventory.names.BREAD.toString(), 39.12, 184));
		inventory.addProduct(new Product(Inventory.names.BEEF.toString(), 52.27, 1676));
		inventory.addProduct(new Product(Inventory.names.CHEESE.toString(), 67.45, 1893));
		inventory.addProduct(new Product(Inventory.names.BEER.toString(), 9.68, 1786));
		inventory.addProduct(new Product(Inventory.names.WINE.toString(), 37.14, 695));
		inventory.addProduct(new Product(Inventory.names.CIGRETTES.toString(), 65.16, 101));
		inventory.addProduct(new Product(Inventory.names.PROD1.toString(), 39.90, 1950));
		inventory.addProduct(new Product(Inventory.names.PROD2.toString(), 94.01, 1176));
		inventory.addProduct(new Product(Inventory.names.PROD3.toString(), 65.32, 1317));
		inventory.addProduct(new Product(Inventory.names.PROD4.toString(), 66.59, 1548));
		inventory.addProduct(new Product(Inventory.names.PROD5.toString(), 48.10, 92));
		inventory.addProduct(new Product(Inventory.names.PROD6.toString(), 57.47, 1657));
		inventory.addProduct(new Product(Inventory.names.PROD7.toString(), 26.89, 935));
		inventory.addProduct(new Product(Inventory.names.PROD8.toString(), 60.45, 1168));
		inventory.addProduct(new Product(Inventory.names.PROD9.toString(), 58.60, 1220));
		inventory.addProduct(new Product(Inventory.names.PROD10.toString(), 93.78, 982));
		inventory.addProduct(new Product(Inventory.names.PROD11.toString(), 49.43, 1874));
		inventory.addProduct(new Product(Inventory.names.PROD12.toString(), 9.88, 1777));
		inventory.addProduct(new Product(Inventory.names.PROD13.toString(), 2.99, 1781));
		inventory.addProduct(new Product(Inventory.names.PROD14.toString(), 5.88, 1783));
		inventory.addProduct(new Product(Inventory.names.PROD15.toString(), 49.47, 742));
		inventory.addProduct(new Product(Inventory.names.PROD16.toString(), 98.68, 74));
		inventory.addProduct(new Product(Inventory.names.PROD17.toString(), 43.21, 1768));
		inventory.addProduct(new Product(Inventory.names.PROD18.toString(), 38.56, 1994));
		inventory.addProduct(new Product(Inventory.names.PROD19.toString(), 78.63, 153));
	}

	@Override
	public void generateBills(Inventory inventory, int billNumber) {
		int prodcount = 0;
		Product prod;
		Bill bill = null;
		for(int i=0; i<billNumber; i++) {
			bill = new Bill();
			for(int k=0; k<4; k++) {
				if(prodcount == 24)
					prodcount = 0;
				prod = (Product) inventory.getProductList().values().toArray()[prodcount++];
				
				bill.addProduct(prod, (i % (k+1) + 1) * 3);
			}
			inventory.addBill(bill);
		}
	}

}
