package jme.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline (due date and time).
 * Accepts date-time input in {@code yyyy-MM-dd HHmm} format and
 * displays it in {@code MMM dd yyyy, h:mma} format.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    protected LocalDateTime dueTime;

    /**
     * Constructs a Deadline by parsing the due time from a string.
     *
     * @param description Description of the deadline task.
     * @param dueTimeStr  Due date-time string in {@code yyyy-MM-dd HHmm} format.
     */
    public Deadline(String description, String dueTimeStr) {
        super(description);
        this.dueTime = LocalDateTime.parse(dueTimeStr.trim(), INPUT_FORMAT);
    }

    /**
     * Constructs a Deadline with a pre-parsed {@code LocalDateTime}.
     *
     * @param description Description of the deadline task.
     * @param dueTime     Due date-time as a {@code LocalDateTime}.
     */
    public Deadline(String description, LocalDateTime dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    /**
     * Returns the due date-time of this deadline.
     *
     * @return The due date-time as a {@code LocalDateTime}.
     */
    public LocalDateTime getBy(){
        return this.dueTime;
    }

    @Override
    public String toString() {
        String doneMarker = (this.isDone? "X" : " ");
        return "[D]" + "[" + doneMarker + "] " + this.description + " (by: "
                + this.dueTime.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.isDone? "1 | " : "0 | ") + this.description + " | " + this.dueTime.format(INPUT_FORMAT);
    }

    /**
     * {@inheritDoc}
     * Two deadlines are equal if they share the same description and due time.
     */
    @Override
    public boolean isEqual(Task other) {
        if (!(other instanceof Deadline)) {
            return false;
        }
        Deadline otherDeadline = (Deadline) other;
        return this.description.equals(otherDeadline.description)
                && this.dueTime.equals(otherDeadline.dueTime);
    }
}