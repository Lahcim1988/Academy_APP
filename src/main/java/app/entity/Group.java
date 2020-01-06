package main.java.app.entity;

public class Group {

    private int group_id;
    private String name;

    // CONSTRUCTORS

    public Group() {
    }

    public Group(int group_id, String name) {
        this.group_id = group_id;
        this.name = name;
    }

    // SET / GET


    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("This is an instance of Group class.\n");
        sb.append(String.format("* Group_ID: %d\n", this.group_id));
        sb.append(String.format("* name: %s\n", this.name));
        return sb.toString();
    }
}
