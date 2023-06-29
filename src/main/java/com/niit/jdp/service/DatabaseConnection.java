/*
 * Author Name : Ashutosh Das
 * Date : 30-11-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    Connection connection;

    /**
     * It creates a connection to the database
     */
    public void createConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://localhost:3306/jukebox";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString,"root","Iamashu@18");
    }

    /**
     * This function returns the connection object.
     *
     * @return Connection - The connection object.
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        this.createConnection();
        return connection;
    }
}
