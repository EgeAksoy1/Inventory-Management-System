package InventoryManagement;

import java.util.Scanner;

import DataBaseCon.DBProduct;
import DataBaseCon.DBUser;

public class InventoryManagement {
	private static Scanner sc = new Scanner(System.in);
	private static User user;
	public static void login() {
		
		System.out.println("1. Sign Up");
		System.out.println("2. Login");
		System.out.print("Type a number: ");
		int a = sc.nextInt();
		switch (a) {
		case 1: {
			System.out.print("Username: ");
			String username = sc.next();
			System.out.print("Password: ");
			String password = sc.next();
			user = new User(username, password, "user");
			DBUser.save(user);
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
			throw new IllegalArgumentException("Unexpected value: " + a);
		}
	}
	public static void pageselecter(String username,String password) {
		String selected = DBUser.selectUser(username, password);
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
		switch (choose) {
		case 1: {
			pageProductManagement();
			break;
		}
		case 2: {
			
			break;
		}
		case 3: {
	
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
	public static void pageProductManagement() {
		System.out.println("Product Management Page");
		System.out.println("1. Add new Product");
		System.out.println("2. Reduce Product");
		System.out.println("3. Update Product");
		System.out.println("4. Delete Product");
		System.out.println("5. Back");
		System.out.print("Type a number: ");
		int choose = sc.nextInt();
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
	
			break;
		}
		case 5: {
			
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
		
	}
	public static void updateProduct() {
		for (String s : DBProduct.searchProductsList()) {
			System.out.println(s);
		}
		System.out.println("Enter an ID which product you want update: ");
		int searchID = sc.nextInt();
		DBProduct.selectProductsID(searchID);
		System.out.print("Enter the number of the feature you want to update: ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			System.out.print("Enter new name: ");
			String newname = sc.nextLine();
			System.out.println("New Details of Updated Product");
			DBProduct.selectProductsID(searchID);
			break;
		}
		case 2: {
			System.out.print("Enter new price: ");
			Double newprice = sc.nextDouble();
			System.out.println("New Details of Updated Product");
			DBProduct.selectProductsID(searchID);
			break;
		}
		case 3: {
			System.out.print("Enter new supplier ID: ");
			String newsupplierid = sc.nextLine();
			System.out.println("New Details of Updated Product");
			DBProduct.selectProductsID(searchID);
			break;
		}
		case 4: {
			System.out.print("Enter new stock level: ");
			int newstocklevel = sc.nextInt();
			System.out.println("New Details of Updated Product");
			DBProduct.selectProductsID(searchID);
			break;
		}
		case 5: {
			System.out.print("Enter new minimum stock level: ");
			int newminimumstocklevel = sc.nextInt();
			System.out.println("New Details of Updated Product");
			DBProduct.selectProductsID(searchID);
			break;
		}
		case 6: {
			System.out.print("Enter new max storage days: ");
			String newmaxstoragedays = sc.nextLine();
			System.out.println("New Details of Updated Product");
			DBProduct.selectProductsID(searchID);
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
		System.out.print("Supplier ID: ");
		int supplierid = sc.nextInt();
		System.out.print("Minimum Stock Level: ");
		int minimumstocklevel = sc.nextInt();
		System.out.print("Max Storage Days (It can be space): ");
		String maxstoragedays = sc.next();
		PerishableProduct added = new PerishableProduct(name, price, stocklevel, supplierid, minimumstocklevel, maxstoragedays);
		DBProduct.save(added);
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
		
		for (String s : DBProduct.getProductsList()) {
			System.out.println(s);
		}
		
		System.out.print("Enter an ID which product you want reduce: ");
		int id = sc.nextInt();
		System.out.print("Enter an amount that you want reduce: ");
		int amount = sc.nextInt();
		DBProduct.reduceProduct(id,amount);
		System.out.println("New Product List");
		for (String s : DBProduct.getProductsList()) {
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
		for (String s : DBProduct.searchProductsList()) {
			System.out.println(s);
		}
		System.out.print("Enter an ID which product you want search: ");
		int searchID = sc.nextInt();
		DBProduct.selectProductsID(searchID);
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
			DBUser.update(user, user.getName(), newpass);
			user = new User(user.getName(),newpass,user.getRole());
			System.out.println("Username: "+user.getName()+" Password: "+user.getPassword());
			accountmanagement();
			break;
		}
		case 2: {
			System.out.println("Your account has been deleted");
			DBUser.delete(user);
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
