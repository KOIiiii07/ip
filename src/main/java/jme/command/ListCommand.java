package jme.command;

import jme.task.TaskList;
import jme.storage.Storage;
import jme.ui.Ui;

/**
 * Command to display all tasks in the task list.
 */
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