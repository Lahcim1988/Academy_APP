package main.java.app.dao;

import main.java.app.conn.ConnectionFactory;
import main.java.app.entity.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDao {

    private static final String CREATE_GROUP_QUERY =
            "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_GROUP_QUERY =
            "SELECT * FROM user_group where group_id = ?";
    private static final String UPDATE_GROUP_QUERY =
            "UPDATE user_group SET name = ? where group_id = ?";
    private static final String DELETE_GROUP_QUERY =
            "DELETE FROM user_group WHERE group_id = ?";
    private static final String FIND_ALL_GROUP_QUERY =
            "SELECT * FROM user_group";
// CREATE / UPDATE

    public void saveToDB(Group group) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        if (group.getGroup_id() == 0) {
            PreparedStatement stmt = conn.prepareStatement(CREATE_GROUP_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, group.getName());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                group.setGroup_id(resultSet.getInt(1));
            }
        } else {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_GROUP_QUERY);
            try {
                stmt.setString(1, group.getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt.setInt(2, group.getGroup_id());
            stmt.executeUpdate();
        }
    }

    // READ

    public Group getGroupById(int groupID) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = conn.prepareStatement(READ_GROUP_QUERY);
        stmt.setInt(1, groupID);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            Group group = new Group();
            group.setGroup_id(resultSet.getInt("group_id"));
            group.setName(resultSet.getString("name"));
            return group;
        }
        return null;

    }

    // DELETE

    public void delete(int groupID) throws SQLException {

        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement stmt = conn.prepareStatement(DELETE_GROUP_QUERY);
        stmt.setInt(1, groupID);
        stmt.executeUpdate();

    }

    // FIND_ALL_GROUP_QUERY

    public ArrayList<Group> getAllGroups() throws SQLException {

        Connection conn = ConnectionFactory.getConnection();
        ArrayList<Group> groups = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement(FIND_ALL_GROUP_QUERY);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            Group g = new Group();
            g.setGroup_id(resultSet.getInt("group_id"));
            g.setName(resultSet.getString("name"));
            groups.add(g);
        }
        return groups;
    }
}

