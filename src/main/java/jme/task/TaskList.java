package jme.task;

import jme.exception.JMeException;

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
     * Checks whether a task with the same fields already exists in the list.
     *
     * @param newTask The task to check against existing tasks.
     * @return {@code true} if a duplicate is found.
     */
    public boolean isDuplicate(Task newTask) {
        for (int i = 0; i < taskCount; i++) {
            if (tasks.get(i).isEqual(newTask)) {
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
        // Format validation is type-specific (differing delimiters), so it is not extracted into a shared method.
        if (taskCount >= MAX_TASKS) {
            throw new JMeException.OutOfBounds();
        }
        if (userInput.isEmpty()) {
            throw new JMeException.InvalidFormat();
        }

        Task task = new Todo(userInput.trim());
        if (isDuplicate(task)) {
            throw new JMeException.Duplicates();
        }

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

        String description = deadline[0].trim();
        String dueTime = deadline[1].trim();

        if (description.isEmpty() || dueTime.isEmpty()) {
            throw new JMeException.InvalidFormat();
        }

        Task task;
        try {
            task = new Deadline(description, dueTime);
        } catch (DateTimeParseException e) {
            throw new JMeException.InvalidFormat();
        }

        if (isDuplicate(task)) {
            throw new JMeException.Duplicates();
        }

        tasks.add(task);
        taskCount++;
        return task;
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
        if (event.length != 3 || !event[1].trim().startsWith("from") || !event[2].trim().startsWith("to")) {
            throw new JMeException.InvalidFormat();
        }

        String description = event[0].trim();
        String startTime = event[1].replace("from", "").trim();
        String endTime = event[2].replace("to", "").trim();
        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new JMeException.InvalidFormat();
        }

        Task task = new Event(description, startTime, endTime);
        if (isDuplicate(task)) {
            throw new JMeException.Duplicates();
        }
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
     * Searches for tasks whose descriptions contain the given keyword
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
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }
        return results;
    }
}