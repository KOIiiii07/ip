package JMe.storage;

import JMe.task.Task;
import JMe.task.Todo;
import JMe.task.Event;
import JMe.task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/jme.txt";

    private static Task fromFileString(String line) {
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

    public static void load(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error loading: " + e.getMessage());
            }
            return;
        }

        try {
            Scanner s = new Scanner(file);

            while (s.hasNextLine()) {
                String line = s.nextLine();
                Task task = fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }

    public static void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
}
