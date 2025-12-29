package DataBaseCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
	public static void adminUpdate(int id, String name, String password, String role) {
	    
	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String dbpassword = "sql1234"; 

	    String updateSql = "UPDATE users SET name = ?, password = ?, role = ? WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
	             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

	            preparedStatement.setString(1, name);     
	            preparedStatement.setString(2, password); 
	            preparedStatement.setString(3, role);     
	            preparedStatement.setInt(4, id);          

	            int affectedRows = preparedStatement.executeUpdate();

	            if (affectedRows > 0) {
	                System.out.println("User with ID " + id + " was updated successfully.");
	            } else {
	                System.out.println("Update failed. User with ID " + id + " not found.");
	            }
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public static User getUserById(int searchId) {
	    
	    User user = null; 

	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";

	    String selectSql = "SELECT id, name, password, role FROM users WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DriverManager.getConnection(url, dbuser, password);
	             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

	            preparedStatement.setInt(1, searchId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {

	                    String name = resultSet.getString("name");
	                    String pass = resultSet.getString("password");
	                    String role = resultSet.getString("role");

	                    user = new User(name, pass, role);
	                }
	            }
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }

	    return user; 
	}
	public static void getUserDetailsById(int searchId) {
	    
	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";

	    String selectSql = "SELECT id, name, password, role FROM users WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DriverManager.getConnection(url, dbuser, password);
	             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

	            preparedStatement.setInt(1, searchId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {

	                if (resultSet.next()) {
	                    int id = resultSet.getInt("id");
	                    String name = resultSet.getString("name");
	                    String pass = resultSet.getString("password");
	                    String role = resultSet.getString("role");

	                    System.out.println("--- User Details ---");
	                    System.out.println("   ID       : " + id);
	                    System.out.println("1. Username : " + name);
	                    System.out.println("2. Password : " + pass);
	                    System.out.println("3. Role     : " + role);
	                    System.out.println("--------------------");
	                } else {
	                    System.out.println("User with ID " + searchId + " not found.");
	                }
	            }
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
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
	public static void adminDelete(int id) {
	    
	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";

	    String deleteSql = "DELETE FROM users WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	       
	        try (Connection connection = DriverManager.getConnection(url, dbuser, password);
	             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

	            preparedStatement.setInt(1, id);
	        
	            int affectedRows = preparedStatement.executeUpdate();

	            if (affectedRows > 0) {
	                System.out.println("User with ID " + id + " was deleted successfully.");
	            } else {
	                System.out.println("Deletion failed. User with ID " + id + " not found.");
	            }
	        }
	        
	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public static List<String> getUserList() {
	    List<String> userList = new ArrayList<>();

	    String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";

	    String selectSql = "SELECT id, name FROM users";
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (Connection connection = DriverManager.getConnection(url, dbuser, password);
	             PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String name = resultSet.getString("name");

	                String userInfo = id + " - " + name;
	                userList.add(userInfo);
	            }
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }

	    return userList;
	}
	public static String selectUser(String username,String pd) {
		String url = "jdbc:mysql://localhost:3306/oop-project?useSSL=false&allowPublicKeyRetrieval=true";
	    String dbuser = "root";
	    String password = "sql1234";
	    
	 
	    String selectSql = "SELECT role FROM users WHERE name = ? AND password = ?";
	    
	    String userrole = null; 

	    try {
	   
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (
	        
	            Connection connection = DriverManager.getConnection(url, dbuser, password);
	
	            PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
	        ) {
	            
	  
	            preparedStatement.setString(1, username);     
	            preparedStatement.setString(2, pd); 

	     
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	         
	            if (resultSet.next()) {
	 
	                userrole = resultSet.getNString("role"); 
	            }
	            
	        }

	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL Driver bulunamadı: " + e.getMessage());
	  
	    } catch (SQLException e) {
	        System.out.println("Bağlantı/Sorgu hatası: " + e.getMessage());
	
	    }
	    

	    return userrole;
	    
		
	}

}
