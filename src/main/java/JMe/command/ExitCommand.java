package JMe.command;

import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

/**
 * Command to exit the JMe application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printGoodbye();
    }

    /**
     * Returns {@code true} to signal the application to terminate.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}