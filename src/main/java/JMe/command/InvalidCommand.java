package JMe.command;

import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

/**
 * Command representing an unrecognised or invalid user input.
 * Displays the help message showing valid command formats.
 */
public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printInvalidFormatHelp();
    }
}