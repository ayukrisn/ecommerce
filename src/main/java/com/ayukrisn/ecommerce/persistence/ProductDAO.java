package com.ayukrisn.ecommerce.persistence;
import com.ayukrisn.ecommerce.model.Addresses;
import com.ayukrisn.ecommerce.model.Products;
import com.ayukrisn.ecommerce.model.Users;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {

    // SELECT ONE PRODUCT FROM DATABASE
    public Products selectProductById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Products product = new Products();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            while (result.next()) {
                product.setId(result.getInt("id"));
                product.setSeller(result.getInt("seller"));
                product.setTitle(result.getString("title"));
                product.setDescription(result.getString("description"));
                product.setPrice(result.getInt("price"));
                product.setStock(result.getInt("stock"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return product;
    }

    // SELECT MULTIPLE PRODUCTS FROM DATABASE
    public ArrayList<Products> selectMultiple(String idSeller) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Products> listProducts = new ArrayList<Products>();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Connected to database");
            if (idSeller.equals("*")) {
                statement = connection.prepareStatement("SELECT * FROM products");
            }
            else {
                statement = connection.prepareStatement("SELECT * FROM products WHERE seller =" + idSeller);
            }
            result = statement.executeQuery();
            System.out.println("select done");

            while (result.next()) {
                Products product = new Products();
                product.setId(result.getInt("id"));
                product.setSeller(result.getInt("seller"));
                product.setTitle(result.getString("title"));
                product.setDescription(result.getString("description"));
                product.setPrice(result.getInt("price"));
                product.setStock(result.getInt("stock"));
                listProducts.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listProducts;
    }

    // POST PRODUCTS TO DATABASE
    public String addNewProduct(Products product) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?,?);");
            statement.setInt(1, product.getId());
            statement.setInt(2, product.getSeller());
            statement.setString(3, product.getTitle());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getPrice());
            statement.setInt(6, product.getStock());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) in the products table has been added";
                System.out.println(response);
            } else {
                response = "No rows have been added";
                System.out.println(response);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return response;
    }

    // UPDATE PRODUCT BASED ON ID
    public String updateProduct(Products product, int idProduct) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;
        System.out.println("Connected to database");

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("UPDATE products SET seller = ?, title = ?, description = ?, " +
                    "price = ?, stock = ? WHERE id = " + idProduct);
            statement.setInt(1, product.getSeller());
            statement.setString(2, product.getTitle());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getPrice());
            statement.setInt(5, product.getStock());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) in the products table has been updated";
                System.out.println(response);
            } else {
                response = "No rows have been added";
                System.out.println(response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return response;
    }


    // DELETE PRODUCT BASED ON ID
    public String deleteProduct(int idProduct) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("DELETE FROM products WHERE id = " + idProduct);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) have been affected";
                System.out.println(response);
            } else {
                response = "No rows have been affected";
                System.out.println(response);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return response;
    }

}