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

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listOrderDetails;
    }

    // INSERT NEW ORDER TO DATABASE
    public String addNewOrder(Orders orders) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;
        System.out.println("Connected to database");

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?,?);");
            System.out.println("Inserting data to table orders into database");
            statement.setInt(1, orders.getId());
            statement.setInt(2, orders.getBuyer());
            statement.setString(3, orders.getNote());
            statement.setInt(4, orders.getTotal());
            statement.setInt(5, orders.getDiscount());
            statement.setInt(6, orders.getIs_paid());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) in the orders table has been added";
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

    // INSERT NEW ORDER DETAIL TO DATABASE
    public String addNewOrderDetail(OrderDetails orderDetails) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response = "";

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("INSERT INTO order_details VALUES (" +
                    orderDetails.getOrder()  + "," +
                    orderDetails.getProduct() + "," +
                    orderDetails.getQuantity() + "," +
                    orderDetails.getPrice() + ");");
            System.out.println("Inserting data to table order_details into database");

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) in the order_details table has been added";
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
}
