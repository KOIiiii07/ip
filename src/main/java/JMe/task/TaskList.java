package JMe.task;

import JMe.exception.JMeException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages the list of tasks in JMe.
 * Provides methods for adding, deleting, updating, and searching tasks,
 * with validation for duplicates, capacity limits, and input format.
 */
public class TaskList {
    /** Maximum number of tasks allowed in the list. */
    public static final int MAX_TASKS = 100;
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructs a TaskList pre-populated with the given tasks.
     *
     * @param tasks An ArrayList of tasks to initialise the list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return ArrayList of all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return The task count.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Checks whether a task matching the given user input already exists in the list.
     *
     * @param userInput The user input string to check against existing tasks.
     * @return status if a duplicate is found
     */
    public boolean isDuplicate(String userInput) {
        for (int i = 0; i < taskCount; i++) {
            if (tasks.get(i).isEqual(userInput)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new Todo task to the list.
     *
     * @param userInput The description of the todo.
     * @return The newly created Todo task.
     * @throws JMeException.OutOfBounds   If the list has reached maximum capacity.
     * @throws JMeException.InvalidFormat If the input is empty.
     * @throws JMeException.Duplicates    If an identical task already exists.
     */
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

    /**
     * Adds a new Deadline task to the list.
     * Expects the user input to contain a {@code /by} delimiter separating the
     * description from the due date-time.
     *
     * @param userInput The raw user input in the format {@code description /by yyyy-MM-dd HHmm}.
     * @return The newly created Deadline task.
     * @throws JMeException.OutOfBounds   If the list has reached maximum capacity.
     * @throws JMeException.InvalidFormat If the input format is incorrect or the date cannot be parsed.
     * @throws JMeException.Duplicates    If an identical task already exists.
     */
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

    /**
     * Adds a new Event task to the list.
     * Expects the user input to contain {@code /from} and {@code /to} delimiters.
     *
     * @param userInput The raw user input in the format {@code description /from start /to end}.
     * @return The newly created Event task.
     * @throws JMeException.OutOfBounds   If the list has reached maximum capacity.
     * @throws JMeException.InvalidFormat If the input does not contain the required delimiters.
     * @throws JMeException.Duplicates    If an identical task already exists.
     */
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

    /**
     * Deletes the task at the specified index.
     *
     * @param index Zero-based index of the task to delete.
     * @return The deleted Task.
     * @throws JMeException.OutOfBounds If the index is out of range.
     */
    public Task deleteTask(int index) throws JMeException.OutOfBounds {
        if (index < 0 || index >= taskCount) {
            throw new JMeException.OutOfBounds();
        }
        Task deletedTask = tasks.remove(index);
        taskCount--;
        return deletedTask;
    }

    /**
     * Updates the completion status of the task at the specified index.
     *
     * @param index  Zero-based index of the task to update.
     * @param isDone The status of the task.
     * @return The updated Task.
     * @throws JMeException.OutOfBounds If the index is out of range.
     */
    public Task updateTaskStatus(int index, boolean isDone) throws JMeException.OutOfBounds {
        if (index < 0 || index >= taskCount) {
            throw new JMeException.OutOfBounds();
        }
        Task task = tasks.get(index);
        task.setDone(isDone);
        return task;
    }

    /**
     * Searches for tasks whose string representation contains the given keyword
     * (case-insensitive).
     *
     * @param keyword The keyword to search for.
     * @return An ArrayList of matching tasks.
     * @throws JMeException.InvalidFormat If the keyword is empty.
     */
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