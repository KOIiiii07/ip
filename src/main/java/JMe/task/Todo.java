package JMe.task;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "T | " + (this.isDone? "1 | " : "0 | ") + this.description;
    }

    @Override
    public String toString() {
        String doneMarker = (this.isDone ? "X" : " ");
        return "[T]" + "[" + doneMarker + "] " + this.description;
    }
}
