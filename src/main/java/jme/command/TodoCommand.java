package jme.command;

import jme.exception.JMeException;
import jme.task.Task;
import jme.task.TaskList;
import jme.storage.Storage;
import jme.ui.Ui;

/**
 * Command to add a new Todo task.
 */
public class TodoCommand extends Command {
    private String arguments;

    /**
     * Constructs a TodoCommand with the given arguments.
     *
     * @param arguments The description of the todo task.
     */
    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command by adding a todo task to the list and saving.
     *
     * @param tasks   The task list to add the todo task.
     * @param ui      The UI component (unused directly).
     * @param storage The storage component for persisting the updated list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.addTodo(arguments);
            storage.save(tasks.getTasks());
            Ui.printTaskAdded(task, tasks.getTaskCount());
        } catch (JMeException.OutOfBounds e) {
            Ui.printMessage("The list is full");
        } catch (JMeException.Duplicates e) {
            Ui.printMessage("There already exists such task.");
        } catch (JMeException.InvalidFormat e) {
            Ui.printMessage("Invalid format: \"todo (__) \"");
        }
    }
}