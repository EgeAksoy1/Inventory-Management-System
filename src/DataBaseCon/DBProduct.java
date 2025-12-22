package DataBaseCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import InventoryManagement.PerishableProduct;


public class DBProduct {

	public static void save(PerishableProduct p){
		String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
        String dbuser = "root";
        String password = "sql1234";

        String insertSql = "INSERT INTO products (name, price, supplierId, minimumstocklevel, maxstoragedays) VALUES ('"
                + p.getName() + "', '"
                + p.getPrice() + "', '"
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
}
