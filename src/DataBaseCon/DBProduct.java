package DataBaseCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import InventoryManagement.PerishableProduct;


public class DBProduct {

	public static void save(PerishableProduct p){
		String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
        String dbuser = "root";
        String password = "sql1234";

        String insertSql = "INSERT INTO products (name, price, stocklevel, supplierId, minimumstocklevel, maxstoragedays) VALUES ('"
                + p.getName() + "', '"
                + p.getPrice() + "', '"
                + p.getStock() + "', '"
                + p.getSupplierId() + "', '"
                + p.getMinimumstocklevel() + "', '"
                + p.getMaxstoragedays() + "')";

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            
            Connection connection = DriverManager.getConnection(url, dbuser, password);

            System.out.println("MySQL bağlantısı başarılı.");
            
            
            Statement statement = connection.createStatement();
            
            
            int affectedRows = statement.executeUpdate(insertSql);
            
            
            if (affectedRows > 0) {
                System.out.println("✅ Veri başarıyla eklendi.");
            } else {
                System.out.println("❌ Veri eklenirken bir sorun oluştu.");
            }

            
            statement.close();
            connection.close();
            
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver bulunamadı: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Bağlantı/Sorgu hatası: " + e.getMessage());
        }
	}
	public static List<String> getProductsList() {
	
	    List<String> productList = new ArrayList<>();

	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";


	    String selectSql = "SELECT id, name, stocklevel FROM products";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (Connection connection = DriverManager.getConnection(url, dbuser, password);
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

	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";


	    String selectSql = "SELECT id, name FROM products";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (Connection connection = DriverManager.getConnection(url, dbuser, password);
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
	    
	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";

	    String selectSql = "SELECT id, name, price, supplierId, stocklevel, minimumstocklevel, maxstoragedays " +
	                       "FROM products WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver"); 

	        try (Connection connection = DriverManager.getConnection(url, dbuser, password);
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
	public static void deleteProduct(int id) {
		String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";


	    String deleteSql = "DELETE FROM products WHERE id = " + id;

	    Connection connection = null;
	    Statement statement = null;

	    try {
	  
	        Class.forName("com.mysql.cj.jdbc.Driver");
	       
	    
	        connection = DriverManager.getConnection(url, dbuser, password);

	        System.out.println("MySQL bağlantısı başarılı.");
	        
	     
	        statement = connection.createStatement();
	        
	    
	        int affectedRows = statement.executeUpdate(deleteSql);
	        

	        if (affectedRows > 0) {
	            System.out.println("Product reduced succesfully");
	        } else {
	            System.out.println("Product reduced unsuccesfully");
	        }
	        
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver bulunamadı: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Bağlantı/Sorgu hatası: " + e.getMessage());
	    } finally {
	  
	        try {
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            System.out.println("Kaynakları kapatırken hata oluştu: " + e.getMessage());
	        }
	    }
		
	}
	

	public static void reduceProduct(int id, int amount) {
	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";

	    
	    String updateSql = "UPDATE products SET stocklevel = stocklevel - ? WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	       
	        try (Connection connection = DriverManager.getConnection(url, dbuser, password);
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
}
