package main.java.app.test;

import main.java.app.dao.GroupDao;
import main.java.app.entity.Group;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class GroupDaoTest {

    private Group group1;
    private Group group2;
    private GroupDao groupDao;

    @Before
    public void setUp(){

        group1 = new Group("beginner");
        group2 = new Group("advanced");
        groupDao = new GroupDao();

    }

    @Test
    public void saveToDB() throws SQLException {

        groupDao.saveToDB(group1);
        groupDao.saveToDB(group2);

    }

    @Test
    public void getGroupById() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAllGroups() {
    }
}