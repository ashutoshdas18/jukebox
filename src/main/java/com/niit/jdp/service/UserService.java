/*
 * Author Name : Ashutosh Das
 * Date : 02-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.service;

import com.niit.jdp.exception.InvalidCredentialsException;
import com.niit.jdp.exception.UserAlreadyExistsException;
import com.niit.jdp.log.UserLog;
import com.niit.jdp.repository.UserRepo;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserRepo userRepo;
    private String id;
    private boolean isVerified;

    public String getId() {
        return id;
    }

    public boolean isVerified() {
        return isVerified;
    }

    /**
     * "If the user is already registered, then log them in, otherwise register them."
     * <p>
     * This is a very simple example, but it's easy to see how this can get out of hand
     */
    public void verifyUser() {
        UserLog log = new UserLog();
        int loginStatus = log.welcomeMessage();
        if (loginStatus == 1) {
            loginUser();
        } else {
            registerUser();
        }
    }

    /**
     * It takes the username and password from the user and checks if the user exists in the database
     */
    public void loginUser() {
        try {
            userRepo = new UserRepo();
            UserLog log = new UserLog();
            List<String> loginDetails = log.loginMessage();
            int result = userRepo.loginUser(loginDetails.get(0), loginDetails.get(1));
            if (result == 1) {
                this.id = loginDetails.get(0);
                this.isVerified = true;
            }
        } catch (ClassNotFoundException | SQLException | InvalidCredentialsException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * It takes the user's input, creates a new user object, and then calls the registerUser function in the UserRepo class
     */
    public void registerUser() {
        try {
            UserLog log = new UserLog();
            userRepo = new UserRepo();
            List<String> loginDetails = log.registerMessage();
            int result = userRepo.registerUser(loginDetails.get(0), loginDetails.get(1), loginDetails.get(2));
            if (result == 1) {
                loginUser();
            } else {
                System.err.println("Registration failed");
            }
        } catch (SQLException | ClassNotFoundException | UserAlreadyExistsException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
