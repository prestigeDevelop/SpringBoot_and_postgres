package com.myjdbc.jdbcdata;

import lombok.AllArgsConstructor;

import java.sql.*;

@AllArgsConstructor
public class SelectExample {
    static final String DB_URL = "jdbc:mysql://localhost/pc-store";
    static final String USER = "root";
    static final String PASS = "1234";
    static final String QUERY = "SELECT model, color, type, price FROM printer";

    public static void main(String[] args) {

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("Model: " + rs.getInt("model"));
                System.out.print(", Color: " + rs.getString("color"));
                System.out.print(", Type: " + rs.getString("type"));
                System.out.println(", Price: " + rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
