package JMe.task;

public class Event extends Task{
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

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

    @Override
    public boolean isEqual(String userInput) {
        String[] event = userInput.split("/", 3);

        if (event.length != 3) {
            return false;
        }

        String description = event[0].trim();
        String startTime = event[1].replace("from" , "").trim();
        String endTime = event[2].replace("to" , "").trim();
        return (this.description.equals(description) & this.startTime.equals(startTime) &
                this.endTime.equals(endTime));
    }
}
