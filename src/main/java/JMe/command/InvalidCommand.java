package JMe.command;

import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printInvalidFormatHelp();
    }
}