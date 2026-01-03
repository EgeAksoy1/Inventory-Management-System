package InventoryManagement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DataBaseCon.DBHelper;

public class User implements Storable{

	private int id;
	private String name;
	private String password;
	private String role;
	
	public User(String name,String password,String role) {
		setName(name);
		setPassword(password);
		setRole(role);
	}
	@Override
	public void save() {
		
	    String insertSql = "INSERT INTO users (name, password, role) VALUES (?, ?, ?)";

	    try (Connection connection = DBHelper.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

	        preparedStatement.setString(1, this.name);
	        preparedStatement.setString(2, this.password);
	        preparedStatement.setString(3, this.role);

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows > 0) {

	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    this.id = generatedKeys.getInt(1);
	                    System.out.println("User saved successfully. Assigned ID: " + this.id);
	                }
	            }
	        } else {
	            System.out.println("Failed to save user.");
	        }

	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public void delete() {

	    String deleteSql = "DELETE FROM users WHERE id = ?";

	    try (Connection connection = DBHelper.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

	        preparedStatement.setInt(1, this.id);

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("User deleted successfully.");

	        } else {
	            System.out.println("Deletion failed. User not found.");
	        }

	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public void update(String newName, String newPassword) {

	    String updateSql = "UPDATE users SET name = ?, password = ? WHERE id = ?";

	    try (Connection connection = DBHelper.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

	        preparedStatement.setString(1, newName);

	        preparedStatement.setString(2, newPassword);

	        preparedStatement.setInt(3, this.id);

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("User updated successfully.");
	            this.name = newName;
	            this.password = newPassword;
	            
	        } else {
	            System.out.println("Update failed. User not found.");
	        }

	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public static void adminUpdate(int id, String name, String password, String role) {
 

	    String updateSql = "UPDATE users SET name = ?, password = ?, role = ? WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DBHelper.getConnection();
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
	public static void getUserDetailsById(int searchId) {

	    String selectSql = "SELECT id, name, password, role FROM users WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DBHelper.getConnection();
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
	public static User getUserById(int searchId) {
    
	    User user = null; 
	
	    String selectSql = "SELECT id, name, password, role FROM users WHERE id = ?";
	
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	
	        try (Connection connection = DBHelper.getConnection();
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
	public static void adminDelete(int id) {

	    String deleteSql = "DELETE FROM users WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	       
	        try (Connection connection = DBHelper.getConnection();
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
	    
	    String selectSql = "SELECT id, name FROM users";
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (Connection connection = DBHelper.getConnection();
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

	    String selectSql = "SELECT role FROM users WHERE name = ? AND password = ?";
	    
	    String userrole = null; 

	    try {
	   
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (
	        
	            Connection connection = DBHelper.getConnection();
	
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
	        System.out.println("Driver Error: " + e.getMessage());
	  
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	
	    }
	    

	    return userrole;
	    
		
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
