package com.ayukrisn.ecommerce.persistence;

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listReviews;
    }
}
