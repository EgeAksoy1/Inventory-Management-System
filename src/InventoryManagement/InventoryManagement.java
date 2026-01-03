package InventoryManagement;

import java.time.LocalDate;
import java.util.Scanner;



public class InventoryManagement {
	private Scanner sc = new Scanner(System.in);
	private User user;
	private User newuser;
	private PerishableProduct product;
	private Supplier supplier;
	public void login() {
		int a = 0;
		boolean b = true;
		do {
			try {
				System.out.println("1. Sign Up");
				System.out.println("2. Login");
				System.out.print("Type a number: ");
				a = sc.nextInt();
				sc.nextLine();
				b = false;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
			}
		}while(b);
		switch (a) {
		case 1: {
			b = true;
			do {
				try {
					System.out.print("Username: ");
					String username = sc.nextLine();
					if (username.isEmpty()) {
						System.out.println("Invalid input please try again");
						continue;
					}
					System.out.print("Password: ");
					String password = sc.nextLine();
					if (password.isEmpty()) {
						System.out.println("Invalid input please try again");
						continue;
					}
					user = new User(username, password, "user");
					user.save();
					b = false;
					System.out.println("Now you have a account");
				}catch (Exception e) {
					System.out.println("Invalid input please try again");
					sc.nextLine();
				}
			}while(b);
			pageselecter(user.getName(),user.getPassword());
			break;
		}
		case 2: {
			b = true;
			String username = null, password = null;
			do {
				try {
					System.out.print("Username: ");
					username = sc.nextLine();
					if (username.isEmpty()) {
						System.out.println("Invalid input please try again");
						continue;
					}
					System.out.print("Password: ");
					password = sc.nextLine();
					if (password.isEmpty()) {
						System.out.println("Invalid input please try again");
						continue;
					}
					String selected = User.selectUser(username, password);
					if (selected == null) {
						System.out.println("Username or Password is wrong please try again");
						continue;
					}
					b = false;
				}catch (Exception e) {
					System.out.println("Invalid input please try again");
				}
			}while(b);
			pageselecter(username, password);
			break;
		}
		default:
			System.out.println("\nInvalid input please try again");
			login();
		}
	}
	public void pageselecter(String username,String password) {
		String selected = User.selectUser(username, password);
		if (selected.equals("admin")) {
			user = new User(username, password, "admin");
			adminpage();
		} else if (selected.equals("user")) {
			user = new User(username, password, "user");
			userpage();
		}
	}
	public void adminpage() {
		
		int choose = 0;
		boolean b = true;
		do {
			try {
				System.out.println("======ADMİN PAGE======");
				System.out.println("1. Product Management");
				System.out.println("2. Supplier Management");
				System.out.println("3. User Management");
				System.out.println("4. Logout");
				System.out.print("Type a number: ");
				choose = sc.nextInt();
				sc.nextLine();
				b = false;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
			}
		}while(b);
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
			System.out.println("Invalid input please try again");
			adminpage();
		}
	}
	public void pageSupplierManagement() {
		int choose = 0;
		boolean b = true;
		do {
			try {
				System.out.println("Supplier Management Page");
				System.out.println("1. Add new Supplier");
				System.out.println("2. Delete Supplier");
				System.out.println("3. Update Supplier");
				System.out.println("4. Back");
				System.out.print("Type a number: ");
				choose = sc.nextInt();
				sc.nextLine();
				b = false;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
			}
		}while(b);
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
			System.out.println("Invalid input please try again");
			pageSupplierManagement();
		}
	}
	public void updateSupplier() {
		int search = 0;
		int choose = 0;
		try {
			for(String s:Supplier.getSupplierList()) {
				System.out.println(s);
			}
			System.out.print("Enter an ID which supplier you want update: ");
			search = sc.nextInt();
			Supplier.getSupplierDetailsById(search);
			supplier = Supplier.getSupplierById(search);
			System.out.print("Enter the number of the detail you want to update: ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			updateSupplier();
			return;
		}
		switch (choose) {
		case 1: {
			try {
				System.out.print("New Supplier name: ");
				String newname = sc.nextLine();
				if (newname.isEmpty()) {
					System.out.println("Invalid input please try again");
					sc.nextLine();
					updateSupplier();
				}
				Supplier.updateSupplier(search, newname, supplier.getAddress(), supplier.getLastorderDate());
				pagebackSupplier();
				break;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				updateSupplier();
				return;
			}
		}
		case 2: {
			try {
				System.out.print("New Supplier address: ");
				String newaddress = sc.nextLine();
				if (newaddress.isEmpty()) {
					System.out.println("Invalid input please try again");
					sc.nextLine();
					updateSupplier();
				}
				Supplier.updateSupplier(search, supplier.getSuppliername(), newaddress, supplier.getLastorderDate());
				pagebackSupplier();
				break;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				updateSupplier();
				return;
			}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			updateSupplier();
		}
	}
	public void pagebackSupplier() {
		int choose2 = 0;
		try {
			System.out.println("1. Back      2.Quit");
			System.out.print("Type a number (1-2): ");
			choose2 = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pagebackSupplier();
			return;
		}
		if (choose2 == 1) {
			updateSupplier();
		}else if(choose2 == 2) {
			adminpage();
		}else {
			System.out.println("Invalid input");
			pagebackSupplier();
		}
	}
	public void deleteSupplier() {
		for(String s:Supplier.getSupplierList()) {
			System.out.println(s);
		}
		int searchID = 0;
		try {
			System.out.print("Enter an ID which supplier you want delete: ");
			searchID = sc.nextInt();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			deleteSupplier();
			return;
		}
		Supplier deleteSupplier = Supplier.getSupplierById(searchID);
		deleteSupplier.delete(searchID);
		System.out.println("New Supplier List");
		for(String s:Supplier.getSupplierList()) {
			System.out.println(s);
		}
		int choose = 0;
		try {
			System.out.println("1. Quit     2. Back");
			System.out.print("Type a number (1-2): ");
			choose = sc.nextInt();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			deleteSupplier();
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			deleteSupplier();
		}
	}
	public void addSupplier() {
		String suppliername = null;
		String address = null;
		try {
			System.out.print("Supplier name: ");
			suppliername = sc.nextLine();
			System.out.print("Supplier address: ");
			address = sc.nextLine();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			addSupplier();
			return;
		}
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
		int choose = 0;
		try {
			System.out.println("1. Quit     2. Back");
			System.out.print("Type a number (1-2): ");
			choose = sc.nextInt();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageSupplierManagement();
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			addSupplier();
		}
	}
	public void pageUserManagement() {
		System.out.println("User Management Page");
		System.out.println("1. Add new User");
		System.out.println("2. Delete User");
		System.out.println("3. Update User");
		System.out.println("4. Search User");
		System.out.println("5. Back");
		int choose = 0;
		try {
			System.out.print("Type a number: ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageUserManagement();
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageUserManagement();
		}
	}
	public void adminsearchUser() {
		for (String s : User.getUserList()) {
			System.out.println(s);
		}
		int choose = 0;
		try {
			System.out.print("Enter the ID of the user to search:");
			int userid = sc.nextInt();
			User.getUserDetailsById(userid);
			System.out.println("1. Back    2. Quit");
			System.out.print("Type a number: ");
			choose = sc.nextInt();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			adminsearchUser();
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			adminsearchUser();
		}
	}
	public void adminupdateUser() {
		for (String s : User.getUserList()) {
			System.out.println(s);
		}
		int choose = 0;
		int userid = 0;
		try {
			System.out.print("Enter the ID of the user to update: ");
			userid = sc.nextInt();
			User.getUserDetailsById(userid);
			newuser = User.getUserById(userid);
			System.out.print("Enter the number of the detail you want to update: ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			adminupdateUser();
			return;
		}
		switch (choose) {
		case 1: {
			String newusername = null;
			try {
				System.out.print("New username: ");
				newusername = sc.nextLine();
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				adminupdateUser();
				return;
			}
			User.adminUpdate(userid, newusername, newuser.getPassword(), newuser.getRole());
			System.out.println("New Details of User");
			User.getUserDetailsById(userid);
			pagebackUser();
			break;
		}
		case 2: {
			String newpassword = null;
			try {
				System.out.print("New password: ");
				newpassword = sc.nextLine();
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				adminupdateUser();
				return;
			}
			User.adminUpdate(userid, newuser.getName(), newpassword, newuser.getRole());
			System.out.println("New Details of User");
			User.getUserDetailsById(userid);
			pagebackUser();
			break;
		}
		case 3: {
			String newrole = null;
			try {
				System.out.print("New role: ");
				newrole = sc.nextLine();
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				adminupdateUser();
				return;
			}
			User.adminUpdate(userid, newuser.getName(), newuser.getPassword(), newrole);
			System.out.println("New Details of User");
			User.getUserDetailsById(userid);
			pagebackUser();
			break;
		}
		default:
			System.out.println("Invalid input please try again");
			sc.nextLine();
			adminupdateUser();
		}
	}
	public void pagebackUser() {
		int choose2 = 0;
		try {
			System.out.println("1. Back      2.Quit");
			System.out.print("Type a number (1-2): ");
			choose2 = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pagebackUser();
			return;
		}
		if (choose2 == 1) {
			adminupdateUser();
		}else if(choose2 == 2) {
			adminpage();
		}else {
			System.out.println("Invalid input");
			pagebackUser();
		}
	}
	public void admindeleteUser() {
		for (String s : User.getUserList()) {
			System.out.println(s);
		}
		int userid = 0;
		try {
			System.out.print("Enter the ID of the user to delete:");
			userid = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			admindeleteUser();
			return;
		}
		User.adminDelete(userid);
		int choose = 0;
		try {
			System.out.println("1. Back     2. Quit");
			System.out.print("Type a number (1-2): ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			admindeleteUser();
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			admindeleteUser();
		}
	}
	public void adminsaveUser() {
		String username, password, role = null;
		try {
			System.out.print("Username: ");
			username = sc.next();
			System.out.print("Password: ");
			password = sc.next();
			System.out.print("Role: ");
			role = sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			adminsaveUser();
			return;
		}
		newuser = new User(username, password, role);
		newuser.save();
		int choose = 0;
		try {
			System.out.print("Type a number (1-2): ");
			choose = sc.nextInt();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			adminpage();
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			admindeleteUser();
		}
		
	}
	public void pageProductManagement() {
		int choose = 0;
		try {
			System.out.println("Product Management Page");
			System.out.println("1. Add new Product");
			System.out.println("2. Reduce Product");
			System.out.println("3. Update Product");
			System.out.println("4. Delete Product");
			System.out.println("5. Back");
			System.out.print("Type a number: ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageProductManagement();
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageProductManagement();
		}
		
	}
	public void deleteProduct() {
		int searchID = 0;
		try {
			for (String s : PerishableProduct.searchProductsList()) {
				System.out.println(s);
			}
			System.out.print("Enter an ID which product you want delete: ");
			searchID = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			deleteProduct();
			return;
		}
		PerishableProduct deletePerishableProduct = PerishableProduct.getPerishableProductById(searchID);
		deletePerishableProduct.delete(searchID);
		System.out.println("Final Product List");
		for (String s : PerishableProduct.searchProductsList()) {
			System.out.println(s);
		}
		int choose = 0;
		try {
			System.out.print("1. Back     2. Quit");
			System.out.print("Type a number: ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			deleteProduct();
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			deleteProduct();
		}
	}
	public void pageBack() {
		int choose2 = 0;
		try {
			System.out.println("1. Back     2. Quit");
			System.out.print("Type a number (1-2): ");
			choose2 = sc.nextInt();
			if (choose2 == 1) {
				updateProduct();
			}else if(choose2 == 2) {
				adminpage();
			}else {
				System.out.println("Invalid input");
				pageBack();
			}
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageBack();
			return;
		}
	}
	public void updateProduct() {
		int searchID = 0;
		int choose = 0;
		try {
			for (String s : PerishableProduct.searchProductsList()) {
				System.out.println(s);
			}
			System.out.println("Enter an ID which product you want update: ");
			searchID = sc.nextInt();
			sc.nextLine();
			PerishableProduct.selectProductsID(searchID);
		    product = PerishableProduct.getPerishableProductById(searchID);
			System.out.print("Enter the number of the detail you want to update: ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			updateProduct();
			return;
		}
		switch (choose) {
		case 1: {
			try {
				System.out.print("Enter new name: ");
				String newname = sc.nextLine();
				PerishableProduct.updateProduct(searchID, newname, product.getPrice(), product.getSupplierId(), product.getStock(), product.getMinimumstocklevel(), product.getMaxstoragedays());
				System.out.println("New Details of Updated Product");
				PerishableProduct.selectProductsID(searchID);
				pageBack();
				break;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				updateProduct();
				return;
			}
		}
		case 2: {
			try {
				System.out.print("Enter new price: ");
				Double newprice = sc.nextDouble();
				PerishableProduct.updateProduct(searchID, product.getName(), newprice, product.getSupplierId(), product.getStock(), product.getMinimumstocklevel(), product.getMaxstoragedays());
				System.out.println("New Details of Updated Product");
				PerishableProduct.selectProductsID(searchID);
				pageBack();
				break;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				updateProduct();
				return;
			}
		}
		case 3: {
			try {
				System.out.print("Enter new supplier ID: ");
				int newsupplierid = sc.nextInt();
				PerishableProduct.updateProduct(searchID, product.getName(), product.getPrice(), newsupplierid, product.getStock(), product.getMinimumstocklevel(), product.getMaxstoragedays());
				System.out.println("New Details of Updated Product");
				PerishableProduct.selectProductsID(searchID);
				pageBack();
				break;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				updateProduct();
				return;
			}
		}
		case 4: {
			try {
				System.out.print("Enter new stock level: ");
				int newstocklevel = sc.nextInt();
				PerishableProduct.updateProduct(searchID, product.getName(), product.getPrice(), product.getSupplierId(), newstocklevel, product.getMinimumstocklevel(), product.getMaxstoragedays());
				System.out.println("New Details of Updated Product");
				PerishableProduct.selectProductsID(searchID);
				pageBack();
				break;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				updateProduct();
				return;
			}
		}
		case 5: {
			try {
				System.out.print("Enter new minimum stock level: ");
				int newminimumstocklevel = sc.nextInt();
				PerishableProduct.updateProduct(searchID, product.getName(), product.getPrice(), product.getSupplierId(), product.getStock(), newminimumstocklevel, product.getMaxstoragedays());
				System.out.println("New Details of Updated Product");
				PerishableProduct.selectProductsID(searchID);
				pageBack();
				break;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				updateProduct();
				return;
			}
		}
		case 6: {
			try {
				System.out.print("Enter new max storage days: ");
				String newmaxstoragedays = sc.nextLine();
				PerishableProduct.updateProduct(searchID, product.getName(), product.getPrice(), product.getSupplierId(), product.getStock(), product.getMinimumstocklevel(), newmaxstoragedays);
				System.out.println("New Details of Updated Product");
				PerishableProduct.selectProductsID(searchID);
				pageBack();
				break;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
				sc.nextLine();
				updateProduct();
				return;
			}
		}
		default:
			System.out.println("Invalid input please try again");
			sc.nextLine();
			updateProduct();
		}
	}
	public void userpage() {
		int choose = 0;
		boolean b = true;
		do {
			try {
				System.out.println("======USER PAGE======");
				System.out.println("1. Add new product");
				System.out.println("2. Reduce Product");
				System.out.println("3. Search Product");
				System.out.println("4. Account Management");
				System.out.println("5. Logout");
				System.out.print("Type a number: ");
				choose = sc.nextInt();
				sc.nextLine();
				b = false;
			}catch (Exception e) {
				System.out.println("Invalid input please try again");
			}
		}while(b);
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
			System.out.println("Invalid input please try again");
			userpage();
		}
		
	}
	public void addProduct() {
		String name, maxstoragedays = null;
		Double price = 0.0;
		int stocklevel, supplierid, minimumstocklevel = 0;
		try {
			System.out.print("Product's name: ");
			name = sc.nextLine();
			System.out.print("Product's price: ");
			price = sc.nextDouble();
			System.out.print("Stock level: ");
			stocklevel = sc.nextInt();
			System.out.println("\n Supplier List");
			for(String s:Supplier.getSupplierList()) {
				System.out.println(s);
			}
			System.out.print("Supplier ID: ");
			supplierid = sc.nextInt();
			System.out.print("Minimum Stock Level: ");
			minimumstocklevel = sc.nextInt();
			sc.nextLine();
			System.out.print("Max Storage Days (It can be space): ");
			maxstoragedays = sc.nextLine();
			if (maxstoragedays.isEmpty()) {
				Product added = new Product(name, minimumstocklevel, stocklevel, supplierid, minimumstocklevel);
				added.save();
			}else {
				PerishableProduct added = new PerishableProduct(name, price, stocklevel, supplierid, minimumstocklevel, maxstoragedays);
				added.save();
			}
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			addProduct();
			return;
		}
		int choose = 0;
		try {
			System.out.println("1. Back     2. Add another product");
			System.out.print("Type a number (1-2): ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageselecter(user.getName(), user.getPassword());
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageselecter(user.getName(), user.getPassword());
		}
	}
	public void reduceProduct() {
		int id, amount = 0;
		try {
			for (String s : PerishableProduct.getProductsList()) {
				System.out.println(s);
			}
			
			System.out.print("Enter an ID which product you want reduce: ");
			id = sc.nextInt();
			System.out.print("Enter an amount that you want reduce: ");
			amount = sc.nextInt();
			PerishableProduct.reduceProduct(id,amount);
			PerishableProduct.reorderProduct(id);
			System.out.println("New Product List");
			for (String s : PerishableProduct.getProductsList()) {
				System.out.println(s);
			}
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			reduceProduct();
			return;
		}
		int choose = 0;
		try {
			System.out.println("1. Back     2. Reduce another product");
			System.out.print("Type a number (1-2): ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageselecter(user.getName(), user.getPassword());
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageselecter(user.getName(), user.getPassword());
			return;
		}
	}
	public void searchProduct() {
		int searchID, choose = 0;
		try {
			for (String s : PerishableProduct.searchProductsList()) {
				System.out.println(s);
			}
			System.out.print("Enter an ID which product you want search: ");
			searchID = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			searchProduct();
			return;
		}
		PerishableProduct.selectProductsID(searchID);
		try {
			System.out.println("1. Back     2. Search another product");
			System.out.print("Type a number (1-2): ");
			choose = sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageselecter(user.getName(), user.getPassword());
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageselecter(user.getName(), user.getPassword());

		}
	}
	public void accountmanagement() {
		int choose = 0;
		try {
			System.out.println("Account Management Page");
			System.out.println("Username: "+user.getName()+" Password: "+user.getPassword());
			System.out.println("1. Change the password");
			System.out.println("2. Delete the account");
			System.out.println("3. Back");
			System.out.print("Enter your choose: ");
			choose =sc.nextInt();
			sc.nextLine();
		}catch (Exception e) {
			System.out.println("Invalid input please try again");
			sc.nextLine();
			accountmanagement();
			return;
		}
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
			System.out.println("Invalid input please try again");
			sc.nextLine();
			pageselecter(user.getName(), user.getPassword());
		}
	}
	

}
