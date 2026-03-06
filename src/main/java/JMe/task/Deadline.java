package JMe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    protected LocalDateTime dueTime;

    public Deadline(String description, String dueTimeStr) {
        super(description);
        this.dueTime = LocalDateTime.parse(dueTimeStr.trim(), INPUT_FORMAT);
    }

    public Deadline(String description, LocalDateTime dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

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
        return "D | " + (this.isDone? "1 | " : "0 | ") + this.description + " | " + this.dueTime.format(DISPLAY_FORMAT);
    }

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
