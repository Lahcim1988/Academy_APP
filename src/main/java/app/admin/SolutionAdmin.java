package main.java.app.admin;

import main.java.app.dao.ExerciseDao;
import main.java.app.dao.SolutionDao;
import main.java.app.dao.UserDao;
import main.java.app.entity.Exercise;
import main.java.app.entity.Solution;
import main.java.app.entity.User;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SolutionAdmin {

    public static void main(String[] args) throws SQLException {

        boolean working = true;
        Scanner scan = new Scanner(System.in);
        SolutionDao solutionDao = new SolutionDao();

        while (working) {

            System.out.println("+++++++++++++++++++++++++++");
            System.out.println("SOLUTION");
            System.out.println("Choose one of the following options: ");
            System.out.println("* add: ");
            System.out.println("* view: ");
            System.out.println("* quit: ");
            System.out.println();

            String option = scan.nextLine();

            switch (option.toLowerCase()) {
                case "add": {

                    System.out.println("User list: ");
                    UserDao userDao = new UserDao();
                    ArrayList<User> userArray = userDao.getAllUsers();
                    for (User user : userArray) {
                        System.out.println(user.toString());
                    }
                    System.out.println("Enter User ID: ");
                    int userID = Integer.parseInt(scan.nextLine());

                    System.out.println("+++++++++++++++++++++++++++");
                    System.out.println("Exercise List: ");
                    ExerciseDao exerciseDao = new ExerciseDao();
                    ArrayList<Exercise> exercisesArray = exerciseDao.getAllExercise();
                    for (Exercise exercise : exercisesArray) {
                        System.out.println(exercise.toString());
                    }
                    System.out.println("Enter Exercise ID: ");
                    int exerID = Integer.parseInt(scan.nextLine());

                    System.out.println("+++++++++++++++++++++++++++");

                    ArrayList<Solution> solutions = solutionDao.findAllByUserId(userID);


                    LocalDateTime obj = LocalDateTime.now();

                    Solution solution = new Solution(obj, obj, exerID, userID);
                    solutionDao.saveToDB(solution);
                    System.out.println("Solution added: ");
                    break;
                }
                case "view": {
                    System.out.println("Enter user ID: ");
                    int userID = Integer.parseInt(scan.nextLine());
                    Solution solution = solutionDao.getSolutionById(userID);
                    System.out.println("Exercise: " + solution.getExercise_id() + ": created" + solution.getCreated() +
                            " description: " + solution.getDescription());

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
