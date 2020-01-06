package main.java.app.test;

import main.java.app.jbcrypt.BCrypt;
import main.java.app.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;
    private final String password = "TestedPassword";

    @Before
    public void setUp(){

        user = new User("UserName", "UserEmail", password);
    }

    @Test
    public void setPasswordTest() {

        System.out.println(user.getPassword());

    }

    @Test
    public void compareHashPasswordTest(){

        if(BCrypt.checkpw(password, user.getPassword())){
            System.out.println("OK");
        }else {
            System.out.println("Password does not match");
        }

    }
}