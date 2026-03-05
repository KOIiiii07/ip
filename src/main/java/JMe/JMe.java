package JMe;

import JMe.command.Command;
import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.parser.Parser;
import JMe.ui.Ui;

import java.io.IOException;

public class JMe {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        new JMe("data/jme.txt").run();
    }
}