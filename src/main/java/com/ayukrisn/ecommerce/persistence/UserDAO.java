package com.ayukrisn.ecommerce.persistence;
import com.ayukrisn.ecommerce.model.Users;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    // SELECT ONE USER FROM DATABASE
    public Users selectUserById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Users user = new Users();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            while(result.next()) {
                user.setId(result.getInt("id"));
                user.setFirst_name(result.getString("first_name"));
                user.setLast_name(result.getString("last_name"));
                user.setEmail(result.getString("email"));
                user.setPhone_number(result.getString("phone_number"));
                user.setType(result.getString("type"));
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
        return user;
    }

    // SELECT ALL USER FROM DATABASE
    public ArrayList<Users> selectAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Users> listUsers = new ArrayList<Users>();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("SELECT * FROM users");
            result = statement.executeQuery();

            while(result.next()) {
                Users user = new Users();
                user.setId(result.getInt("id"));
                user.setFirst_name(result.getString("first_name"));
                user.setLast_name(result.getString("last_name"));
                user.setEmail(result.getString("email"));
                user.setPhone_number(result.getString("phone_number"));
                user.setType(result.getString("type"));
                listUsers.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listUsers;
    }

    public void addUser(Users users) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("INSERT INTO users (id, first_name, last_name," +
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
