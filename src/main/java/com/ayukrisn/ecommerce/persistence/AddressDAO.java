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
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listAddresses;
    }
}
