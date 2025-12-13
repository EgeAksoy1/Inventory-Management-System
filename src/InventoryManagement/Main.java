package InventoryManagement;

import DataBaseCon.DBUser;

public class Main {

	public static void main(String[] args) {
		User user = new User("deneme2", "5555", "user");
		DBUser.save(user);

	}

}
