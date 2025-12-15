package DataBaseCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



import java.sql.SQLException;
import InventoryManagement.User;

public class DBUser{
	User user;
	private static Integer getId(User user) {
	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";
	    
	 
	    String selectSql = "SELECT id FROM users WHERE name = ? AND password = ?";
	    
	    Integer userId = null; 

	    try {
	   
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (
	        
	            Connection connection = DriverManager.getConnection(url, dbuser, password);
	
	            PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
	        ) {
	            
	  
	            preparedStatement.setString(1, user.getName());     
	            preparedStatement.setString(2, user.getPassword()); 

	     
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	         
	            if (resultSet.next()) {
	 
	                userId = resultSet.getInt("id"); 
	            }
	            
	        }

	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver bulunamadı: " + e.getMessage());
	  
	    } catch (SQLException e) {
	        System.out.println("Bağlantı/Sorgu hatası: " + e.getMessage());
	
	    }
	    

	    return userId;
	    
	}
	public static void save(User user){
		String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
        String dbuser = "root";
        String password = "sql1234";

        
        
        String insertSql = "INSERT INTO users (name, password, role) VALUES ('"
                + user.getName() + "', '"
                + user.getPassword() + "', '"
                + user.getRole() + "')";

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
	public static void update(User user,String newname,String newpassword) {
		String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
        String dbuser = "root";
        String password = "sql1234";

        
        
        String updateSql = "UPDATE users SET name = '" + newname + "', password = '" + newpassword + "' WHERE id = " + getId(user);

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            
            Connection connection = DriverManager.getConnection(url, dbuser, password);

            System.out.println("MySQL bağlantısı başarılı.");
            
            
            Statement statement = connection.createStatement();
            
            
            int affectedRows = statement.executeUpdate(updateSql);
            
            
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
	public static void delete(User user) {
	    
	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";


	    String deleteSql = "DELETE FROM users WHERE id = " + getId(user);

	    Connection connection = null;
	    Statement statement = null;

	    try {
	  
	        Class.forName("com.mysql.cj.jdbc.Driver");
	       
	    
	        connection = DriverManager.getConnection(url, dbuser, password);

	        System.out.println("MySQL bağlantısı başarılı.");
	        
	     
	        statement = connection.createStatement();
	        
	    
	        int affectedRows = statement.executeUpdate(deleteSql);
	        

	        if (affectedRows > 0) {
	            System.out.println("✅ Kullanıcı başarıyla silindi.");
	        } else {
	            System.out.println("❌ Kullanıcı silinirken bir sorun oluştu veya kullanıcı bulunamadı.");
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

}
