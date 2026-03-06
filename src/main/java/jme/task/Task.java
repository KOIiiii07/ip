package jme.task;

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
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion status of this task.
     *
     * @param isDone The status of the task
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return isDone The status of the task
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the description of this task.
     *
     * @return The description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a human-readable string representation of this task.
     *
     * @return Formatted string for display to the user.
     */
    public abstract String toString();

    /**
     * Returns a string representation of this task in a format suitable for file storage.
     *
     * @return String representation of the task for saving to a file.
     */
    public abstract String toFileString();

    /**
     * Checks whether this task is equal to another task.
     *
     * @param other The task to compare against.
     * @return Whether the two tasks are considered equal.
     */
    public boolean isEqual(Task other) {
        return this.description.equals(other.description);
    }
}