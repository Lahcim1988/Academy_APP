package main.java.app.entity;

public class Solution {

    private int id;
    private String created;
    private String updated;
    private String description;

    // CONSTRUCTOR

    public Solution() {
    }

    public Solution(int id, String created, String updated, String description) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    // SET / GET

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("This is an instance of Solution class.\n");
        sb.append(String.format("* ID: %d\n", this.id));
        sb.append(String.format("* created: %s\n", this.created));
        sb.append(String.format("* updated: %s\n", this.updated));
        sb.append(String.format("* description: %s\n", this.description));
        return sb.toString();
    }
}
