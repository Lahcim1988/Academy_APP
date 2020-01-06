package main.java.app.entity;

public class Exercise {

    private int id;
    private String title;
    private String description;

    // CONSTRUCTOR

    public Exercise() {
    }

    public Exercise(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.description = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("This is an instance of Exercise class.\n");
        sb.append(String.format("* ID: %d\n", this.id));
        sb.append(String.format("* title: %s\n", this.title));
        sb.append(String.format("* description: %s\n", this.description));
        return sb.toString();
    }
}
