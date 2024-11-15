package org.example.application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConfig {

    private static final String URL = "jdbc:postgresql://localhost:5432/db";
    private static final String USER = "user";
    private static final String PASSWORD = "123";

    /**
     * Get a connection to the PostgreSQL database.
     *
     * @return a connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Attempt to connect to the database using the URL, user, and password.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return null;
    }
}
