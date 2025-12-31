package InventoryManagement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DataBaseCon.DBHelper;

public class Supplier implements Storable{

	private int id;
	private String suppliername;
	private String address;
	private LocalDate lastorderDate;
	
	Supplier(String suppliername, String address, LocalDate lastorderdate){
		setSuppliername(suppliername);
		setAddress(address);
		setLastorderDate(lastorderdate);
	}

	public void setId(int id2) {
		this.id=id2;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getLastorderDate() {
		return lastorderDate;
	}
	public void setLastorderDate(LocalDate lastorderDate) {
		this.lastorderDate = lastorderDate;
	}
	public static List<String> getSupplierList() {

	    List<String> supplierList = new ArrayList<>();


	    String selectSql = "SELECT id, suppliername FROM suppliers";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DBHelper.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(selectSql)) {

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String name = resultSet.getString("suppliername");

	                String formatliYazi = id + " - " + name;
	                supplierList.add(formatliYazi);
	            }
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }

	    return supplierList;
	}
	
	@Override
	public void save() {
        

        String insertSql = "INSERT INTO suppliers (suppliername, address, lastorderdate) VALUES (?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DBHelper.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, this.suppliername);
                preparedStatement.setString(2, this.address);

                if (this.lastorderDate != null) {
                    preparedStatement.setDate(3, java.sql.Date.valueOf(this.lastorderDate));
                } else {
                    preparedStatement.setNull(3, java.sql.Types.DATE);
                }

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {

                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            this.id = generatedKeys.getInt(1);
                            System.out.println("Supplier saved successfully with ID: " + this.id);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver Error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
	public static void updateSupplier(int id, String name, String address, LocalDate lastOrderDate) {
	    
		String updateSql = "UPDATE suppliers SET suppliername = ?, address = ?, lastorderdate = ? WHERE id = ?";

	    try (Connection connection = DBHelper.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

	        preparedStatement.setString(1, name);
	        preparedStatement.setString(2, address);

	        if (lastOrderDate != null) {
	            preparedStatement.setDate(3, java.sql.Date.valueOf(lastOrderDate));
	        } else {
	            preparedStatement.setNull(3, java.sql.Types.DATE);
	        }

	        preparedStatement.setInt(4, id);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Supplier with ID " + id + " was updated successfully.");
	        } else {
	            System.out.println("Update failed. Supplier with ID " + id + " not found.");
	        }

	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public static Supplier getSupplierById(int searchId) {
	    
	    Supplier supplier = null; 


	    String selectSql = "SELECT id, suppliername, address, lastorderdate FROM suppliers WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DBHelper.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

	            preparedStatement.setInt(1, searchId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    int id = resultSet.getInt("id");
	                    String name = resultSet.getString("suppliername");
	                    String address = resultSet.getString("address");

	                    java.sql.Date sqlDate = resultSet.getDate("lastorderdate");
	                    LocalDate localDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;

	                    supplier = new Supplier(name, address, localDate);

	                    supplier.setId(id); 
	                }
	            }
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }

	    return supplier;
	}
	public static void getSupplierDetailsById(int searchId) {

	    String selectSql = "SELECT id, suppliername, address, lastorderdate FROM suppliers WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DBHelper.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

	            preparedStatement.setInt(1, searchId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {

	                    int id = resultSet.getInt("id");
	                    String name = resultSet.getString("suppliername");
	                    String address = resultSet.getString("address");

	                    Date lastOrderDate = resultSet.getDate("lastorderdate");

	                    System.out.println("--- Supplier Details ---");
	                    System.out.println("   ID              : " + id);
	                    System.out.println("1. Name            : " + name);
	                    System.out.println("2. Address         : " + address);
	                    System.out.println("3. Last Order Date : " + (lastOrderDate != null ? lastOrderDate : "N/A"));
	                    System.out.println("------------------------");
	                } else {
	                    System.out.println("Supplier with ID " + searchId + " not found.");
	                }
	            }
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public void delete(int id) {
	    

	    String deleteSql = "DELETE FROM suppliers WHERE id = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DBHelper.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

	            preparedStatement.setInt(1, id);

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Supplier with ID " + id + " was deleted successfully.");
	            } else {
	                System.out.println("Deletion failed. Supplier with ID " + id + " not found.");
	            }
	        }

	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver Error: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());

	        System.err.println("Note: Cannot delete a supplier if they have linked products.");
	    }
	}
}
