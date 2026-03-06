package jme.command;

import jme.exception.JMeException;
import jme.task.Task;
import jme.task.TaskList;
import jme.storage.Storage;
import jme.ui.Ui;

import java.util.ArrayList;

/**
 * Command to search for tasks containing a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the given search keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.getTaskCount() == 0) {
                Ui.printMessage("The list is empty!");
                return;
            }
            ArrayList<Task> results = tasks.findTasks(keyword);
            if (results.isEmpty()) {
                Ui.printMessage("Unfortunately, there are no matching tasks in the list!");
                return;
            }
            Ui.printFindTasks(results, results.size());
        } catch (JMeException.InvalidFormat e) {
            Ui.printMessage("Invalid format: \"find (__)\"");
        }
    }
}