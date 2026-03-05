package JMe.command;

import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTaskCount() == 0) {
            Ui.printMessage("No tasks in the list");
            return;
        }
        Ui.printTaskList(tasks.getTasks(), tasks.getTaskCount());
    }
}