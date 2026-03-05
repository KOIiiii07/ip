package JMe.command;

import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}