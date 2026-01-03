# Inventory Management System

> A comprehensive console-based stock automation system developed using **Java**, **JDBC**, and **MySQL**, built on solid Object-Oriented Programming (OOP) principles.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Connection-green?style=for-the-badge)

## Project Scope & Purpose

In traditional retail or warehouse environments, tracking inventory manually is prone to human error, insecure, and inefficient. This project aims to solve these problems by providing a **digital, role-based Inventory Management System**.

**The Main Goal:**
To automate the tracking of goods (especially perishable ones), manage supplier relationships, and ensure data integrity through a secure database connection.

**How It Works:**
* **For Staff (Users):** Provides a streamlined interface to search for products, view detailed information, and **reduce stock levels** as sales occur, ensuring real-time inventory tracking.
* **For Administrators:** Grants **full authority** over the system. Admins can perform CRUD (Create, Read, Update, Delete) operations on Users, Products, and Suppliers, ensuring total control over the supply chain.
  
### Security & Access Control (RBAC)
* **Secure Login:** Username and password authentication against a MySQL database.
* **Role-Based Access:** Distinct interfaces for **Admins** and **Users**.
* **Robust Error Handling:** Input validation loops to prevent crashes (No recursive stack overflow issues).

### Admin Dashboard
* **User Management:** Create, update, delete, and search system users.
* **Supplier Management:** Full CRUD operations for supplier details and order dates.
* **Advanced Product Control:** Manage all aspects of inventory, including critical stock levels.

### User (Staff) Dashboard
* **Stock Operations:** Reduce stock count (simulate sales) and reorder products.
* **Product Entry:** Add new standard or perishable products to the inventory.
* **Account Management:** Update personal credentials or delete accounts.

## Technical Stack
* **Language:** Java SE (Standard Edition)
* **Database:** MySQL
* **Connectivity:** JDBC (Java Database Connectivity)
* **IDE:** Eclipse

### OOP Principles Implemented
This project demonstrates key software engineering concepts:
1.  **Inheritance:** Utilized for product categorization (`PerishableProduct` extends `Product`).
2.  **Encapsulation:** Data fields are private and accessed via Getter/Setter methods to ensure data integrity.
3.  **Abstraction:** Separation of database logic (Data Access Objects) from the user interface.
4.  **Polymorphism:** Method overriding used in product saving and management.

## Installation & Setup

To run this project locally:

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/EgeAksoy1/Inventory-Management-System.git](https://github.com/EgeAksoy1/Inventory-Management-System.git)
    ```
2.  **Database Setup:**
    * Create a database named `inventory_db` in MySQL.
    * Import the provided SQL schema (if available) or create tables for `users`, `products`, and `suppliers`.
3.  **Configure JDBC:**
    * Ensure `mysql-connector-java.jar` is added to your project's build path.
    * Update the database connection string (url, username, password) in the connection class.
4.  **Run:**
    * Execute the `InventoryManagement.java` (or Main) file.
      
##  Contact
**Developer:** Ege Aksoy
**Email:** egesaksoy61@gmail.com

---
*Developed by a Computer Engineering Student | 2026*
