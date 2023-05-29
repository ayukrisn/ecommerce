package com.ayukrisn.ecommerce.persistence;

// Package class dan interfaces untuk mengakses dan memproses data
// pada database
import java.sql.*;
public class Database {
    public static void main(String[] args) throws SQLException {
        // variabel koneksi
        Connection connection = null;

        // Percobaan untuk terhubung ke database
        try {
            // load SQLite JDBC Driver
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Database sukses dibuka");
        } catch ( Exception e ) {
            // Print error bila gagal
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        // Misalkan database belum ada / tabel blm ada
        createTableUsers(connection);
        createTableAddresses(connection);
        createTableProducts(connection);
        createTableOrders(connection);
        createTableOrderDetails(connection);
        createTableReviews(connection);
    }

    private static void createTableUsers(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS users(" +
                "id INTEGER PRIMARY KEY, " +
                "first_name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "email VARCHAR(100) NOT NULL, " +
                "phone_number VARCHAR(15) NOT NULL, " +
                "type TEXT CHECK(type IN('buyer', 'seller')) NOT NULL);"
                );

        // System.out.println("Tabel Users berhasil dibuat.");
    }

    private static void createTableAddresses (Connection connection) throws  SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS addresses(" +
                "user INTEGER NOT NULL, " +
                "type TEXT CHECK(type IN('office', 'home')) NOT NULL, " +
                "line1 VARCHAR(100) NOT NULL, " +
                "line2 VARCHAR(100) NULL, " +
                "city VARCHAR(100) NOT NULL, " +
                "province VARCHAR(100) NOT NULL, " +
                "postcode VARCHAR(15) NOT NULL, " +
                "FOREIGN KEY(user) REFERENCES user(id));"
                );

        // System.out.println("Tabel Addresses berhasil dibuat");
    }

    private static void createTableProducts (Connection connection) throws  SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS products(" +
                "id INTEGER PRIMARY KEY, " +
                "seller INTEGER NOT NULL, " +
                "title VARCHAR(100) NOT NULL, " +
                "description TEXT NOT NULL, " +
                "price INTEGER NOT NULL, " +
                "stock INTEGER NOT NULL);"
                );

        // System.out.println("Tabel Products berhasil dibuat");
    }

    private static void createTableOrders (Connection connection) throws  SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS orders (" +
                "id INTEGER PRIMARY KEY, " +
                "buyer INTEGER NOT NULL, " +
                "note TEXT NULL, " +
                "total INTEGER NOT NULL, " +
                "discount INTEGER NULL, " +
                "is_paid TINYINT(1) NOT NULL, " +
                "FOREIGN KEY(buyer) REFERENCES user(id));"
                );
        // System.out.println("Tabel Orders berhasil dibuat");
    }

    private static void createTableOrderDetails (Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS order_details(" +
        "orders INTEGER NOT NULL, " +
        "product INTEGER NOT NULL, " +
        "quantity INTEGER NOT NULL, " +
        "price INTEGER NOT NULL, " +
        "FOREIGN KEY(orders) REFERENCES orders(id), " +
        "FOREIGN KEY(product) REFERENCES product(id));"
        );

        // System.out.println("Tabel order_detail berhasil dibuat");
    }

    private static void createTableReviews (Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS reviews(" +
                "orders INTEGER NOT NULL, " +
                "star TINYINT(5) NOT NULL, " +
                "description TEXT NOT NULL);"
                );

        // System.out.println("Tabel review berhasil dibuat.");
    }
}
