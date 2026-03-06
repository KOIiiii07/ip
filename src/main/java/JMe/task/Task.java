package JMe.task;

/**
 * Abstract base class representing a task in JMe.
 * Each task has a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description. The task is initially not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion status of this task.
     *
     * @param isDone Status of the task
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return isDone Status of the task
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a human-readable string representation of this task.
     *
     * @return Formatted string for display to the user.
     */
    public abstract String toString();

    /**
     * Returns a pipe-delimited string representation of this task for file storage.
     *
     * @return Formatted string suitable for saving to a file.
     */
    public String toFileString() {return this.description;}

    /**
     * Checks whether this task is equal to a task described by the given user input string.
     *
     * @param task The user input string to compare against.
     * @return Status of task whether it has duplicates.
     */
    public boolean isEqual(String task) {
        return (this.description.equals(task));
    }
}