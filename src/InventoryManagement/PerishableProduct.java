package InventoryManagement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DataBaseCon.DBHelper;

public class PerishableProduct extends Product implements Storable{

	private String maxstoragedays;
	public PerishableProduct (String name, double price, int stock,  int supplierId, int minimumstocklevel,String maxstoragedays) {
		super(name,price,stock,supplierId,minimumstocklevel);
		setMaxstoragedays(maxstoragedays);
	}
	public void save() {
	    String insertSql = "INSERT INTO products (name, price, stocklevel, supplierId, minimumstocklevel, maxstoragedays) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection connection = DBHelper.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

	        preparedStatement.setString(1, this.getName()); 
	        preparedStatement.setDouble(2, this.getPrice());
	        preparedStatement.setInt(3, this.getStock()); 
	        preparedStatement.setInt(4, this.getSupplierId());
	        preparedStatement.setInt(5, this.getMinimumstocklevel());
	        preparedStatement.setString(6, this.getMaxstoragedays());

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows > 0) {
	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	
	                    this.setId(generatedKeys.getInt(1)); 
	                    System.out.println("Perishable Product saved successfully. Assigned ID: " + this.getId());
	                }
	            }
	        } else {
	            System.out.println("Failed to save product.");
	        }

	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public void delete(int id) {
	    String deleteSql = "DELETE FROM products WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DBHelper.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

	            preparedStatement.setInt(1, id);

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Product with ID " + id + " was deleted successfully.");
	            } else {
	                System.out.println("Deletion failed. Product with ID " + id + " not found.");
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {

	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public static PerishableProduct getPerishableProductById(int searchId) {
	    
	    PerishableProduct product = null; 

	    String selectSql = "SELECT name, price, supplierId, stocklevel, minimumstocklevel, maxstoragedays " +
	                       "FROM products WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DBHelper.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

	            preparedStatement.setInt(1, searchId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    String name = resultSet.getString("name");
	                    double price = resultSet.getDouble("price");
	                    int supplierId = resultSet.getInt("supplierId");
	                    int stockLevel = resultSet.getInt("stocklevel");
	                    int minStockLevel = resultSet.getInt("minimumstocklevel");
	                    String maxStorageDays = resultSet.getString("maxstoragedays"); 

	                    product = new PerishableProduct(name, price, supplierId, stockLevel, minStockLevel, maxStorageDays);
	                }
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }

	    return product; 
	}
	public static List<String> getProductsList() {
		
	    List<String> productList = new ArrayList<>();

	    String selectSql = "SELECT id, name, stocklevel FROM products";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (Connection connection = DBHelper.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(selectSql)) {
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String name = resultSet.getString("name");
	                int currentStock = resultSet.getInt("stocklevel");

	                String formatliYazi = id + " - " + name + " (Current Stock: " + currentStock + ")";
	                
	                productList.add(formatliYazi);
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.out.println("Driver hatası: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("SQL hatası: " + e.getMessage());
	    }

	    return productList;
	}
	public static List<String> searchProductsList() {
		
	    List<String> productList = new ArrayList<>();

	    String selectSql = "SELECT id, name FROM products";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (Connection connection = DBHelper.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(selectSql)) {
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String name = resultSet.getString("name");

	                String formatliYazi = id + " - " + name ;
	                
	                productList.add(formatliYazi);
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.out.println("Driver hatası: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("SQL hatası: " + e.getMessage());
	    }

	    return productList;
	}
	public static void selectProductsID(int searchId) {

	    String selectSql = "SELECT id, name, price, supplierId, stocklevel, minimumstocklevel, maxstoragedays " +
	                       "FROM products WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver"); 

	        try (Connection connection = DBHelper.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
	             
	            preparedStatement.setInt(1, searchId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    int id = resultSet.getInt("id");
	                    String name = resultSet.getString("name");
	                    double price = resultSet.getDouble("price");
	                    String supplier = resultSet.getString("supplierId");
	                    int stockLevel = resultSet.getInt("stocklevel");
	                    int minStockLevel = resultSet.getInt("minimumstocklevel");
	                    String maxStorageDays = resultSet.getString("maxstoragedays");

	                    System.out.println("Product Details");
	                    System.out.println("   ID                  : " + id);
	                    System.out.println("1. Name                : " + name);
	                    System.out.println("2. Price               : " + price);
	                    System.out.println("3. Supplier ID         : " + supplier);
	                    System.out.println("4. Stock Level         : " + stockLevel);
	                    System.out.println("5. Minimum Stock Level : " + minStockLevel);
	                    System.out.println("6. Max Storage Days    : " + maxStorageDays);
	                } else {
	                    System.out.println("Product with ID " + searchId + " not found.");
	                }
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public static void updateProduct(int id, String name, double price, int supplierId, int stockLevel, int minStockLevel, String maxStorageDays) {

	    String updateSql = "UPDATE products SET name=?, price=?, supplierId=?, stocklevel=?, minimumstocklevel=?, maxstoragedays=? WHERE id=?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DBHelper.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

	            preparedStatement.setString(1, name);
	            preparedStatement.setDouble(2, price);
	            preparedStatement.setInt(3, supplierId);
	            preparedStatement.setInt(4, stockLevel);
	            preparedStatement.setInt(5, minStockLevel);
	            preparedStatement.setString(6, maxStorageDays); 
	            preparedStatement.setInt(7, id);
	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Product with ID " + id + " was updated successfully.");
	            } else {
	                System.out.println("Update failed. Product with ID " + id + " not found.");
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public static void reduceProduct(int id, int amount) {

	    String updateSql = "UPDATE products SET stocklevel = stocklevel - ? WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	       
	        try (Connection connection = DBHelper.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

	            System.out.println("MySQL bağlantısı başarılı.");

	          
	            preparedStatement.setInt(1, amount); 
	            preparedStatement.setInt(2, id);    

	            int affectedRows = preparedStatement.executeUpdate();

	            if (affectedRows > 0) {
	                System.out.println("Product stock reduced successfully.");
	            } else {
	                System.out.println("Update failed. Product ID may not exist.");
	            }
	        }

	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver bulunamadı: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Bağlantı/Sorgu hatası: " + e.getMessage());
	    }
	}
	public String getMaxstoragedays() {
		return maxstoragedays;
	}
	public void setMaxstoragedays(String maxstoragedays2) {
		this.maxstoragedays = maxstoragedays2;
	}
}
