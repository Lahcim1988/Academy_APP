package main.java.app.dao;

import main.java.app.conn.ConnectionFactory;
import main.java.app.entity.Solution;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SolutionDao {

    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET created = ?, updated = ?, description = ?, exercise_id = ?, users_id = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY =
            "SELECT * FROM solution";


    // CREATE / UPDATE

    public void saveToDB(Solution solution) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        if (solution.getId() == 0) {
            PreparedStatement stmt = conn.prepareStatement(CREATE_SOLUTION_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, solution.getCreated());
            stmt.setString(2, solution.getUpdated());
            stmt.setString(3, solution.getDescription());
            stmt.setInt(4, solution.getExercise_id());
            stmt.setInt(5, solution.getUser_id());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
        } else {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            stmt.setString(1, solution.getCreated());
            stmt.setString(2, solution.getUpdated());
            stmt.setString(3, solution.getDescription());
            stmt.setInt(4, solution.getExercise_id());
            stmt.setInt(5, solution.getUser_id());
            stmt.setInt(6, solution.getId());
            stmt.executeUpdate();
        }
    }

    // READ

    public Solution getSolutionById(int solutionID) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = conn.prepareStatement(READ_SOLUTION_QUERY);
        stmt.setInt(1, solutionID);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return setSolution(resultSet);
        }
        return null;

    }

    // DELETE

    public void delete(int solutionID) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = conn.prepareStatement(DELETE_SOLUTION_QUERY);
        stmt.setInt(1, solutionID);
        stmt.executeUpdate();
    }

    // FIND_ALL_USERS_QUERY

    public ArrayList<Solution> getAllSolution() throws SQLException {

        Connection conn = ConnectionFactory.getConnection();
        ArrayList<Solution> solutions = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement(FIND_ALL_SOLUTION_QUERY);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            Solution s = setSolution(resultSet);
            solutions.add(s);
        }
        return solutions;
    }

    private Solution setSolution(ResultSet resultSet) throws SQLException {
        Solution s = new Solution();
        s.setId(resultSet.getInt("id"));
        s.setCreated(resultSet.getString("created"));
        s.setUpdated(resultSet.getString("updated"));
        s.setDescription(resultSet.getString("description"));
        s.setExercise_id(resultSet.getInt("exercise_id"));
        s.setUser_id(resultSet.getInt("users_id"));
        return s;
    }
}
