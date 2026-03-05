package JMe.command;

import JMe.exception.JMeException;
import JMe.task.Task;
import JMe.task.TaskList;
import JMe.storage.Storage;
import JMe.ui.Ui;

public class TodoCommand extends Command {
    private String arguments;

    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }

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