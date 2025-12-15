package InventoryManagement;

import DataBaseCon.DBProduct;
import DataBaseCon.DBUser;

public class Main {

	public static void main(String[] args) {
		PerishableProduct p = new PerishableProduct("Product C", 19.90, 1, 10 ,null);
		DBProduct.save(p);

	}

}
