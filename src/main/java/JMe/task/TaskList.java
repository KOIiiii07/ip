package JMe.task;

import JMe.exception.JMeException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    public static final int MAX_TASKS = 100;
    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public boolean isDuplicate(String userInput) {
        for (int i = 0; i < taskCount; i++) {
            if (tasks.get(i).isEqual(userInput)) {
                return true;
            }
        }
        return false;
    }

    public Task addTodo(String userInput) throws JMeException.OutOfBounds,
            JMeException.InvalidFormat, JMeException.Duplicates {
        if (taskCount >= MAX_TASKS) {
            throw new JMeException.OutOfBounds();
        }
        if (userInput.isEmpty()) {
            throw new JMeException.InvalidFormat();
        }
        if (isDuplicate(userInput)) {
            throw new JMeException.Duplicates();
        }

        Task task = new Todo(userInput);
        tasks.add(task);
        taskCount++;
        return task;
    }

    public Task addDeadline(String userInput) throws JMeException.OutOfBounds,
            JMeException.InvalidFormat, JMeException.Duplicates {
        if (taskCount >= MAX_TASKS) {
            throw new JMeException.OutOfBounds();
        }

        String[] deadline = userInput.split("/by", 2);
        if (deadline.length != 2) {
            throw new JMeException.InvalidFormat();
        }
        if (isDuplicate(userInput)) {
            throw new JMeException.Duplicates();
        }

        String description = deadline[0].trim();
        String dueTime = deadline[1].trim();

        try {
            Task task = new Deadline(description, dueTime);
            tasks.add(task);
            taskCount++;
            return task;
        } catch (DateTimeParseException e) {
            throw new JMeException.InvalidFormat();
        }
    }

    public Task addEvent(String userInput) throws JMeException.OutOfBounds,
            JMeException.InvalidFormat, JMeException.Duplicates {
        if (taskCount >= MAX_TASKS) {
            throw new JMeException.OutOfBounds();
        }

        String[] event = userInput.split("/", 3);
        if (event.length != 3) {
            throw new JMeException.InvalidFormat();
        }
        if (isDuplicate(userInput)) {
            throw new JMeException.Duplicates();
        }

        String description = event[0].trim();
        String startTime = event[1].replace("from", "").trim();
        String endTime = event[2].replace("to", "").trim();
        Task task = new Event(description, startTime, endTime);
        tasks.add(task);
        taskCount++;
        return task;
    }

    public Task deleteTask(int index) throws JMeException.OutOfBounds {
        if (index < 0 || index >= taskCount) {
            throw new JMeException.OutOfBounds();
        }
        Task deletedTask = tasks.remove(index);
        taskCount--;
        return deletedTask;
    }

    public Task updateTaskStatus(int index, boolean isDone) throws JMeException.OutOfBounds {
        if (index < 0 || index >= taskCount) {
            throw new JMeException.OutOfBounds();
        }
        Task task = tasks.get(index);
        task.setDone(isDone);
        return task;
    }

    public ArrayList<Task> findTasks(String keyword) throws JMeException.InvalidFormat {
        if (keyword.isEmpty()) {
            throw new JMeException.InvalidFormat();
        }
        ArrayList<Task> results = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks.get(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }
        return results;
    }
}