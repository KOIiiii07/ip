package JMe.command;

import JMe.exception.JMeException;
import JMe.task.Task;
import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

public class MarkCommand extends Command {
    private String arguments;
    private boolean isDone;

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