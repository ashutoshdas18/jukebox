package com.niit.jdp.repository;

import com.niit.jdp.exception.InvalidCredentialsException;
import com.niit.jdp.exception.UserAlreadyExistsException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class UserRepoTest {
    UserRepo userRepo;
    @Before
    public void setUp() throws Exception {
        userRepo = new UserRepo();
    }

    @After
    public void tearDown() throws Exception {
        userRepo = null;
    }

    @Test
    public void registerUser() {
        try {
            int expected = 1;
            int actual = userRepo.registerUser("test579", "12345abc", "Test");
            Assert.assertEquals("Values mismatch", expected, actual);
        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void loginUser() {
        try {
            int expected = 1;
            int actual = userRepo.loginUser("test579", "12345abc");
            Assert.assertEquals("Values mismatch", expected, actual);
        } catch (SQLException | InvalidCredentialsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void registerUserFail() {
        int actual = 0;
        int expected = 1;
        try {
            actual = userRepo.registerUser("test579", "12345abc", "Test");
            Assert.assertEquals("Values mismatch", expected, actual);
        } catch (UserAlreadyExistsException e) {
            Assert.assertNotEquals("Values mismatch", expected, actual);
        }
    }

    @Test
    public void loginUserFail() {
        try {
            int expected = 1;
            int actual = userRepo.loginUser("test5796", "12345abc");
            Assert.assertNotEquals("Values mismatch", expected, actual);
        } catch (SQLException | InvalidCredentialsException e) {
            throw new RuntimeException(e);
        }
    }
}