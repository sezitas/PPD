package inventory;

public interface IFactory {

	void generateProducts(Inventory inventory);

	void generateBills(Inventory inventory, int billNumber);

}