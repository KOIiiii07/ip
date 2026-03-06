package JMe.storage;

import JMe.task.Task;
import JMe.task.Todo;
import JMe.task.Event;
import JMe.task.Deadline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading tasks from and saving tasks to a file.
 * Tasks are stored in a pipe-delimited format (e.g. {@code T | 0 | read book}).
 * Missing directories and files are created silently on first load.
 */
public class Storage {
    private String filePath;


    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath Path to the file used for persisting tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses a single line from the storage file into a Task object.
     *
     * @param line A pipe-delimited line from the storage file.
     * @return The parsed Task, or {@code null} if the task type is unrecognised.
     */
    private Task fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        Task task = switch (parts[0]) {
            case "T" -> new Todo(parts[2]);
            case "D" -> new Deadline(parts[2], parts[3]);
            case "E" -> new Event(parts[2], parts[3], parts[4]);
            default -> null;
        };

        if (task != null) {
            task.setDone(isDone);
        }

        return task;
    }

    /**
     * Loads all tasks from the storage file.
     * If the file or its parent directories do not exist, they are created
     * and an empty list is returned.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs during file creation or reading.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            try {
                Task task = fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            } catch (Exception e) {
                // Skip malformed lines instead of crashing
                System.out.println("Skipping corrupted line: " + line);
            }
        }
        s.close();

        return tasks;
    }

    /**
     * Saves all tasks to the storage file, overwriting any existing content.
     *
     * @param tasks The list of tasks to save.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
}