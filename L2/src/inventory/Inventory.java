package inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

	public enum names { 
		BREAD,
		BEEF,
		CHEESE,
		BEER,
		WINE,
		CIGRETTES,
		PROD1,
		PROD2,
		PROD3,
		PROD4,
		PROD5,
		PROD6,
		PROD7,
		PROD8,
		PROD9,
		PROD10,
		PROD11,
		PROD12,
		PROD13,
		PROD14,
		PROD15,
		PROD16,
		PROD17,
		PROD18,
		PROD19
	}

	private Map<String, Product> totalProducts;
	private List<Bill> activeBills;
	private List<Bill> record;
	private Double cash;
	public final Object Lock = new Object();
	public final Object RecordLock = new Object();

	public Inventory() {
		totalProducts 	= new HashMap<String, Product>();
		activeBills		= new ArrayList<Bill>();
		record 			= new ArrayList<Bill>();
		cash 			= new Double(0);
	}

	public Map<String, Product> getProductList() {
		return totalProducts;
	}

	public void addProduct(Product prod) {
		totalProducts.put(prod.getName(), prod);
	}

	public Product getProduct(String name ) {
		return totalProducts.get(name);
	}

	public void addCash(double cash) {
		synchronized(this.Lock) {
			this.cash += cash;
		}
	}

	public double cash() {
		return cash;
	}

	public Bill getBill(int i) {
		return activeBills.get(i);
	}

	public void addBill(Bill bill) {
		activeBills.add(bill);
	}

	public void closeBill(Bill bill) {
		synchronized(this.RecordLock) {
			record.add(bill);
		}
	}

	public void runCheck() {
		synchronized(this.Lock) {
			double recordTotal = 0;
			double tempCash;
			synchronized(this.RecordLock) {
				for(Bill recordBill: record) {
					recordTotal += recordBill.getTotalPrice();
				}
			}
			tempCash = Math.round(cash* 100);
			tempCash /= 100;
			recordTotal = Math.round(recordTotal * 100);
			recordTotal /= 100;
			String out = "Check: ";
			if (recordTotal == tempCash) 
				out += "PASSED";
			else
				out += "FAILED";
			System.out.println(out);
			//			System.out.println("Actual Cash: " + tempCash + "\nRecord Cash: " + recordTotal);
		}

	}

	public void reset() {
		this.cash = 0d;
		synchronized(this.RecordLock) {
			record.clear();
		}
		for(Product prod: totalProducts.values()) {
			prod.reset();
		}
	}

	@Override
	public String toString() {
		String out = "Inventory:\n";
		for(Product prod: totalProducts.values()) {
			out += prod.toString();
		}

		out += "\n Active Bills " + this.activeBills.size() + " :\n";
		for(Bill bill: activeBills) {
			out += bill.toString();
		}
		out += "\n cash=" + cash + "\n ]";
		return out;
	}

}
