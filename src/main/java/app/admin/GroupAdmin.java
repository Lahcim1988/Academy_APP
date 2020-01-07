package main.java.app.admin;

import main.java.app.dao.ExerciseDao;
import main.java.app.dao.GroupDao;
import main.java.app.entity.Exercise;
import main.java.app.entity.Group;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GroupAdmin {

    public static void main(String[] args) throws SQLException {

        boolean working = true;
        Scanner scan = new Scanner(System.in);

        while (working) {

            GroupDao groupDao = new GroupDao();
            ArrayList<Group> groupArray = groupDao.getAllGroups();
            for (Group group : groupArray) {
                System.out.println(group.toString());
            }

            System.out.println("+++++++++++++++++++++++++++");
            System.out.println("GROUP");
            System.out.println("Choose one of the following options: ");
            System.out.println("* add: ");
            System.out.println("* edit: ");
            System.out.println("* delete: ");
            System.out.println("* quit: ");
            System.out.println();

            String option = scan.nextLine();

            switch (option.toLowerCase()) {
                case "add": {
                    System.out.println("Enter name: ");
                    String name = scan.nextLine();
                    Group group = new Group(name);
                    groupDao.saveToDB(group);
                    System.out.println("Group correctly added. ");
                    System.out.println("+++++++++++++++++++++++++++");
                    break;
                }
                case "edit": {
                    System.out.println("Enter group id to edit: ");
                    int id = Integer.parseInt(scan.nextLine());
                    System.out.println("Enter new name: ");
                    String name = scan.nextLine();

                    Group group = new Group(name);
                    groupDao.saveToDB(group);
                    System.out.println("Group correctly edited. ");
                    System.out.println("+++++++++++++++++++++++++++");
                    break;
                }
                case "delete": {
                    System.out.println("Enter group id to edit: ");
                    int id = Integer.parseInt(scan.nextLine());
                    groupDao.delete(id);
                    System.out.println("Group deleted. ");
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
