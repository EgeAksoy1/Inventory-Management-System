package InventoryManagement;

public class PerishableProduct extends Product {

	private String maxstoragedays;
	public PerishableProduct (String name, double price, int supplierId, int minimumstocklevel,String maxstoragedays) {
		super(name,price,supplierId,minimumstocklevel);
		setMaxstoragedays(maxstoragedays);
	}
	public String getMaxstoragedays() {
		return maxstoragedays;
	}
	public void setMaxstoragedays(String maxstoragedays2) {
		this.maxstoragedays = maxstoragedays2;
	}
}
