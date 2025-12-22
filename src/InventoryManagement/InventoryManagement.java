package InventoryManagement;

import java.util.Scanner;

import DataBaseCon.DBProduct;
import DataBaseCon.DBUser;

public class InventoryManagement {
	public static void login() {
		Scanner sc = new Scanner(System.in);
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
			User user = new User(username, password, "user");
			DBUser.save(user);
			break;
			
		}
		case 2: {
			System.out.print("Username: ");
			String username = sc.next();
			System.out.println("Password: ");
			String password = sc.next();
			String selected = DBUser.selectUser(username, password);
			if (selected.equals("admin")) {
				adminpage();;
			} else if (selected.equals("user")) {
				userpage();;
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + a);
		}
	}
	public static void adminpage() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======ADMİN PAGE======");
		System.out.println("1. Product Management");
		System.out.println("2. Supplier Management");
		System.out.println("3. User Management");
		System.out.println("4. Logout");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			
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
	public static void userpage() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======USER PAGE======");
		System.out.println("1. Add new product");
		System.out.println("2. Reduce Product");
		System.out.println("3. Search Product");
		System.out.println("4. User Management");
		System.out.println("5. Account Management");
		System.out.println("6. Logout");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: {
			addProduct();
			break;
		}
		case 2: {
			
			break;
		}
		case 3: {
	
			break;
		}
		case 4: {
			
			break;
		}
		case 5: {
			
			break;
		}
		case 6: {
			login();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}
		
	}
	public static void addProduct() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Product's name: ");
		String name = sc.nextLine();
		System.out.print("Product's price: ");
		Double price = sc.nextDouble();
		System.out.print("Stock level: ");
		int stocklevel = sc.nextInt();
		System.out.print("Supplier İD: ");
		int supplierid = sc.nextInt();
		System.out.print("Minimum Stock Level: ");
		int minimumstocklevel = sc.nextInt();
		System.out.print("Max Storage Days (It can be space): ");
		String maxstoragedays = sc.next();
		PerishableProduct added = new PerishableProduct(name, price, stocklevel, supplierid, minimumstocklevel, maxstoragedays);
		DBProduct.save(added);
	}
	public static void reduceProduct() {
		
		DBProduct.getProductsList();
	}
	

}
