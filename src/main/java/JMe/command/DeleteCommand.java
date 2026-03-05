package JMe.command;

import JMe.exception.JMeException;
import JMe.task.Task;
import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

public class DeleteCommand extends Command {
    private String arguments;

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