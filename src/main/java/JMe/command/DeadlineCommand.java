package JMe.command;

import JMe.exception.JMeException;
import JMe.task.Task;
import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

/**
 * Command to add a new Deadline task.
 */
public class DeadlineCommand extends Command {
    private String arguments;

    /**
     * Constructs a DeadlineCommand with the given arguments.
     *
     * @param arguments The raw input containing the description and due date.
     */
    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.addDeadline(arguments);
            storage.save(tasks.getTasks());
            Ui.printTaskAdded(task, tasks.getTaskCount());
        } catch (JMeException.OutOfBounds e) {
            Ui.printMessage("The list is full");
        } catch (JMeException.Duplicates e) {
            Ui.printMessage("There already exists such task.");
        } catch (JMeException.InvalidFormat e) {
            Ui.printMessage("Invalid format: \"deadline (__) /by (yyyy-MM-dd HHmm)\"");
        }
    }
}