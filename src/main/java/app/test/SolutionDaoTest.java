package main.java.app.test;

import main.java.app.dao.SolutionDao;
import main.java.app.entity.Solution;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SolutionDaoTest {

    private Solution solution;
    private SolutionDao solutionDao;

    @Before
    public void setUp(){

        solution = new Solution("2020-01-05 23:00:00", "2020-01-05 23:00:01", "5");
        solutionDao = new SolutionDao();
    }

    @Test
    public void saveToDB() throws SQLException {

        solutionDao.saveToDB(solution);

    }

    @Test
    public void getSolutionById() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAllSolution() {
    }
}