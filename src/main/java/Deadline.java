public class Deadline extends Task{
    protected String dueTime;

    public Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    public String getBy(){
        return dueTime;
    }

    public void setBy(String by){
        this.dueTime = dueTime;
    }

    public String toString() {
        String doneMarker = (this.isDone? "X" : " ");
        return "[D]" + "[" + doneMarker + "] " + description + " (by: " + dueTime + ")";
    }

    @Override
    public boolean isEqual(String userInput) {
        String[] deadline = userInput.split("/by", 2);
        if (deadline.length != 2) {
            return false;
        }
        String description = deadline[0].trim();
        String dueTime = deadline[1].trim();
        return (this.description.equals(description) & this.dueTime.equals(dueTime));
    }
}
