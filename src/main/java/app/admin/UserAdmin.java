package main.java.app.admin;

import main.java.app.dao.UserDao;
import main.java.app.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserAdmin {

    public static void main(String[] args) throws SQLException {

        boolean working = true;
        Scanner scan = new Scanner(System.in);

        while (working) {

            UserDao userDao = new UserDao();
            ArrayList<User> userArray = userDao.getAllUsers();
            for (User user : userArray) {
                System.out.println(user.toString());
            }

            System.out.println("+++++++++++++++++++++++++++");
            System.out.println("USER");
            System.out.println("Choose one of the following options: ");
            System.out.println("* add: ");
            System.out.println("* edit: ");
            System.out.println("* delete: ");
            System.out.println("* quit: ");
            System.out.println();

            String option = scan.nextLine();

            switch (option.toLowerCase()) {
                case "add": {
                    System.out.println("Enter username: ");
                    String username = scan.nextLine();
                    System.out.println("Enter email: ");
                    String email = scan.nextLine();
                    System.out.println("Enter password: ");
                    String password = scan.nextLine();
                    System.out.println("Enter user group id: ");
                    int userGrID = Integer.parseInt(scan.nextLine());
                    User user = new User(username, email, password, userGrID);
                    userDao.saveToDB(user);
                    System.out.println("User correctly added. ");
                    System.out.println("+++++++++++++++++++++++++++");
                    break;
                }
                case "edit": {
                    System.out.println("Enter user id to edit: ");
                    int id = Integer.parseInt(scan.nextLine());
                    System.out.println("Enter new username: ");
                    String username = scan.nextLine();
                    System.out.println("Enter new email: ");
                    String email = scan.nextLine();
                    System.out.println("Enter new password: ");
                    String password = scan.nextLine();
                    System.out.println("Enter new user group id: ");
                    int userGrID = Integer.parseInt(scan.nextLine());

                    User user = new User(username, email, password, userGrID);
                    userDao.saveToDB(user);
                    System.out.println("User correctly edited. ");
                    System.out.println("+++++++++++++++++++++++++++");
                    break;
                }
                case "delete": {
                    System.out.println("Enter user id to edit: ");
                    int id = Integer.parseInt(scan.nextLine());
                    userDao.delete(id);
                    System.out.println("User deleted. ");
                    System.out.println("+++++++++++++++++++++++++++");
                    break;
                }
                case "quit": {
                    working = false;
                    break;
                }
                default: {
                    System.out.println("Not recognized");
                    option.toLowerCase();
                }
            }
        }
    }
}
