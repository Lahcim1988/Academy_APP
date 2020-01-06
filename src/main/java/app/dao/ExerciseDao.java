package main.java.app.dao;

import main.java.app.conn.ConnectionFactory;
import main.java.app.entity.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseDao {

    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercise(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY =
            "SELECT * FROM exercise where id = ?";
    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercise SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_EXERCISE_QUERY =
            "SELECT * FROM exercise";


    // CREATE / UPDATE

    public void saveToDB(Exercise exercise) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        if (exercise.getId() == 0) {
            PreparedStatement stmt = conn.prepareStatement(CREATE_EXERCISE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, exercise.getTitle());
            stmt.setString(2, exercise.getDescription());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                exercise.setId(resultSet.getInt(1));
            }
        } else {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_EXERCISE_QUERY);
            try {
                stmt.setString(1, exercise.getTitle());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt.setString(2, exercise.getDescription());
            stmt.executeUpdate();
        }
    }

    // READ

    public Exercise getExcerciseById(int exerciseID) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = conn.prepareStatement(READ_EXERCISE_QUERY);
        stmt.setInt(1, exerciseID);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getInt("id"));
            exercise.setTitle(resultSet.getString("title"));
            exercise.setDescription(resultSet.getString("description"));
            return exercise;
        }
        return null;
    }

    // DELETE

    public void delete(int exerciseID) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = conn.prepareStatement(DELETE_EXERCISE_QUERY);
        stmt.setInt(1, exerciseID);
        stmt.executeUpdate();

    }

    // FIND_ALL_EXERCISE_QUERY

    public ArrayList<Exercise> getAllExercise() throws SQLException {

        Connection conn = ConnectionFactory.getConnection();
        ArrayList<Exercise> exercise = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement(FIND_ALL_EXERCISE_QUERY);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            Exercise e = new Exercise();
            e.setId(resultSet.getInt("id"));
            e.setTitle(resultSet.getString("title"));
            e.setDescription(resultSet.getString("description"));
            exercise.add(e);
        }
        return exercise;
    }
}
