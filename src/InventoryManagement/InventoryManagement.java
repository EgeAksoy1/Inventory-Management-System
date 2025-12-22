package InventoryManagement;

import java.util.Scanner;

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
		
		System.out.println("======ADMİN PAGE======");
		System.out.println("1. Product Management");
		System.out.println("2. Supplier Management");
		System.out.println("3. User Management");
		System.out.println("4. Logout");
	}
	public static void userpage() {
		
	}

}
