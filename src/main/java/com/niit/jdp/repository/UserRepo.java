/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.repository;

import com.niit.jdp.exception.InvalidCredentialsException;
import com.niit.jdp.exception.UserAlreadyExistsException;
import com.niit.jdp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {
    Connection connection;

    //Constructor
    public UserRepo() throws ClassNotFoundException, SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }

    /**
     * > This function registers a user
     *
     * @return void
     */
    public int registerUser(String userId, String name, String password) throws UserAlreadyExistsException {
        try {
            String query = "insert into `users` values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, password);
            statement.setString(3, name);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            throw new UserAlreadyExistsException("User already exists try other user ids");
        }
    }

    /**
     * It takes the user's input, checks if it matches the database, and if it does, it sets the user's id and isLoggedIn
     * to true
     */
    public int loginUser(String userId, String password) throws SQLException, InvalidCredentialsException {
        String query = "select count(*) from `users` where `id` = ? and `password` = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int result = resultSet.getInt(1);
        if (result == 0) {
            throw new InvalidCredentialsException("Either id or pass didn't match, try again");
        }
        return result;
    }
}
