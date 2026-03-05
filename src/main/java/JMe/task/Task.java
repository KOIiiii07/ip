package JMe.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public abstract String toString();

    public String toFileString() {return this.description;}

    public boolean isEqual(String task) {
        return (this.description.equals(task));
    }
}

