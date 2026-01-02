package InventoryManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DataBaseCon.DBHelper;

public class Product implements Storable{

	private int id;
	private String name;
	private double price;
	private int supplierId;
	private int minimumstocklevel;
	private int stock;
	
	public Product(String name, double price,int stock, int supplierId, int minimumstocklevel) {
		setName(name);
		setPrice(price);
		setStock(stock);
		setSupplierId(supplierId);
		setMinimumstocklevel(minimumstocklevel);
	}
	public void save() {
	    String insertSql = "INSERT INTO products (name, price, stocklevel, supplierId, minimumstocklevel) VALUES (?, ?, ?, ?, ?)";

	    try (Connection connection = DBHelper.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

	        preparedStatement.setString(1, this.getName()); 
	        preparedStatement.setDouble(2, this.getPrice());
	        preparedStatement.setInt(3, this.getStock()); 
	        preparedStatement.setInt(4, this.getSupplierId());
	        preparedStatement.setInt(5, this.getMinimumstocklevel());

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows > 0) {
	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	
	                    this.setId(generatedKeys.getInt(1)); 
	                    System.out.println("Product saved successfully. Assigned ID: " + this.getId());
	                }
	            }
	        } else {
	            System.out.println("Failed to save product.");
	        }

	    } catch (SQLException e) {
	        System.err.println("SQL Error: " + e.getMessage());
	    }
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getMinimumstocklevel() {
		return minimumstocklevel;
	}

	public void setMinimumstocklevel(int minimumstocklevel) {
		this.minimumstocklevel = minimumstocklevel;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void delete(int id) {

	}
}
