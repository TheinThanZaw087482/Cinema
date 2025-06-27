package com.cinema.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class moviedatabase {
    public static Connection connectDb() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a connection to the database (replace with your own credentials)
            Connection connect = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cinemadb?useSSL=false&serverTimezone=UTC", 
                "root", 
                ""
            );
            
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
