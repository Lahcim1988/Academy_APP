package main.java.app.test;

import main.java.app.dao.UserDao;
import main.java.app.jbcrypt.BCrypt;
import main.java.app.model.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserDaoTest {

    private User user;
    private UserDao userDao;

    @Before
    public void setUp() {

        user = new User("Tom", "tom@tom.com", "passwordBob");
        userDao = new UserDao();
    }


    @Test
    public void saveToDBTest() throws SQLException {

        userDao.saveToDB(user);

    }

    @Test
    public void updateToDBTest() throws SQLException {

        String newUserName = "Tom";
        String newEmail = "tomy3@tom.com";
        String newPassword = "passwordTom";

        userDao.getUserById(4);
        user.setId(4);
        user.setUsername(newUserName);
        user.setEmail(newEmail);
        user.setPassword(newPassword);
        userDao.saveToDB(user);

        userDao.getUserById(4);

        assertEquals(user.getUsername(), newUserName);
        assertEquals(user.getEmail(), newEmail);

        assertTrue(BCrypt.checkpw(newPassword, user.getPassword()));

        // Remove if/else

        if (BCrypt.checkpw(newPassword, user.getPassword())) {
            System.out.println("OK");
        } else {
            System.out.println("Password does not match");
        }
    }

    @Test
    public void deleteUserTest() throws SQLException {

        userDao.delete(3);
        assertNull(userDao.getUserById(3));

    }

    @Test
    public void getAllUsers() throws SQLException {

        ArrayList<User> users = userDao.getAllUsers();

        for (User value : users) {
            System.out.println(value);
        }
    }
}
