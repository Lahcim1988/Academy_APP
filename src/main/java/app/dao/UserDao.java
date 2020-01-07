package main.java.app.dao;

import main.java.app.conn.ConnectionFactory;
import main.java.app.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password, group_id) VALUES (?, ?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ?, group_id = ? where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";


    // CREATE / UPDATE

    public void saveToDB(User user) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        if (user.getId() == 0) {
            PreparedStatement stmt = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getGroup_id());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } else {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_USER_QUERY);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getGroup_id());
            stmt.setInt(5, user.getId());
            stmt.executeUpdate();
        }
    }

    // READ

    public User getUserById(int userID) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = conn.prepareStatement(READ_USER_QUERY);
        stmt.setInt(1, userID);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return setUser(resultSet);
        }
        return null;

    }

    // DELETE

    public void delete(int userID) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = conn.prepareStatement(DELETE_USER_QUERY);
        stmt.setInt(1, userID);
        stmt.executeUpdate();

    }

    // FIND_ALL_USERS_QUERY

    public ArrayList<User> getAllUsers() throws SQLException {

        Connection conn = ConnectionFactory.getConnection();
        ArrayList<User> users = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement(FIND_ALL_USERS_QUERY);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            User user = setUser(resultSet);
            users.add(user);
        }
        return users;
    }

    private User setUser(ResultSet resultSet) throws SQLException {
        User u = new User();
        u.setId(resultSet.getInt("id"));
        u.setUsername(resultSet.getString("username"));
        u.setEmail(resultSet.getString("email"));
        u.setPassword(resultSet.getString("password"));
        u.setGroup_id(resultSet.getInt("group_id"));
        return u;
    }

}
