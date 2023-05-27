package com.ayukrisn.ecommerce.persistence;

// Package class dan interfaces untuk mengakses dan memproses data
// pada database
import java.sql.*;
public class Database {
    public static void main (String args[]) {
        // variabel koneksi
        Connection connection = null;

        // percobaan untuk terhubung ke database
        try {
            // load SQLite JDBC Driver
            Class.forName("org.sqlite.JDBC");
            // Establish hubungan ke SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
        } catch ( Exception e ) {
            // Print error bila gagal
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Database sukses dibuka");
    }
}
