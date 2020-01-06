package main.java.app.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static main.java.app.conn.ConnEnum.*;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useSSL=false&characterEncoding=utf8";
        return DriverManager.getConnection(connectionString, USER, PASSWORD);
    }

}
