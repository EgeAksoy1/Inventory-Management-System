package InventoryManagement;

import java.time.LocalDate;
import java.util.Scanner;



public class InventoryManagement {
	private static Scanner sc = new Scanner(System.in);
	private static User user;
	private static User newuser;
	private static PerishableProduct product;
	private static Supplier supplier;
	public static void login() {
		int a = 0;
		try {
			System.out.println("1. Sign Up");
			System.out.println("2. Login");
			System.out.print("Type a number: ");
			a = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			login();
			return;
		}
		switch (a) {
		case 1: {
			System.out.print("Username: ");
			String username = sc.next();
			System.out.print("Password: ");
			String password = sc.next();
			user = new User(username, password, "user");
			user.save();
			System.out.println("Now you have a account try login");
			login();
			break;
			
		}
		case 2: {
			System.out.print("Username: ");
			String username = sc.next();
			System.out.print("Password: ");
			String password = sc.next();
			pageselecter(username, password);
			break;
		}
		default:
			System.out.println("\nInvalid input please try again");
			login();
		}
	}
	public static void pageselecter(String username,String password) {
		String selected = User.selectUser(username, password);
		if (selected.equals("admin")) {
			user = new User(username, password, "admin");
			adminpage();
		} else if (selected.equals("user")) {
			user = new User(username, password, "user");
			userpage();
		}
	}
	public static void adminpage() {
		
		System.out.println("======ADMİN PAGE======");
		System.out.println("1. Product Management");
		System.out.println("2. Supplier Management");
		System.out.println("3. User Management");
		System.out.println("4. Logout");
		System.out.print("Type a number: ");
		int choose = sc.nextInt();
		sc.nextLine();
		switch (choose) {
		case 1: {
			pageProductManagement();
			break;
		}
		case 2: {
			pageSupplierManagement();
			break;
		}
		case 3: {
			pageUserManagement();
			break;
		}
		case 4: {
			login();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void pageSupplierManagement() {
		System.out.println("Supplier Management Page");
		System.out.println("1. Add new Supplier");
		System.out.println("2. Delete Supplier");
		System.out.println("3. Update Supplier");
		System.out.println("4. Back");
		System.out.print("Type a number: ");
		int choose = sc.nextInt();
		sc.nextLine();
		switch (choose) {
		case 1: {
			addSupplier();
			break;
		}
		case 2: {
			deleteSupplier();
			break;
		}
		case 3: {
			updateSupplier();
			break;
		}
		case 4: {
			adminpage();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void updateSupplier() {
		for(String s:Supplier.getSupplierList()) {
			System.out.println(s);
		}
		System.out.print("Enter an ID which supplier you want update: ");
		int search = sc.nextInt();
		Supplier.getSupplierDetailsById(search);
		supplier = Supplier.getSupplierById(search);
		System.out.print("Enter the number of the detail you want to update: ");
		int choose = sc.nextInt();
		sc.nextLine();
		switch (choose) {
		case 1: {
			System.out.print("New Supplier name: ");
			String newname = sc.nextLine();
			Supplier.updateSupplier(search, newname, supplier.getAddress(), supplier.getLastorderDate());
			pagebackSupplier();
			break;
		}
		case 2: {
			System.out.print("New Supplier address: ");
			String newaddress = sc.nextLine();
			Supplier.updateSupplier(search, supplier.getSuppliername(), newaddress, supplier.getLastorderDate());
			pagebackSupplier();
			break;
		}
		case 3: {
			LocalDate date = null;

			while (date == null) { 
			    System.out.print("New Last Order Date (yyyy-MM-dd): ");
			    String input = sc.nextLine();
			    
			    try {
			        date = LocalDate.parse(input);
			    } catch (Exception e) {
			        System.out.println("Invalid input please try again");
			    }
			}
			Supplier.updateSupplier(search, supplier.getSuppliername(), supplier.getAddress(), date);
			pagebackSupplier();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void pagebackSupplier() {
		System.out.println("1. Back      2.Quit");
		System.out.print("Type a number (1-2): ");
		int choose2 = sc.nextInt();
		sc.nextLine();
		if (choose2 == 1) {
			updateSupplier();
		}else if(choose2 == 2) {
			adminpage();
		}else {
			System.out.println("Invalid input");
			pagebackSupplier();
		}
	}
	public static void deleteSupplier() {
		for(String s:Supplier.getSupplierList()) {
			System.out.println(s);
		}
		System.out.print("Enter an ID which supplier you want delete: ");
		int searchID = sc.nextInt();
		Supplier deleteSupplier = Supplier.getSupplierById(searchID);
		deleteSupplier.delete(searchID);
		System.out.println("New Supplier List");
		for(String s:Supplier.getSupplierList()) {
			System.out.println(s);
		}
		System.out.println("1. Quit     2. Back");
		System.out.print("Type a number (1-2): ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			pageselecter(user.getName(), user.getPassword());
			break;
		}
		case 2: {
			pageSupplierManagement();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void addSupplier() {
		System.out.print("Supplier name: ");
		String suppliername = sc.nextLine();
		System.out.print("Supplier address: ");
		String address = sc.nextLine();
		sc.nextLine();
		LocalDate date = null;

		while (date == null) { 
		    System.out.print("Enter Last Order Date (yyyy-MM-dd): ");
		    String input = sc.nextLine();
		    
		    try {
		        date = LocalDate.parse(input);
		    } catch (Exception e) {
		        System.out.println("Invalid input please try again");
		    }
		}
		supplier = new Supplier(suppliername, address, date);
		supplier.save();
		System.out.println("1. Quit     2. Back");
		System.out.print("Type a number (1-2): ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			pageselecter(user.getName(), user.getPassword());
			break;
		}
		case 2: {
			pageSupplierManagement();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void pageUserManagement() {
		System.out.println("User Management Page");
		System.out.println("1. Add new User");
		System.out.println("2. Delete User");
		System.out.println("3. Update User");
		System.out.println("4. Search User");
		System.out.println("5. Back");
		System.out.print("Type a number: ");
		int choose = sc.nextInt();
		sc.nextLine();
		switch (choose) {
		case 1: {
			adminsaveUser();
			break;
		}
		case 2: {
			admindeleteUser();
			break;
		}
		case 3: {
			adminupdateUser();
			break;
		}
		case 4: {
			adminsearchUser();
			break;
		}
		case 5: {
			adminpage();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void adminsearchUser() {
		for (String s : User.getUserList()) {
			System.out.println(s);
		}
		System.out.print("Enter the ID of the user to search:");
		int userid = sc.nextInt();
		User.getUserDetailsById(userid);
		System.out.println("1. Back    2. Quit");
		System.out.print("Type a number: ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			adminsearchUser();
			break;
		}
		case 2: {
			adminpage();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void adminupdateUser() {
		for (String s : User.getUserList()) {
			System.out.println(s);
		}
		System.out.print("Enter the ID of the user to update: ");
		int userid = sc.nextInt();
		User.getUserDetailsById(userid);
		newuser = User.getUserById(userid);
		System.out.print("Enter the number of the detail you want to update: ");
		int choose = sc.nextInt();
		sc.nextLine();
		switch (choose) {
		case 1: {
			System.out.print("New username: ");
			String newusername = sc.nextLine();
			User.adminUpdate(userid, newusername, newuser.getPassword(), newuser.getRole());
			System.out.println("New Details of User");
			User.getUserDetailsById(userid);
			pagebackUser();
			break;
		}
		case 2: {
			System.out.print("New password: ");
			String newpassword = sc.nextLine();
			User.adminUpdate(userid, newuser.getName(), newpassword, newuser.getRole());
			System.out.println("New Details of User");
			User.getUserDetailsById(userid);
			pagebackUser();
			break;
		}
		case 3: {
			System.out.print("New role: ");
			String newrole = sc.nextLine();
			User.adminUpdate(userid, newuser.getName(), newuser.getPassword(), newrole);
			System.out.println("New Details of User");
			User.getUserDetailsById(userid);
			pagebackUser();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void pagebackUser() {
		System.out.println("1. Back      2.Quit");
		System.out.print("Type a number (1-2): ");
		int choose2 = sc.nextInt();
		sc.nextLine();
		if (choose2 == 1) {
			adminupdateUser();
		}else if(choose2 == 2) {
			adminpage();
		}else {
			System.out.println("Invalid input");
			pagebackUser();
		}
	}
	public static void admindeleteUser() {
		for (String s : User.getUserList()) {
			System.out.println(s);
		}
		System.out.print("Enter the ID of the user to delete:");
		int userid = sc.nextInt();
		User.adminDelete(userid);
		System.out.println("1. Back     2. Quit");
		System.out.print("Type a number (1-2): ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			pageselecter(user.getName(), user.getPassword());
			break;
		}
		case 2: {
			pageUserManagement();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void adminsaveUser() {
		System.out.print("Username: ");
		String username = sc.next();
		System.out.print("Password: ");
		String password = sc.next();
		System.out.print("Role: ");
		String role = sc.nextLine();
		newuser = new User(username, password, role);
		newuser.save();
		System.out.print("Type a number (1-2): ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			pageselecter(user.getName(), user.getPassword());
			break;
		}
		case 2: {
			pageUserManagement();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
		
	}
	public static void pageProductManagement() {
		System.out.println("Product Management Page");
		System.out.println("1. Add new Product");
		System.out.println("2. Reduce Product");
		System.out.println("3. Update Product");
		System.out.println("4. Delete Product");
		System.out.println("5. Back");
		System.out.print("Type a number: ");
		int choose = sc.nextInt();
		sc.nextLine();
		switch (choose) {
		case 1: {
			addProduct();
			break;
		}
		case 2: {
			reduceProduct();
			break;
		}
		case 3: {
			updateProduct();
			break;
		}
		case 4: {
			deleteProduct();
			break;
		}
		case 5: {
			pageselecter(user.getName(), user.getPassword());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
		
	}
	public static void deleteProduct() {
		for (String s : PerishableProduct.searchProductsList()) {
			System.out.println(s);
		}
		System.out.print("Enter an ID which product you want delete: ");
		int searchID = sc.nextInt();
		PerishableProduct deletePerishableProduct = PerishableProduct.getPerishableProductById(searchID);
		deletePerishableProduct.delete(searchID);
		System.out.println("Final Product List");
		for (String s : PerishableProduct.searchProductsList()) {
			System.out.println(s);
		}
		System.out.print("1. Back     2. Quit");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			deleteProduct();
			break;
		}
		case 2: {
			adminpage();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void pageBack() {
		System.out.println("1. Back     2. Quit");
		System.out.print("Type a number (1-2): ");
		int choose2 = sc.nextInt();
		if (choose2 == 1) {
			updateProduct();
		}else if(choose2 == 2) {
			adminpage();
		}else {
			System.out.println("Invalid input");
			pageBack();
		}
	}
	public static void updateProduct() {
		for (String s : PerishableProduct.searchProductsList()) {
			System.out.println(s);
		}
		System.out.println("Enter an ID which product you want update: ");
		int searchID = sc.nextInt();
		PerishableProduct.selectProductsID(searchID);
	    product = PerishableProduct.getPerishableProductById(searchID);
		System.out.print("Enter the number of the detail you want to update: ");
		int choose = sc.nextInt();
		sc.nextLine();
		switch (choose) {
		case 1: {
			System.out.print("Enter new name: ");
			String newname = sc.nextLine();
			PerishableProduct.updateProduct(searchID, newname, product.getPrice(), product.getSupplierId(), product.getStock(), product.getMinimumstocklevel(), product.getMaxstoragedays());
			System.out.println("New Details of Updated Product");
			PerishableProduct.selectProductsID(searchID);
			pageBack();
			break;
		}
		case 2: {
			System.out.print("Enter new price: ");
			Double newprice = sc.nextDouble();
			PerishableProduct.updateProduct(searchID, product.getName(), newprice, product.getSupplierId(), product.getStock(), product.getMinimumstocklevel(), product.getMaxstoragedays());
			System.out.println("New Details of Updated Product");
			PerishableProduct.selectProductsID(searchID);
			pageBack();
			break;
		}
		case 3: {
			System.out.print("Enter new supplier ID: ");
			int newsupplierid = sc.nextInt();
			PerishableProduct.updateProduct(searchID, product.getName(), product.getPrice(), newsupplierid, product.getStock(), product.getMinimumstocklevel(), product.getMaxstoragedays());
			System.out.println("New Details of Updated Product");
			PerishableProduct.selectProductsID(searchID);
			pageBack();
			break;
		}
		case 4: {
			System.out.print("Enter new stock level: ");
			int newstocklevel = sc.nextInt();
			PerishableProduct.updateProduct(searchID, product.getName(), product.getPrice(), product.getSupplierId(), newstocklevel, product.getMinimumstocklevel(), product.getMaxstoragedays());
			System.out.println("New Details of Updated Product");
			PerishableProduct.selectProductsID(searchID);
			pageBack();
			break;
		}
		case 5: {
			System.out.print("Enter new minimum stock level: ");
			int newminimumstocklevel = sc.nextInt();
			PerishableProduct.updateProduct(searchID, product.getName(), product.getPrice(), product.getSupplierId(), product.getStock(), newminimumstocklevel, product.getMaxstoragedays());
			System.out.println("New Details of Updated Product");
			PerishableProduct.selectProductsID(searchID);
			pageBack();
			break;
		}
		case 6: {
			System.out.print("Enter new max storage days: ");
			String newmaxstoragedays = sc.nextLine();
			PerishableProduct.updateProduct(searchID, product.getName(), product.getPrice(), product.getSupplierId(), product.getStock(), product.getMinimumstocklevel(), newmaxstoragedays);
			System.out.println("New Details of Updated Product");
			PerishableProduct.selectProductsID(searchID);
			pageBack();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void userpage() {
		
		System.out.println("======USER PAGE======");
		System.out.println("1. Add new product");
		System.out.println("2. Reduce Product");
		System.out.println("3. Search Product");
		System.out.println("4. Account Management");
		System.out.println("5. Logout");
		System.out.print("Type a number: ");
		int choose = sc.nextInt();
		sc.nextLine();
		switch (choose) {
		case 1: {
			addProduct();
			break;
		}
		case 2: {
			reduceProduct();
			break;
		}
		case 3: {
			searchProduct();
			break;
		}
		case 4: {
			accountmanagement();
			break;
		}
		case 5: {
			login();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
		
	}
	public static void addProduct() {
		
		System.out.print("Product's name: ");
		String name = sc.nextLine();
		System.out.print("Product's price: ");
		Double price = sc.nextDouble();
		System.out.print("Stock level: ");
		int stocklevel = sc.nextInt();
		System.out.println("\n Supplier List");
		for(String s:Supplier.getSupplierList()) {
			System.out.println(s);
		}
		System.out.print("Supplier ID: ");
		int supplierid = sc.nextInt();
		System.out.print("Minimum Stock Level: ");
		int minimumstocklevel = sc.nextInt();
		System.out.print("Max Storage Days (It can be space): ");
		String maxstoragedays = sc.next();
		PerishableProduct added = new PerishableProduct(name, price, stocklevel, supplierid, minimumstocklevel, maxstoragedays);
		added.save();
		System.out.println("1. Back     2. Add another product");
		System.out.print("Type a number (1-2): ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			pageselecter(user.getName(), user.getPassword());
			break;
		}
		case 2: {
			addProduct();;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void reduceProduct() {
		
		for (String s : PerishableProduct.getProductsList()) {
			System.out.println(s);
		}
		
		System.out.print("Enter an ID which product you want reduce: ");
		int id = sc.nextInt();
		System.out.print("Enter an amount that you want reduce: ");
		int amount = sc.nextInt();
		PerishableProduct.reduceProduct(id,amount);
		PerishableProduct.reorderProduct(id);
		System.out.println("New Product List");
		for (String s : PerishableProduct.getProductsList()) {
			System.out.println(s);
		}

		System.out.println("1. Back     2. Reduce another product");
		System.out.print("Type a number (1-2): ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			pageselecter(user.getName(), user.getPassword());
			break;
		}
		case 2: {
			reduceProduct();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void searchProduct() {
		for (String s : PerishableProduct.searchProductsList()) {
			System.out.println(s);
		}
		System.out.print("Enter an ID which product you want search: ");
		int searchID = sc.nextInt();
		PerishableProduct.selectProductsID(searchID);
		System.out.println("1. Back     2. Search another product");
		System.out.print("Type a number (1-2): ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			pageselecter(user.getName(), user.getPassword());
			break;
		}
		case 2: {
			searchProduct();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	public static void accountmanagement() {
		System.out.println("Account Management Page");
		System.out.println("Username: "+user.getName()+" Password: "+user.getPassword());
		System.out.println("1. Change the password");
		System.out.println("2. Delete the account");
		System.out.println("3. Back");
		System.out.print("Enter your choose: ");
		int choose =sc.nextInt();
		sc.nextLine();
		switch (choose) {
		case 1: {
			System.out.print("Enter your new password: ");
			String newpass = sc.nextLine();
			user.update(user.getName(), newpass);
			user = new User(user.getName(),newpass,user.getRole());
			System.out.println("Username: "+user.getName()+" Password: "+user.getPassword());
			accountmanagement();
			break;
		}
		case 2: {
			System.out.println("Your account has been deleted");
			user.delete();
			login();
			break;
		}
		case 3: {
			pageselecter(user.getName(), user.getPassword());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}
	

}
