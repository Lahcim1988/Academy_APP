package main.java.app.admin;

import main.java.app.dao.ExerciseDao;
import main.java.app.dao.UserDao;
import main.java.app.entity.Exercise;
import main.java.app.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExerciseAdmin {

    public static void main(String[] args) throws SQLException {

        boolean working = true;
        Scanner scan = new Scanner(System.in);

        while (working) {

            ExerciseDao exerciseDao = new ExerciseDao();
            ArrayList<Exercise> exercisesArray = exerciseDao.getAllExercise();
            for (Exercise exercise : exercisesArray) {
                System.out.println(exercise.toString());
            }

            System.out.println("+++++++++++++++++++++++++++");
            System.out.println("EXERCISE");
            System.out.println("Choose one of the following options: ");
            System.out.println("* add: ");
            System.out.println("* edit: ");
            System.out.println("* delete: ");
            System.out.println("* quit: ");
            System.out.println();

            String option = scan.nextLine();

            switch (option.toLowerCase()) {
                case "add": {
                    System.out.println("Enter title: ");
                    String title = scan.nextLine();
                    System.out.println("Enter description: ");
                    String description = scan.nextLine();
                    Exercise exercise = new Exercise(title, description);
                    exerciseDao.saveToDB(exercise);
                    System.out.println("Exercise correctly added. ");
                    System.out.println("+++++++++++++++++++++++++++");
                    break;
                }
                case "edit": {
                    System.out.println("Enter exercise id to edit: ");
                    int id = Integer.parseInt(scan.nextLine());
                    System.out.println("Enter new title: ");
                    String title = scan.nextLine();
                    System.out.println("Enter new description: ");
                    String description = scan.nextLine();

                    Exercise exercise = new Exercise(title, description);
                    exerciseDao.saveToDB(exercise);
                    System.out.println("Exercise correctly edited. ");
                    System.out.println("+++++++++++++++++++++++++++");
                    break;
                }
                case "delete": {
                    System.out.println("Enter exercise id to edit: ");
                    int id = Integer.parseInt(scan.nextLine());
                    exerciseDao.delete(id);
                    System.out.println("Exercise deleted. ");
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
