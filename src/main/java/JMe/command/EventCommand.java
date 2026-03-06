package JMe.command;

import JMe.exception.JMeException;
import JMe.task.Task;
import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

/**
 * Command to add a new Event task.
 */
public class EventCommand extends Command {
    private String arguments;

    /**
     * Constructs an EventCommand with the given arguments.
     *
     * @param arguments The raw input containing the description, start time, and end time.
     */
    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.addEvent(arguments);
            storage.save(tasks.getTasks());
            Ui.printTaskAdded(task, tasks.getTaskCount());
        } catch (JMeException.OutOfBounds e) {
            Ui.printMessage("The list is full");
        } catch (JMeException.Duplicates e) {
            Ui.printMessage("There already exists such task.");
        } catch (JMeException.InvalidFormat e) {
            Ui.printMessage("Invalid format: \"event (__) /from (__) /to (__)\"");
        }
    }
}