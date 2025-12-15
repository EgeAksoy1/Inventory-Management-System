package InventoryManagement;

public class Product {

	private String name;
	private double price;
	private int supplierId;
	private int minimumstocklevel;
	
	public Product(String name, double price, int supplierId, int minimumstocklevel) {
		setName(name);
		setPrice(price);
		setSupplierId(supplierId);
		setMinimumstocklevel(minimumstocklevel);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getMinimumstocklevel() {
		return minimumstocklevel;
	}

	public void setMinimumstocklevel(int minimumstocklevel) {
		this.minimumstocklevel = minimumstocklevel;
	}
}
