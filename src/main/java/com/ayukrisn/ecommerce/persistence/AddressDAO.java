package com.ayukrisn.ecommerce.persistence;

import com.ayukrisn.ecommerce.model.Addresses;

import java.sql.*;
import java.util.ArrayList;

public class AddressDAO {
    // SELECT ALL ADDRESSES BASED ON USER'S ID
    public ArrayList<Addresses> selectAddressesByUser(int idUser) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Addresses> listAddresses = new ArrayList<Addresses>();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("SELECT * FROM addresses WHERE user = " + idUser);
            result = statement.executeQuery();

            while(result.next()) {
                Addresses address = new Addresses();
                address.setUser(result.getInt("user"));
                address.setType(result.getString("type"));
                address.setLine1(result.getString("line1"));
                address.setLine2(result.getString("line2"));
                address.setCity(result.getString("city"));
                address.setProvince(result.getString("province"));
                address.setPostcode(result.getString("postcode"));
                listAddresses.add(address);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listAddresses;
    }

    // INSERT NEW ADDRESS
    public String addNewAddress(Addresses address) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("INSERT INTO addresses VALUES (?,?,?,?,?,?,?)");
            statement.setInt(1, address.getUser());
            statement.setString(2, address.getType().toString());
            statement.setString(3, address.getLine1());
            statement.setString(4, address.getLine2());
            statement.setString(5, address.getCity());
            statement.setString(6, address.getProvince());
            statement.setString(7, address.getPostcode());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) in the address table has been added";
                System.out.println(response);
            } else {
                response = "No rows have been added";
                System.out.println(response);
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return response;
    }

    // DELETE ADDRESS BASED ON ID
    public String deleteAddress(int idUser) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            statement = connection.prepareStatement("DELETE FROM address WHERE user = " + idUser);
            result = statement.executeQuery();
            response = "1 row(s) in addresses table has been deleted. User ID: " + idUser;
            System.out.println(response);

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return response;
    }
}
