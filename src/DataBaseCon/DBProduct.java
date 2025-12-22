package DataBaseCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	                String formatliYazi = id + " - " + name + " (Mevcut Stok: " + currentStock + ")";
	                
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
}
