package JMe.task;

/**
 * Represents a todo task with no associated date or time.
 */
public class Todo extends Task{

    /**
     * Constructs a Todo with the given description.
     *
     * @param description Description of the todo.
     */
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