package inventory;

public class Product {

	private final String name;
	private final double price;
	private final int totalQuantity;
	private Integer soldQuantity;
	public final Object Lock = new Object();

	public Product(String name, double price, int quantity) {
		this.name = name; 
		this.price = price;
		this.totalQuantity = quantity;
		this.soldQuantity = 0;
	}

	public String getName() {
		return name;
	}

	public double price() {
		return price;
	}

	public int totalQuantity() {
		return totalQuantity;
	}

	public int soldQuantity() {
		return soldQuantity;
	}

	public int remaining() {
		return totalQuantity - soldQuantity;
	}

	public boolean sell(int quantity) {
		synchronized(this.Lock) {
			if(this.remaining() < quantity)
				return false;
			else 
				soldQuantity += quantity;
			return true;
		}
	}

	public void reset() {
		this.soldQuantity = 0;
	}

	@Override
	public String toString() {
		return "Product: " + name + " - " + price + "$ stock=" + totalQuantity + "\n";
	}


}
