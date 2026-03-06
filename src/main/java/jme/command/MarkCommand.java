package jme.command;

import jme.exception.JMeException;
import jme.task.Task;
import jme.task.TaskList;
import jme.storage.Storage;
import jme.ui.Ui;

/**
 * Command to mark or unmark a task as done.
 */
public class MarkCommand extends Command {
    private String arguments;
    private boolean isDone;

    /**
     * Constructs a MarkCommand with the given arguments and desired status.
     *
     * @param arguments The 1-based index of the task, as a string.
     * @param isDone    {@code true} to mark as done, {@code false} to unmark.
     */
    public MarkCommand(String arguments, boolean isDone) {
        this.arguments = arguments;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            Task task = tasks.updateTaskStatus(index, isDone);
            storage.save(tasks.getTasks());
            Ui.printTaskStatus(task);
        } catch (NumberFormatException e) {
            Ui.printMessage("Error: '" + arguments + "' is not a valid number.");
        } catch (JMeException.OutOfBounds e) {
            Ui.printMessage("Your index is out-of-bound.");
        }
    }
}