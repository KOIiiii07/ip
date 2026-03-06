package jme.command;

import jme.exception.JMeException;
import jme.task.Task;
import jme.task.TaskList;
import jme.storage.Storage;
import jme.ui.Ui;

/**
 * Command to delete a task by its displayed index.
 */
public class DeleteCommand extends Command {
    private String arguments;

    /**
     * Constructs a DeleteCommand with the given arguments.
     *
     * @param arguments The 1-based index of the task to delete, as a string.
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            Task deletedTask = tasks.deleteTask(index);
            storage.save(tasks.getTasks());
            Ui.printTaskDeleted(deletedTask, tasks.getTaskCount());
        } catch (NumberFormatException e) {
            Ui.printMessage("Error: '" + arguments + "' is not a valid number.");
        } catch (JMeException.OutOfBounds e) {
            Ui.printMessage("Your index is out-of-bound.");
        }
    }
}