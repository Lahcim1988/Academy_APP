package main.java.app.test;

import main.java.app.dao.ExerciseDao;
import main.java.app.entity.Exercise;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ExerciseDaoTest {

    private Exercise exercise;
    private ExerciseDao exerciseDao;

    @Before
    public void setUp(){

        exercise = new Exercise("Math_Ex1", "a = 2, b = 4, a + b = ?");
        exerciseDao = new ExerciseDao();

    }

    @Test
    public void saveTest() throws SQLException {

        exerciseDao.saveToDB(exercise);

    }

    @Test
    public void getExerciseByIdTest() {
    }

    @Test
    public void deleteTest() {
    }

    @Test
    public void getAllExerciseTest() {
    }
}