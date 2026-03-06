package JMe;

import JMe.command.Command;
import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.parser.Parser;
import JMe.ui.Ui;

import java.io.IOException;

/**
 * Main class for the JMe task management chatbot.
 * Initialises the application components and runs the main command loop.
 */
public class JMe {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a JMe instance with the specified file path for task storage.
     * Loads existing tasks from the file, or starts with an empty list if loading fails.
     *
     * @param filePath Path to the file used for persisting tasks.
     */
    public JMe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            Ui.printMessage("Error loading file. Starting with empty list.");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop.
     * Continuously reads user input, parses it into a command, and executes
     * it until an exit command is received.
     */
    public void run() {
        Ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    /**
     * Entry point for the JMe application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new JMe("data/jme.txt").run();
    }
}