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
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listUsers;
    }

    // POST USERS TO DATABASE
    public String addNewUser(Users user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;
        System.out.println("Connected to database");

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?);");
            System.out.println("Inserting data to table users into database");
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirst_name());
            statement.setString(3, user.getLast_name());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone_number());
            statement.setString(6, user.getType().toString());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) in the users table has been added";
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

    // DELETE USER BASED ON ID
    public String deleteUser(int idUser) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("DELETE FROM users WHERE id = " + idUser);
            result = statement.executeQuery();
            response = "1 row(s) in users table has been deleted. Row: " + idUser;
            System.out.println(response);

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return response;
    }
}
