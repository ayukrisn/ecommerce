package com.ayukrisn.ecommerce.persistence;
import com.ayukrisn.ecommerce.model.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    public UserDAO() {

    }

    // SELECT ONE USER FROM DATABASE
    public User getUserById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        User user = null;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            user = new User(result.getInt("id"), result.getString("first_name"), result.getString("last_name"),
                    result.getString("email"), result.getString("phone_number"), result.getString("type"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return user;
    }

    // SELECT ALL USER FROM DATABASE
    public Map<Integer, User>  getAllUser() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        User user = null;
        HashMap<Integer, User> users = new HashMap<Integer, User>();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("SELECT * FROM user");
            result = statement.executeQuery();

            while(result.next()) {
                user = new User(result.getInt("id"), result.getString("first_name"), result.getString("last_name"),
                        result.getString("email"), result.getString("phone_number"), result.getString("type"));
                users.put(result.getInt("id"), user);
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

        return users;
    }

    public void addUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("INSERT INTO user (id, first_name, last_name," +
                        "email, phone_number, type" + "VALUES (?,?,?,?,?,?);");
            result = statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
}
