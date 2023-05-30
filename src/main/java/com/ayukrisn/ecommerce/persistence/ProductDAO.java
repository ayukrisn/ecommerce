package com.ayukrisn.ecommerce.persistence;
import com.ayukrisn.ecommerce.model.Products;

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listProducts;
    }
}