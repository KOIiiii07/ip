package JMe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline (due date and time).
 * Accepts date-time input in {@code yyyy-MM-dd HHmm} format and
 * displays it in {@code MMM dd yyyy, h:mma} format.
 */
public class Deadline extends Task{
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
     * @throws DateTimeParseException If {@code dueTimeStr} is not in the expected format.
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
     * Compares by parsing the user input string around the {@code /by} delimiter
     * and checking both description and due time.
     */
    @Override
    public boolean isEqual(String userInput) {
        String[] deadline = userInput.split("/by", 2);
        if (deadline.length != 2) {
            return false;
        }

        try {
            String description = deadline[0].trim();
            LocalDateTime dueTime = LocalDateTime.parse(deadline[1].trim(), INPUT_FORMAT) ;
            return (this.description.equals(description) & this.dueTime.equals(dueTime));
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}