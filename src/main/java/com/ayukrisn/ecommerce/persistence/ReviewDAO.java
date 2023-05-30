package com.ayukrisn.ecommerce.persistence;

import com.ayukrisn.ecommerce.model.Addresses;
import com.ayukrisn.ecommerce.model.Orders;
import com.ayukrisn.ecommerce.model.Reviews;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDAO {
    // SELECT REVIEW FROM ID
    public ArrayList<Reviews> selectReviewById(int idUser) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Reviews> listReviews = new ArrayList<Reviews>();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Connected to the database");
            statement = connection.prepareStatement("SELECT * FROM reviews " +
                    "INNER JOIN orders ON orders.id = reviews.orders " +
                    "INNER JOIN users ON users.id = orders.buyer " +
                    "WHERE users.id = ? ;");
            statement.setInt(1, idUser);
            result = statement.executeQuery();
            System.out.println("result done");

            while(result.next()) {
                Reviews review = new Reviews();
                review.setOrder(result.getInt("orders"));
                review.setStar(result.getInt("star"));
                review.setDescription(result.getString("description"));
                listReviews.add(review);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listReviews;
    }

    // INSERT NEW REVIEW TO DATABASE
    public String addNewReview(Reviews review) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("INSERT INTO reviews VALUES (?,?,?);");
            System.out.println("Connected to database");
            statement.setInt(1, review.getOrder());
            statement.setInt(2, review.getStar());
            statement.setString(3, review.getDescription());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) has been affected";
                System.out.println(response);
            } else {
                response = "No rows have been affected";
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

    // UPDATE REVIEW BASED ON ID
    public String updateReview(Reviews review, int idOrder) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("UPDATE reviews SET star = ?, description = ? " +
                    "WHERE orders =" + idOrder);
            statement.setInt(1, review.getStar());
            statement.setString(2, review.getDescription());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) has been affected";
                System.out.println(response);
            } else {
                response = "No rows have been affected";
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

    // DELETE REVIEW
    public String deleteReview(int idOrder) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("DELETE FROM reviews WHERE orders = " + idOrder);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) have been affected";
                System.out.println(response);
            } else {
                response = "No rows have been affected";
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
