package main.java.app.model;

import main.java.app.jbcrypt.BCrypt;

public class User {

    private long id;
    private String username;
    private String email;
    private String password;

    // CONSTRUCTORS

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    // GET / SET

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    // BCrypt

    public void setPassword(String password) {

        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // toString

    public String toString() {
        StringBuilder sb = new StringBuilder("Hi, this is an instance of User class.\n");
        sb.append(String.format("* ID: %d\n", this.id));
        sb.append(String.format("* username: %s\n", this.username));
        sb.append(String.format("* email: %s\n", this.email));
        return sb.toString();
    }
}
