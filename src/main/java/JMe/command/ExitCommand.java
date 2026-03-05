package JMe.command;

import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}