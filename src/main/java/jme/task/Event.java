package jme.task;

/**
 * Represents an event task that spans a time range with a start and end time.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructs an Event with the given description, start time, and end time.
     *
     * @param description Description of the event.
     * @param startTime   Start time of the event.
     * @param endTime     End time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the start time of this event.
     *
     * @return The start time string.
     */
    public String getStartTime(){
        return this.startTime;
    }

    /**
     * Sets the start time of this event.
     *
     * @param startTime The new start time.
     */
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    /**
     * Returns the end time of this event.
     *
     * @return The end time string.
     */
    public String getEndTime(){
        return endTime;
    }

    /**
     * Sets the end time of this event.
     *
     * @param endTime The new end time.
     */
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String doneMarker = (this.isDone? "X" : " ");
        return "[E]" + "[" + doneMarker + "] " + this.description + " (from: " + this.startTime + " to: "
                + this.endTime + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (this.isDone? "1 | " : "0 | ") + this.description + " | " + this.startTime + " | " +
                this.endTime;
    }

    /**
     * {@inheritDoc}
     * Two events are equal if they share the same description, start time, and end time.
     */
    @Override
    public boolean isEqual(Task other) {
        if (!(other instanceof Event)) {
            return false;
        }
        Event otherEvent = (Event) other;
        return this.description.equals(otherEvent.description)
                && this.startTime.equals(otherEvent.startTime)
                && this.endTime.equals(otherEvent.endTime);
    }
}