package com.ayukrisn.ecommerce.persistence;

import com.ayukrisn.ecommerce.model.OrderDetails;
import com.ayukrisn.ecommerce.model.Orders;
import com.ayukrisn.ecommerce.model.Products;
import com.ayukrisn.ecommerce.model.Users;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {
    // SELECT ORDERS FROM ID
    public ArrayList<Orders> selectOrderById(String idType, int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Orders> listOrders = new ArrayList<Orders>();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("SELECT * FROM orders WHERE " + idType +" = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            while(result.next()) {
                Orders order = new Orders();
                order.setId(result.getInt("id"));
                order.setBuyer(result.getInt("buyer"));
                order.setNote(result.getString("note"));
                order.setTotal(result.getInt("total"));
                order.setDiscount(result.getInt("discount"));
                order.setIs_paid(result.getInt("is_paid"));
                listOrders.add(order);
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
        return listOrders;
    }

    // SELECT ORDER DETAILS FROM ORDER'S ID
    public ArrayList<OrderDetails> selectOrderDetailsById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<OrderDetails> listOrderDetails = new ArrayList<OrderDetails>();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("SELECT * FROM order_details WHERE orders = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            while(result.next()) {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setOrder(result.getInt("orders"));
                orderDetail.setProduct(result.getInt("product"));
                orderDetail.setQuantity(result.getInt("quantity"));
                orderDetail.setPrice(result.getInt("price"));
                listOrderDetails.add(orderDetail);
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
        return listOrderDetails;
    }
}
