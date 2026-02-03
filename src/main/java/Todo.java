public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        String doneMarker = (this.isDone ? "X" : " ");
        return "[T]" + "[" + doneMarker + "] " + description;
    }
}
