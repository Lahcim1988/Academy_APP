package main.java.app.entity;

import main.java.app.jbcrypt.BCrypt;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;

    // foreign key
    private int group_id;

    // CONSTRUCTORS

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public User(String username, String email, String password, int group_id) {
        this.username = username;
        this.email = email;
        setPassword(password);
        this.group_id = group_id;
    }

    // GET / SET

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    // foreign key
    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    // toString

    public String toString() {
        StringBuilder sb = new StringBuilder("Hi, this is an instance of User class.\n");
        sb.append(String.format("* ID: %d\n", this.id));
        sb.append(String.format("* username: %s\n", this.username));
        sb.append(String.format("* email: %s\n", this.email));
        sb.append(String.format("* group: %s\n", this.group_id));
        return sb.toString();
    }
}
